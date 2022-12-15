CREATE TABLE product (
                         id varchar(255) NOT NULL PRIMARY KEY UNIQUE,
                         description varchar(255) NOT NULL,
                         heigth decimal(5, 3) NOT NULL,
                         depth_ decimal(5, 3) NOT NULL,
                         weight decimal(5, 3) NOT NULL,
                         price decimal (9, 2) NOT NULL
);
