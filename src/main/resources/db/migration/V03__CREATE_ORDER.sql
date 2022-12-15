CREATE TABLE _order (
                        id varchar(255) PRIMARY KEY NOT NULL UNIQUE,
                        price DECIMAL(9,2) NOT NULL,
                        cpf varchar(14) NOT NULL,
                        quantity int NOT NULL,
                        code  varchar(255) NOT NULL UNIQUE,
                        coupon_id varchar(255) NOT NULL,
                        FOREIGN KEY (coupon_id) REFERENCES coupon(id)
);