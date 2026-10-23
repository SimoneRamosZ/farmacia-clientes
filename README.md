# 💊 Farmácia — Gestão de Clientes

Sistema simples de cadastro de clientes para farmácia, composto por uma API REST em Spring Boot e um frontend em HTML puro.

---

## 📁 Estrutura do projeto

```
farmacia-clientes/
├── api-clientes/      # Backend — Spring Boot + PostgreSQL
└── frontend/
    └── index.html     # Frontend — HTML + CSS + JS (sem dependências)
```

---

## 🚀 Tecnologias

| Camada    | Tecnologia                          |
|-----------|-------------------------------------|
| Backend   | Java 17 · Spring Boot 3.3 · JPA / Hibernate |
| Banco     | PostgreSQL                          |
| Build     | Gradle                              |
| Frontend  | HTML · CSS · JavaScript (vanilla)   |

---

## ⚙️ Pré-requisitos

- **Java 17+**
- **PostgreSQL** rodando localmente na porta `5432`
- Banco de dados `db_farmacia` criado

```sql
CREATE DATABASE db_farmacia;
```

---

## 🔧 Configuração do backend

As configurações ficam em `api-clientes/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db_farmacia
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Ajuste usuário e senha conforme seu ambiente.

---

## ▶️ Como rodar

### 1. Subir o backend

```bash
cd api-clientes
./gradlew bootRun
```

A API ficará disponível em **`http://localhost:8080`**.

### 2. Abrir o frontend

Basta abrir o arquivo diretamente no navegador:

```bash
open frontend/index.html
```

Ou arraste o arquivo `frontend/index.html` para qualquer navegador. Nenhum servidor web é necessário.

---

## 📡 Endpoints da API

Base URL: `http://localhost:8080`

| Método   | Rota              | Descrição                    | Status de sucesso |
|----------|-------------------|------------------------------|-------------------|
| `GET`    | `/clientes`       | Lista todos os clientes      | `200 OK`          |
| `GET`    | `/clientes/{id}`  | Busca cliente por ID         | `200 OK`          |
| `POST`   | `/clientes`       | Cadastra um novo cliente     | `201 Created`     |
| `PUT`    | `/clientes/{id}`  | Atualiza um cliente          | `200 OK`          |
| `DELETE` | `/clientes/{id}`  | Remove um cliente            | `204 No Content`  |

### Exemplo de payload

```json
{
  "nome": "Maria Silva",
  "cpf": "123.456.789-00",
  "telefone": "(11) 99999-9999",
  "email": "maria@email.com"
}
```

### Formato de erro

Todas as respostas de erro seguem o mesmo padrão:

```json
{
  "timestamp": "2026-10-23T10:30:00.123",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Cliente não encontrado com o ID: 99"
}
```

---

## 🗃️ Modelo de dados

| Campo      | Tipo     | Obrigatório | Observações          |
|------------|----------|-------------|----------------------|
| `id`       | Long     | Auto        | Gerado pelo banco    |
| `nome`     | String   | ✅ Sim      | Até 150 caracteres   |
| `cpf`      | String   | ✅ Sim      | Único, até 14 chars  |
| `telefone` | String   | ❌ Não      | Até 20 caracteres    |
| `email`    | String   | ❌ Não      | Até 100 caracteres   |

---

## 🖥️ Frontend

Interface simples e funcional que permite:

- **Cadastrar** novos clientes via formulário
- **Visualizar** todos os clientes em tabela
- **Editar** um cliente clicando em "Editar" na linha
- **Excluir** um cliente com confirmação

> Não depende de nenhum framework ou biblioteca externa. Funciona abrindo o `index.html` diretamente no navegador.

---

## 📄 Licença

Distribuído sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
