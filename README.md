# EstanteWeb

Sistema web para um sebo de bairro, desenvolvido como projeto contínuo da disciplina **Programação para Web 3** (IFSP), com orientação do Prof. Me. Danilo Bueno.

## Sobre o projeto

A EstanteWeb é um sebo que vende livros usados e mantinha todo o acervo anotado em um caderno. A ideia do projeto é levar a loja para a Web usando Spring Boot e o padrão MVC.

O sistema é construído aos poucos ao longo do semestre: cada aula ou missão acrescenta uma funcionalidade nova em cima do que já existe.

## Arquitetura

```
Requisição HTTP → Controller → Repository → Banco (MySQL)
                       ↓
              Template (FreeMarker) / resposta
```

- **Model** (`Livro`, `Sugestao`): classes com os dados do domínio.
- **Controller** (`PaginaController`, `LivroController`, `SugestaoController`, `ReservaController`): recebem as requisições e devolvem uma página ou um texto.
- **Repository** (`LivroRepository`, `SugestaoRepository`): fazem as consultas no banco com `JdbcTemplate`.
- **Templates** (`src/main/resources/templates`): páginas em FreeMarker (`.ftlh`).

As tabelas são criadas pelo Flyway, a partir dos scripts em `src/main/resources/db/migration`. O script `V3` já insere alguns livros no acervo.

## Tecnologias

- Java 21
- Spring Boot 4 (Web, Data JPA, DevTools)
- FreeMarker
- MySQL 8 (rodando no Docker)
- Flyway
- Maven (via Maven Wrapper)

## Pré-requisitos

- JDK 21
- Docker e Docker Compose
- Git

O Maven não precisa estar instalado, o projeto usa o `mvnw`.

## Banco de dados com Docker

O MySQL roda em um container definido no `docker-compose.yml`. A porta do container (3306) foi mapeada para a **3307** da máquina, para não conflitar com um MySQL que já esteja instalado localmente.

1. Crie um arquivo `.env` na raiz do projeto (ele não vai para o repositório):

   ```env
   DB_PASSWORD=sua_senha
   DB_NAME=estanteweb
   ```

2. O volume dos dados está marcado como `external`, então ele precisa existir antes de subir o container. Se for a primeira vez, crie com o mesmo nome que está no `docker-compose.yml`:

   ```bash
   docker volume create e328ee779f37b10dcfec85fc5bfed7e91ce4e0d6db675e7b42638573c1d33954
   ```

3. Suba o banco:

   ```bash
   docker compose up -d
   ```

Para parar o container: `docker compose down` (os dados continuam salvos no volume).

A conexão da aplicação fica em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/estanteweb
spring.datasource.username=root
spring.datasource.password=sua_senha
```

A senha precisa ser a mesma do `DB_PASSWORD` no `.env`.

## Como executar

```bash
git clone https://github.com/<seu-usuario>/estanteweb.git
cd estanteweb

docker compose up -d
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. Na primeira execução o Flyway cria as tabelas automaticamente.

## Rotas

| Método | Rota | Descrição |
|---|---|---|
| GET | `/` | Mensagem de boas-vindas |
| GET | `/sobre` | Descrição da loja |
| GET | `/contato` | Formulário de contato |
| POST | `/contato` | Envia o formulário de contato |
| GET | `/contato/sucesso` | Confirmação do contato |
| GET | `/livros` | Lista o acervo em texto |
| GET | `/livros/html` | Lista o acervo em página HTML (aceita `?ordem=titulo`, `autor`, `ano` ou `preco`) |
| GET | `/livros/total` | Quantidade de livros no acervo |
| GET | `/livros/valor-total` | Soma dos preços do acervo |
| GET | `/livros/busca` | Livros até um preço (`?precoMaximo=30`) |
| GET | `/livros/novo` | Formulário de cadastro de livro |
| POST | `/livros` | Cadastra um livro |
| GET | `/livros/{id}` | Detalhes de um livro e outros do mesmo autor |
| GET | `/livros/quem-acessa` | Mostra o User-Agent de quem acessou |
| GET | `/sugestoes/nova` | Formulário de sugestão de livro |
| POST | `/sugestoes` | Salva uma sugestão |
| GET | `/sugestoes` | Lista as sugestões recebidas |
| POST | `/reserva` | Recebe uma reserva (nome e telefone) |
| GET | `/ola` | Página de teste do FreeMarker |

## Estrutura

```
estanteweb/
├── src/main/
│   ├── java/br/com/estanteweb/
│   │   ├── EstantewebApplication.java
│   │   ├── Livro.java
│   │   ├── LivroController.java
│   │   ├── LivroRepository.java
│   │   ├── Sugestao.java
│   │   ├── SugestaoController.java
│   │   ├── SugestaoRepository.java
│   │   ├── PaginaController.java
│   │   ├── ReservaController.java
│   │   └── TesteController.java
│   └── resources/
│       ├── application.properties
│       ├── db/migration/
│       └── templates/
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Autoria

Desenvolvido por Mariana Alice Pires Leite.

Projeto acadêmico, feito para fins didáticos.
