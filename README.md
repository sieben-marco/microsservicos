# Trabalho Final - Programação para Web II

**Aluno:** Marco Sieben

### Objetivo
Implementação de dois microsserviços em **Quarkus** que se comunicam via **REST Client**:

- `products` → serviço de produtos (porta 8080)  
- `orders` → serviço de pedidos (porta 8081) que consome o serviço de produtos

### Tecnologias
- Quarkus 3.29.3 + Java 21
- Quarkus REST
- REST Jackson
- REST Client Jackson
- Hibernate ORM with Panache
- H2 Database (arquivo local)
- Maven

# Como Executar

## Serviço de Produtos
```bash
cd products
./mvnw quarkus:dev
# ou
quarkus dev
```

## Serviço de Pedidos
```bash
cd orders
./mvnw quarkus:dev
# ou
quarkus dev
```

# Bancos de Dados (H2)
- Produtos: `./products/products.mv.db`
- Pedidos: `./orders/orders.mv.db`

# Referências
- [Writing REST Services with Quarkus REST (formerly RESTEasy Reactive)](https://quarkus.io/guides/rest)
- [Writing JSON REST Services](https://quarkus.io/guides/rest-json)
- [Configure data sources in Quarkus](https://quarkus.io/guides/datasource)
- [Generating Jakarta REST resources with Panache](https://quarkus.io/guides/rest-data-panache)
- [Using the REST Client](https://quarkus.io/guides/rest-client)
- [Simplified Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache)
- [All configuration options (Quarkus)](https://quarkus.io/guides/all-config)
- [@ApplicationScoped and @Singleton look very similar. Which one should I choose for my Quarkus application?](https://quarkus.io/guides/cdi#applicationscoped-and-singleton-look-very-similar-which-one-should-i-choose-for-my-quarkus-application)
- [Programação para Web II (Material das aulas)](https://pw2.rpmhub.dev/)
- [H2 Database Engine](https://www.h2database.com/html/main.html)
- [RFC 9110 HTTP Semantics](https://www.rfc-editor.org/rfc/rfc9110.html)
- [HTTP Semantics](https://datatracker.ietf.org/doc/html/rfc9110)
- [HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status)
- [REST response code for invalid data](https://stackoverflow.com/questions/6123425/rest-response-code-for-invalid-data)