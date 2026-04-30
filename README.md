
# User API

API REST para gerenciamento de usuários, cadastro com link de indicação e sistema de pontuação.
Cada usuário possui um código único de indicação: sempre que alguém se cadastra através desse código, o usuário que indicou ganha +1 ponto.

## Funcionalidades

- Cadastro de usuários com nome, e-mail e senha.
- Geração de código de indicação único.
- Sistema de pontuação automático ao indicar novos usuários.
- Login com autenticação JWT.
- Persistência dos dados em um banco MySQL.


## Ferramentas

- Java 17: linguagem principal
- Spring Boot 3: criação da API REST
- Spring Security + JWT: autenticação segura e baseada em tokens
- Spring Data JPA (Hibernate): abstração de persistência
- MySQL: banco relacional para os dados
- Maven: gerenciamento de dependências e build
- Railway: hospedagem da API, Front e do banco de dados em nuvem
## Rodando localmente
Clonar o repositório:
```bash
git clone https://github.com/lucasF4lcao/user-api.git 
    
cd user-api
```
Configurar o banco MySQL:
- Crie um banco no MySQL
- Atualize as credenciais no arquivo src/main/resources/application.properties:
```java
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```
Rodar:
```bash
./mvnw spring-boot:run
```
## Acesso ao projeto

A API está hospedada no Railway e disponível publicamente:

https://user-api-production-49af.up.railway.app/
    
## Documentação da API

#### Cadastro de usuário

```http  
POST /users
```
```http  
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123",
  "referralCode": "abcd1234"
}
```
Resposta:
```http  
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@email.com",
  "points": 0,
  "referralCode": "abcd1234"
}
```

#### Login

```http
POST /auth/login
```
```http  
{
  "email": "joao@email.com",
  "password": "senha123",
}
```
Resposta:
```http  
{
  "token": "token-gerado",
  "expiresIn": 3600
}
```

#### Perfil autenticado

```http
GET /users/auth/me
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token`      | `Bearer` | token-gerado |

Resposta:
```http
{
  "id": 1
  "name": "João Silva",
  "email": "joao@email.com",
  "points": 0
  "referralCode": "abcd1234"
}
```
