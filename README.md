# Product and Category Service

Serviço para consulta de produtos e categorias implementados de acordo com o do desafio de backend proposto pela Juno.

### Considerações

- A dependência ```spring-data-rest``` do projeto original foi removida a fim de evitar que o framework implementasse automaticamente os métodos REST. Foi utilizado em favor a dependência ```spring-boot-starter-web```, permitindo a customização dos serviços de acordo com os requisitos.

### Requisitos do desafio

https://recruta.juno.com.br/ | opção backend

### Motivações

- Ilustrar algumas competências como desenvolvedor de software, como boas práticas de OOP, utilização de testes, cobertura de código, SQL, e também se divertir um pouco :)

## API endpoints

- Product
  - http://{host}/v1/products/
  - http://{host}/v1/products/{id}
  - http://{host}/v1/products/{id}/categories
  - http://{host}/v1/products/category/{id}
  - http://{host}/v1/products/pattern/{pattern}

- Category
  - http://{host}/v1/categories/
  - http://{host}/v1/categories/{id}
  - http://{host}/v1/categories/{id}/products
  - http://{host}/v1/categories/product/{id}
  - http://{host}/v1/categories/pattern/{pattern}

## Swagger API docs

- http://{host}/api/v2/api-docs
- http://{host}/api/swagger-ui.html

## Testes

- Execução de testes unitários

    ```
    mvn clean test
    ```

- Execução de testes de integração

    ```
    mvn clean test -P integration-tests
    ```

## Diagrama entidade-relacionamento

![Diagrama entidade-relacionamento](ERD.png)


&#35;tamojuno
