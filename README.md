# API de Aplicação Bancária

# Sobre o projeto

O projeto consiste em uma API REST que fornece funcionalidades básicas para uma aplicação bancária, permitindo a criação, atualização, exclusão e consulta as informações de uma ou mais contas. Além disso, também é possível realizar depósitos e saques.

# Tecnologias utilizadas

## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Lombok

## Banco de Dados
- MySQL

# Configuração do ambiente

## Pré-requisitos
- Java 17
- MySQL

## Configuração do Banco de Dados

Antes de executar o projeto, certifique-se de criar um banco local chamado "banking_app".

Em seguida, no arquivo `src/main/resources/application.properties`, configure os detalhes de acesso ao banco local e altere SEU_USUARIO e SUA_SENHA de acordo com seus dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_app
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
```

# Como executar o projeto

## Clone o repositório
```bash
git clone https://github.com/felipecartaxo/java_springboot-banking_app
```

## Entre na pasta do projeto
```bash
cd .\main\java\com\example\banking_app\
```

# E execute o arquivo abaixo
```bash
BankingAppApplication.java
```

# Autor

Felipe Cartaxo de Freitas

https://www.linkedin.com/in/felipecartaxo-dev/
