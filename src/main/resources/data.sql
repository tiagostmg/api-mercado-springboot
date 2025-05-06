INSERT INTO client (name, balance) VALUES ('João Silva', 150.0);
INSERT INTO client (name, balance) VALUES ('Maria Oliveira', 200.0);

INSERT INTO product (name, price, quantity) VALUES ('Leite', 7.00, 10);
INSERT INTO product (name, price, quantity) VALUES ('Café', 15.00, 10);
INSERT INTO product (name, price, quantity) VALUES ('Água', 1.50, 10);

INSERT INTO purchase (client_id, product_id, quantity, date) VALUES (1, 1, 2, CURRENT_TIMESTAMP());
INSERT INTO purchase (client_id, product_id, quantity, date) VALUES (2, 3, 1, CURRENT_TIMESTAMP());