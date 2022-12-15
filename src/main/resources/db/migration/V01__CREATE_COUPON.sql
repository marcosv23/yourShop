CREATE TABLE coupon (
                        id varchar(255) NOT NULL PRIMARY KEY UNIQUE,
                        percentage DECIMAL(9, 2) NOT NULL,
                        name varchar(255) NOT NULL UNIQUE,
                        expiration_date DATETIME NOT NULL
);