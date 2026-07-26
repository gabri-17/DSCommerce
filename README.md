# DSCommerce

Backend de um sistema de e-commerce desenvolvido em Java com o framework Spring Boot.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **H2 Database (Ambiente de Teste/Desenvolvimento)**
- **JPA / Hibernate**

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-seu-repositorio>
    ```

2.  **Abra o projeto:**
    - Abra o projeto na sua IDE de preferência (IntelliJ IDEA, Eclipse, VS Code com o Spring Boot Extension Pack).
    - A IDE deverá reconhecer o projeto como um projeto Maven e baixar as dependências necessárias.

3.  **Execute a aplicação:**
    - Encontre a classe principal `DscommerceApplication.java` (localizada em `src/main/java/com/devsuperior/dscommerce/`).
    - Execute o método `main` para iniciar o servidor web embutido.

4.  **Acesse a aplicação:**
    - A aplicação estará rodando e pronta para receber requisições em `http://localhost:8080`.

## Exemplo de Endpoint

- `GET /orders/{id}`: Busca os detalhes de um pedido específico pelo seu ID.
  - **Exemplo de requisição:** `http://localhost:8080/orders/1`
  - **Exemplo de resposta:**
  <code>
  {
      "id": 1,
      "moment": "2022-07-25T13:00:00Z",
      "status": "PAID",
      "client": {
          "id": 1,
          "name": "Maria Brown"
      },
      "payment": {
          "id": 1,
          "moment": "2022-07-25T15:00:00Z"
      },
      "items": [
          {
              "productId": 1,
              "name": "The Lord of the Rings",
              "price": 90.5,
              "quantity": 2,
              "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg",
              "subTotal": 181.0
          },
          {
              "productId": 3,
              "name": "Macbook Pro",
              "price": 1250.0,
              "quantity": 1,
              "imgUrl": "https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/3-big.jpg",
              "subTotal": 1250.0
          }
      ],
      "total": 1431.0
  }
  </code>





