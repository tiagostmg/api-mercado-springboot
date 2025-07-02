# 🛒 Mercado API

Uma API RESTful para gerenciamento de clientes, produtos e compras em um sistema de mercado, desenvolvida com Spring Boot.

## 🚀 Como Executar
✅ Pré-requisitos

    Java 17+

    Maven 3.8+

    Git

⚙️ Configuração

  Clone o repositório:

```
git clone https://github.com/tiagostmg/api-mercado-springboot.git

api-mercado-springboot
```

## ▶️ Executando a Aplicação

Para rodar:

    mvn spring-boot:run
    
### 🐳 Executando com Docker

Você também pode executar a aplicação usando Docker:

```bash
docker pull tiagostmg/api-mercado
docker run -p 8080:8080 tiagostmg/api-mercado
```

#

### 🌐 Documentação da API

Ao rodar a aplicação, acesse a interface Swagger UI:

    http://localhost:8080/swagger-ui.html

## 📌 Tecnologias Utilizadas

    Java 17

    Spring Boot

    Spring Data JPA

    Lombok

    SpringDoc OpenAPI (Swagger)

    H2 Database 

### 🗃️ Estrutura do Banco de Dados

|Tabela|Descrição|Principais Campos|
| --------- | --------------------------------------------- | ------------------------------------------------------ |
| `cliente` | Armazena informações dos clientes do mercado  | `id`, `nome`, `saldo`|
| `produto` | Armazena produtos disponíveis para venda      | `id`, `nome`, `preco`, `quantidade`            |
| `compra`  | Registra as compras realizadas pelos clientes | `id`, `cliente_id`, `produto_id`, `quantidade`, `data` |


### ✉️ Contato

- Email - tiago.taumaturgo@gmail.com
- GitHub – https://github.com/tiagostmg
