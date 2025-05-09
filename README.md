📌 Cadastro API

🚀 Cadastro API é uma API RESTful desenvolvida em Java (Spring Boot) que permite o cadastro de usuários, consulta de registros e envio de notificações por e-mail. A aplicação segue arquitetura hexagonal e é conteinerizada, permitindo fácil escalabilidade.

✨ Funcionalidades

✅ Cadastro de usuários com nome, sobrenome, idade e país
✅ Envio de notificação assíncrona via RabbitMQ
✅ Consulta de um cadastro por ID
✅ Listagem de todos os cadastros
✅ Atualização parcial do cadastro
✅ Exclusão de um cadastro
✅ Exposição da API via API Gateway
✅ Observabilidade com Grafana, Prometheus e Loki
✅ Deploy automatizado usando Docker e Kubernetes
✅ Infraestrutura gerenciada via Terraform na AWS

🚀 Tecnologias Utilizadas
- Spring Boot (Framework principal)
- Spring Data JPA (Banco de dados PostgreSQL)
- Spring AMQP (Mensageria com RabbitMQ)
- JUnit + Mockito (Testes unitários)
- Docker (Containerização)
- Kubernetes (Orquestração)
- Grafana + Prometheus + Loki (Monitoramento)
- Terraform (Infraestrutura como código)
- AWS (Hospedagem e serviços cloud)

🔧 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- Java 17+
- Docker e Docker Compose
- Kubernetes (kubectl + minikube)
- Terraform (para provisionamento)
- PostgreSQL (Banco de dados)

📜 Instalação
1️⃣ Clone o repositório
git clone https://github.com/seu-repositorio/cadastro-api.git
cd cadastro-api


2️⃣ Configurar variáveis de ambiente
Edite o arquivo application.yml ou crie um .env:
DATABASE_URL=jdbc:postgresql://localhost:5432/cadastro
RABBITMQ_URL=amqp://guest:guest@localhost


3️⃣ Executar via Docker
docker-compose up -d


4️⃣ Executar via Kubernetes
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml


5️⃣ Verificar logs e métricas
Acesse o Grafana para visualizar métricas:
http://localhost:3000

🛠 Endpoints da API
Cadastro
✅ POST /cadastros → Criação de cadastro
✅ GET /cadastros/{id} → Consulta por ID
✅ GET /cadastros → Listagem de cadastros
✅ PATCH /cadastros/{id} → Atualização parcial
✅ DELETE /cadastros/{id} → Exclusão
Exemplo de requisição:
POST /cadastros
{
"nome": "Eduardo",
"sobrenome": "Silva",
"idade": 30,
"pais": "Brasil"
}



✅ Testes
Para rodar os testes unitários:
mvn test


Cobertura esperada: 90%+ 🛡️

📦 Estrutura do Projeto
cadastro-api/
├── src/main/java/com/example/cadastro/
│   ├── domain/       # Entidades e regras de negócio
│   ├── application/  # Casos de uso
│   ├── infrastructure/  # API, Banco de Dados, Mensageria
├── docker/
├── k8s/
├── terraform/
├── README.md
├── pom.xml



🏗 Deploy AWS via Terraform
Para provisionar a infraestrutura:
cd terraform
terraform init
terraform apply


Isso criará RDS (PostgreSQL), API Gateway, RabbitMQ e Kubernetes (EKS) na AWS.


