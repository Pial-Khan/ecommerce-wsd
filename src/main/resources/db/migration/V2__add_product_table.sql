CREATE TABLE public.product_list
(
    id             bigserial    NOT NULL,
    product_name   varchar(150) NOT NULL,
    initial_amount int8         NOT NULL,
    price          int8         NOT NULL,
    CONSTRAINT product_list_pkey PRIMARY KEY (id)
);

INSERT INTO public.product_list (product_name, initial_amount, price)
VALUES ('Laptop', 50, 700),
       ('Smartphone', 200, 300),
       ('Tablet', 100, 250),
       ('Monitor', 75, 150),
       ('Keyboard', 150, 25),
       ('Mouse', 300, 20),
       ('Printer', 60, 100),
       ('Desk Chair', 40, 80),
       ('Desk', 30, 120),
       ('Headphones', 200, 60);
