CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        balance DECIMAL(10, 2)
);

CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         quantity INT NOT NULL
);

CREATE TABLE purchase (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          client_id INT NOT NULL,
                          product_id INT NOT NULL,
                          quantity INT NOT NULL,
                          purchase_date TIMESTAMP NOT NULL,
                          FOREIGN KEY (client_id) REFERENCES client(id),
                          FOREIGN KEY (product_id) REFERENCES product(id)
);