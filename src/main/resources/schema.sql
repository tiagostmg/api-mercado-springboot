DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS product;

CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        balance DECIMAL(10, 2)
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         quantity INT NOT NULL
);

CREATE TABLE purchase (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          client_id BIGINT NOT NULL,
                          product_id BIGINT NOT NULL,
                          quantity INT NOT NULL,
                          date TIMESTAMP NOT NULL,
                          FOREIGN KEY (client_id) REFERENCES client(id),
                          FOREIGN KEY (product_id) REFERENCES product(id)
);