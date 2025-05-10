# Configurar o provedor AWS
provider "aws" {
  region = var.aws_region
}

# Criar VPC para os serviços
resource "aws_vpc" "cadastro_vpc" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "cadastro_subnet" {
  vpc_id            = aws_vpc.cadastro_vpc.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-east-1a"
}

# Criar Security Group para acesso ao PostgreSQL
resource "aws_security_group" "cadastro_sg" {
  vpc_id = aws_vpc.cadastro_vpc.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Criar banco de dados RDS PostgreSQL
resource "aws_db_instance" "cadastro_db" {
  engine               = "postgres"
  engine_version       = "15"
  instance_class       = "db.t3.micro"
  allocated_storage    = 20
  db_name              = var.db_name
  username             = var.db_user
  password             = var.db_password
  vpc_security_group_ids = [aws_security_group.cadastro_sg.id]
  publicly_accessible  = false
}

# Criar fila RabbitMQ via Amazon MQ
resource "aws_mq_broker" "cadastro_rabbitmq" {
  broker_name        = "cadastro-rabbitmq"
  engine_type        = "RabbitMQ"
  engine_version     = "3.9.13"
  host_instance_type = "mq.t3.micro"

  security_groups = [aws_security_group.cadastro_sg.id]
  subnet_ids      = [aws_subnet.cadastro_subnet.id]
}

# Criar um API Gateway para expor a API
resource "aws_apigatewayv2_api" "cadastro_api_gateway" {
  name          = var.api_gateway_name
  protocol_type = "HTTP"
}

resource "aws_apigatewayv2_stage" "cadastro_api_stage" {
  api_id      = aws_apigatewayv2_api.cadastro_api_gateway.id
  name        = "production"
  auto_deploy = true
}

# Criar um cluster Kubernetes (EKS)
resource "aws_eks_cluster" "cadastro_eks" {
  name     = var.eks_cluster_name
  role_arn = aws_iam_role.eks_role.arn

  vpc_config {
    subnet_ids = [aws_subnet.cadastro_subnet.id]
  }
}

resource "aws_eks_node_group" "cadastro_nodes" {
  cluster_name    = aws_eks_cluster.cadastro_eks.name
  node_role_arn   = aws_iam_role.eks_node_role.arn
  subnet_ids      = [aws_subnet.cadastro_subnet.id]
  instance_types  = ["t3.medium"]
}

# Output dos recursos criados
output "rds_endpoint" {
  value = aws_db_instance.cadastro_db.endpoint
}

output "rabbitmq_endpoint" {
  value = aws_mq_broker.cadastro_rabbitmq.instances[0].endpoint
}

output "api_gateway_url" {
  value = aws_apigatewayv2_api.cadastro_api_gateway.api_endpoint
}

output "eks_cluster_name" {
  value = aws_eks_cluster.cadastro_eks.name
}