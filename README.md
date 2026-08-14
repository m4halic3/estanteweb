# 📚 EstanteWeb

> Sistema web para um sebo de bairro — desenvolvido como projeto contínuo da disciplina **Programação para Web 3** (IFSP), sob orientação do Prof. Me. Danilo Bueno.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-MVC-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/build-Maven-blue?logo=apachemaven)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

---

## 📖 Sobre o projeto

A **EstanteWeb** é um sebo de bairro que vende livros usados. Até então, o acervo inteiro vivia em um caderno físico — cada consulta de um cliente exigia folhear páginas à mão. Este projeto leva a loja para a Web, construindo o sistema **desde o início de forma organizada**, aplicando o padrão arquitetural **MVC (Model-View-Controller)** com **Spring Boot**.

O projeto é construído **de forma incremental ao longo do semestre**: cada etapa (aula/missão) adiciona uma nova camada ou funcionalidade sobre a anterior, sem quebrar o que já existe.

## 🏗️ Arquitetura

O projeto segue o padrão **MVC**, com separação clara de responsabilidades:

```
Requisição HTTP → Controller → Repository → Model
                       ↓
                    Resposta
```

- **Model** (`Livro`): representa os dados do domínio (título, autor, ano, preço). Não possui nenhuma anotação HTTP/Spring — não sabe (e não deve saber) que existe uma Web por trás dele.
- **Controller** (`PaginaController`, `LivroController`): recebe requisições HTTP, mapeia rotas e delega o acesso aos dados ao Repository.
- **Repository** (`LivroRepository`): concentra o acesso e a manipulação dos dados do acervo, isolando essa lógica do Controller.

## 🛠️ Tecnologias

| Tecnologia | Versão / Detalhe |
|---|---|
| Java | 21 (Eclipse Temurin) |
| Spring Boot | versão estável mais recente |
| Spring Web | dependência principal |
| Maven | gerenciador de build (via Maven Wrapper `mvnw`) |
| Group / Artifact | `br.com.estanteweb` |

## ✅ Pré-requisitos

- [JDK 21](https://adoptium.net/) instalado e configurado no `PATH`
- [VS Code](https://code.visualstudio.com/) com a extensão **Extension Pack for Java**
- Git

Verifique o ambiente antes de rodar o projeto:

```bash
java -version     # deve exibir versão 21
code --version    # confirma o VS Code no PATH
```

> O Maven não precisa estar instalado globalmente — o projeto já traz o Maven Wrapper (`mvnw`).

## 🚀 Como executar

```bash
# clone o repositório
git clone https://github.com/<seu-usuario>/estanteweb.git
cd estanteweb

# execute a aplicação
./mvnw spring-boot:run
```

A aplicação sobe por padrão em `http://localhost:8080`.

## 🔌 Endpoints disponíveis

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/` | Mensagem de boas-vindas da loja |
| `GET` | `/sobre` | Descrição da EstanteWeb |
| `GET` | `/livros` | Lista o acervo (título, autor e preço) |
| `GET` | `/livros/total` | Retorna a quantidade de livros no acervo |
| `GET` | `/livros/html` | *(desafio)* Acervo renderizado como lista HTML |

## 📁 Estrutura do projeto

```
estanteweb/
├── src/
│   └── main/
│       ├── java/br/com/estanteweb/
│       │   ├── EstantewebApplication.java
│       │   ├── controller/
│       │   │   ├── PaginaController.java
│       │   │   └── LivroController.java
│       │   ├── model/
│       │   │   └── Livro.java
│       │   └── repository/
│       │       └── LivroRepository.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

## 🗺️ Roadmap do semestre

O projeto evolui aula a aula. Este checklist é atualizado conforme novas camadas e funcionalidades são incorporadas.

- [x] **Aula 1 — Servidor no ar**: projeto gerado via Spring Initializr, servidor Spring Boot rodando
- [x] **Aula 2 — Primeiras rotas**: `PaginaController` com rotas `/` e `/sobre`
- [x] **Aula 3 — Modelo do acervo**: classe `Livro` e rota `/livros` com listagem
- [x] **Aula 4 — Separação em camadas**: `LivroRepository` isolando os dados do controller
- [ ] *(próximas entregas serão adicionadas aqui conforme o semestre avança)*

## 🎓 Contexto acadêmico

- **Instituição:** IFSP
- **Disciplina:** Programação para Web 3
- **Professor:** Me. Danilo Bueno
- **Modalidade:** trabalho em dupla, com entregas individuais no Moodle

## 👥 Autoria

Desenvolvido por Mariana Alice Pires Leite

## 📄 Licença

Projeto acadêmico, desenvolvido para fins didáticos.