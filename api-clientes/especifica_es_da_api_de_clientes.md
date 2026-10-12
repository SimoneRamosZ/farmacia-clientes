# Especificação Técnica: API de Clientes (Farmácia)

## 1. Visão Geral do Projeto
* **Domínio:** Sistema de Gerenciamento de Farmácia (`db_farmacia`)
* **Módulo Atual:** Cadastro e Gerenciamento de Clientes (`api-clientes`)
* **Tecnologias Utilizadas:** 
  * Java 17
  * Spring Boot 3.x
  * Spring Data JPA / Hibernate
  * PostgreSQL 16
  * Lombok

---

## 2. Requisitos de Dados (Entidade Cliente)
A tabela `clientes` no PostgreSQL já está estruturada com os seguintes campos:
- `id`: Chave primária (Auto-incremento / `SERIAL`)
- `nome`: String obrigatória (`VARCHAR(150)`)
- `cpf`: String única e obrigatória (`VARCHAR(14)`)
- `telefone`: String opcional (`VARCHAR(20)`)
- `email`: String opcional (`VARCHAR(100)`)

---

## 3. Especificação dos Endpoints REST (Controller)
O objetivo do `ClienteController` é expor os seguintes endpoints HTTP para o front-end ou ferramentas de teste (como Postman/Insomnia):

### 3.1. Listar todos os clientes
* **Método HTTP:** `GET`
* **Rota:** `/clientes`
* **Resposta de Sucesso (200 OK):** Retorna uma lista JSON contendo todos os clientes cadastrados.

### 3.2. Buscar cliente por ID
* **Método HTTP:** `GET`
* **Rota:** `/clientes/{id}`
* **Parâmetros de Path:** `id` (Long)
* **Resposta de Sucesso (200 OK):** Retorna o objeto JSON do cliente correspondente.
* **Resposta de Erro (404 Not Found):** Caso o ID não exista.

### 3.3. Cadastrar novo cliente
* **Método HTTP:** `POST`
* **Rota:** `/clientes`
* **Corpo da Requisição (JSON):**
  ```json
  {
    "nome": "Nome do Cliente",
    "cpf": "111.222.333-44",
    "telefone": "(11) 99999-9999",
    "email": "cliente@email.com"
  }
  ```
* **Resposta de Sucesso (201 Created):** Retorna o objeto criado com o seu respectivo ID.

### 3.4. Atualizar cliente
* **Método HTTP:** `PUT`
* **Rota:** `/clientes/{id}`
* **Corpo da Requisição (JSON):** Dados atualizados do cliente.
* **Resposta de Sucesso (200 OK):** Retorna os dados atualizados.

### 3.5. Deletar cliente
* **Método HTTP:** `DELETE`
* **Rota:** `/clientes/{id}`
* **Resposta de Sucesso (204 No Content):** Cliente removido com sucesso.

---

## 4. Critérios de Aceite para o Kiro
- [ ] A aplicação deve iniciar corretamente conectando-se ao banco `db_farmacia` no PostgreSQL local.
- [ ] A camada de repositório (`ClienteRepository`) deve herdar de `JpaRepository<Cliente, Long>`.
- [ ] O controller deve mapear corretamente as requisições REST utilizando as anotações do Spring MVC (`@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, etc.).
- [ ] Validações básicas de negócio (como CPF único) devem retornar erros tratados adequadamente pela API.