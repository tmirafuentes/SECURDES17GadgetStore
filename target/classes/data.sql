-- Insert the two roles --
INSERT INTO role (id, name)
VALUES (1, "ROLE_USER"),
(2, "ROLE_ADMIN");

-- Insert the default brands --
INSERT INTO brand (brand_id, brand_name)
VALUES (1, "Apple"), (2, "Asus"), (3, "Acer"), (4, "HP"), (5, "Lenovo"), (6, "Samsung");

-- Insert the default types --
INSERT INTO type (type_id, type_name)
VALUES (1, "Desktop"), (2, "Laptop"), (3, "Mobile"), (4, "Tablet");