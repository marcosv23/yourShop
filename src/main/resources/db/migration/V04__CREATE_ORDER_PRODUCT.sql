CREATE TABLE order_product (
                               order_id varchar(255) NOT NULL UNIQUE,
                               product_id varchar(255) NOT NULL UNIQUE,
                               PRIMARY KEY (order_id, product_id),
                               FOREIGN KEY (product_id) REFERENCES product(id),
                               FOREIGN KEY (order_id) REFERENCES _order(id)
);