# Definição das variáveis
variable "aws_region" {
  description = "Região da AWS onde os recursos serão criados"
  type        = string
  default     = "us-east-1"
}

variable "aws_vpc_id" {
  description = "ID da VPC onde os serviços serão provisionados"
  type        = string
}

variable "db_name" {
  description = "Nome do banco de dados PostgreSQL"
  type        = string
  default     = "cadastro_db"
}

variable "db_user" {
  description = "Usuário do banco de dados"
  type        = string
}

variable "db_password" {
  description = "Senha do banco de dados"
  type        = string
  sensitive   = true
}

variable "eks_cluster_name" {
  description = "Nome do cluster Kubernetes (EKS)"
  type        = string
  default     = "cadastro-eks"
}

variable "instance_type" {
  description = "Tipo de instância EC2 utilizada"
  type        = string
  default     = "t3.medium"
}

variable "api_gateway_name" {
  description = "Nome do API Gateway"
  type        = string
  default     = "cadastro-api-gateway"
}

variable "rabbitmq_instance_type" {
  description = "Tipo de instância do RabbitMQ"
  type        = string
  default     = "t3.micro"
}

variable "monitoring_enabled" {
  description = "Habilitar monitoramento com Grafana e Prometheus"
  type        = bool
  default     = true
}

variable "tls_enabled" {
  description = "Habilitar TLS na API"
  type        = bool
  default     = true
}