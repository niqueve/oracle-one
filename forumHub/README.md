<h1 align="center"> Fórum Hub Challenge</h1>

<p align="center">
   <img src="https://img.shields.io/static/v1?label=springBoot&message=framework&color=blue&style=for-the-badge&logo=SpringBoot"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>

### Descrição do desafio
#### Objetivo

Construir um Fórum semelhante ao encontrado na Alura onde alunos e alunas podem tirar suas dúvidas sobre cursos e projetos em que estão participando.
A API se concentrará especificamente nos tópicos, e deve permitir aos usuários:
<ol>
    <li>Criar um novo tópico</li>
    <li>Mostrar todos os tópicos criados</li>
    <li>Mostrar um tópico específico</li>
    <li>Atualizar um tópico</li>
    <li>Eliminar um tópico</li>
</ol>

Em resumo, o objetivo principal do challenge é a criação de uma API REST que disponibilize um CRUD (CREATE, READ, UPDATE e DELETE), seguindo as melhores práticas e aplicando as regras de negócio estabelecidas.

#### Tecnologias e bibliotecas utilizadas

- java (JDK 25)
- Spring Boot 4.0.2 (Framework base para construção do projeto)
- Maven (gerenciador de depêndencias e automação de build)
- Spring Data JPA: Abstração para persistência de dados utilizando Hibernate.
- MySQL Connector/J: Driver de conexão com o banco de dados MySQL.
- Flyway Migration: Ferramenta para controle de versão e migração do esquema do banco de dados.
- Spring Security: Framework de segurança para autenticação e controle de acesso.
- Auth0 Java JWT: Biblioteca para geração e validação de tokens JSON Web Tokens.
- Lombok (getters, setters, construtores, etc.)
- Spring Validation: Implementação do Bean Validation para validação de dados de entrada.
- Spring Boot DevTools

### Como utilizar
 
 <ol>
    <li>Clone ou faça o download deste repositório;</li>
    <li>Crie um arquivo .env ou adicione as variáveis de ambiente listadas abaixo diretamente nas configurações do seu projeto alterando os valores de acordo com suas configurações.
 </ol><br/>
 
 ```txt
 DB_URL= adicione url do banco de dados
 DB_USER= usuario
 DB_PASSWORD= sua senha

 JWT_SECRET= valor do secret
 ```
 ### Rotas implentadas


 ```json
 // Exemplo de corpo de requisição "/login"
 //POST (http://localhost:8080/login)
 {
    "login": "emailcadastrado@gmail.com",
    "password": "coloqueSuaSenha"
 }
 ```
 ```json
 //Exemplo de corpo de requisição "/usuario"
 //POST (http://localhost:8080/usuario)
 {
	"name": "nome do usuario",
	"email": "emailcadastrado@gmail.com",
	"password": "coloqueSuaSenha"
}
 
 ```
 Todas as rotas, com excessão de "/login" e "/usuario" necessitam de autenticação para acesso. O token gerado e devolvido na requisição de login deve ser incluido no cabeçalho das outras requisições em Authorization.

 ```json
 // Exemplo corpo da requisição "/cursos"
 // http://localhost:8080/cursos
 //POST - Criar um curso
 {
	"name": "Chat GPT",
	"category": "IA"
}

// category aceitas: MOBILE,PROGRAMACAO,FRONTEND,DEVOPS,UX_DESIGNER,DATA_SCIENCE,INOVACAO_GESTAO e IA
 
 ```
 ```json
 // Exemplos do corpo de requisição "/topicos"
 // http://localhost:8080/topicos

 // POST - Criar tópico
 {
	"title": "Primeiro tópico criado",
	"message": "duvidas relacionada a determinado tópico que precisa de solução",
	"userId": 1,
	"courseId": 1
}

// PUT - Atualizar tópico
// único valor obrigatório para atualização é o id
{
"id": 2,
"title": "novo título",
"message": "duvida alteradda",
"status": "INATIVO"
}

// GET - Listar tópicos
// requisição sem corpo 

// DEL - Deletar tópico
// requisição sem corpo para http://localhost:8080/topicos/{id} onde i{id} é o valor do id do tópico que deseja excluir.
 ```
 
 
 
 
