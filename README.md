CRUD de Usuários – Spring Boot + JWT

API REST desenvolvida em Java com Spring Boot para cadastro, autenticação e gerenciamento de usuários, utilizando JWT (JSON Web Token) para autenticação e controle de acesso.

Funcionalidades

Cadastro de usuários

Login com geração de token JWT

CRUD completo de usuários

Rotas protegidas com Spring Security

Senhas criptografadas com BCrypt

Tecnologias

Java

Spring Boot

Spring Security

Spring Data JPA

JWT

Maven

Autenticação

Após o login, o token JWT deve ser enviado no header das requisições protegidas:

Authorization: Bearer <token>

Execução
mvn spring-boot:run


API disponível em:

http://localhost:8080
