# 🍰 Confectionery API

Uma API REST desenvolvida com **Spring Boot** para gerenciar operações de confeitaria.

---

## 📋 Sobre o Projeto

Este projeto está em desenvolvimento e segue uma abordagem **iterativa**, adicionando camadas e funcionalidades conforme a necessidade surge.

O objetivo é construir uma API escalável, organizada e seguindo boas práticas do ecossistema Spring.

---

## 🛠️ Tecnologias Utilizadas

- **Java 25**
- **Spring Boot**
- **Spring Web**
- **Maven (Wrapper)**
- **Arquitetura em Camadas**

---

## 📁 Estrutura do Projeto

```text
confectionery-api/
├── src/
│   ├── main/
│   │   ├── java/com/taynahamaral/confectionery/
│   │   │   ├── ConfectioneryApiApplication.java
│   │   │   └── controller/
│   │   │       └── HomeController.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/com/taynahamaral/confectionery/
│
├── pom.xml
└── README.md
```

---

## 🚀 Como Executar

### 📌 Pré-requisitos

Verifique se o Maven Wrapper está funcionando:

```bash
./mvnw --version
```

Exemplo de saída:

```text
Apache Maven 3.9.12
Java version: 25.0.2
OS: Windows 11
```

---

### ▶️ Executando a Aplicação

1. Clone o repositório:

   ```bash
   git clone https://github.com/LeoAnDev/confectionery-api.git
   ```

1. Navegue até a pasta do projeto:

   ```bash
   cd confectionery-api
   ```

1. Execute a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

1. A aplicação estará disponível em:

   ```text
   http://localhost:8080
   ```

---

## 🌐 Endpoints Disponíveis

| Método | Endpoint  | Descrição                           |
| ------ | --------- | ----------------------------------- |
| GET    | `/`       | Retorna a página inicial da API     |
| GET    | `/teste`  | Retorna a página de teste da API    |

---

## 🔄 Roadmap do Projeto

As seguintes camadas serão adicionadas progressivamente:

- [ ] Camada de Serviço (`service`)
- [ ] Camada de Repositório (`repository`)
- [ ] Camada de Entidades (`entity`)
- [ ] DTOs (`dto`)
- [ ] Tratamento Global de Exceções (`exception`)
- [ ] Integração com Banco de Dados (PostgreSQL)
- [ ] Autenticação com Spring Security + JWT
- [ ] Documentação com Swagger/OpenAPI

---

## 👨‍💻 Desenvolvedor

**Leonardo De Andrade Oliveira**  
GitHub: https://github.com/LeoAnDev

---

## 📄 Licença

Este projeto está em desenvolvimento.
