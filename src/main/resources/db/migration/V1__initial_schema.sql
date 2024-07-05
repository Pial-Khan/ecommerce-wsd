CREATE TABLE public.customer
(
    id         bigserial    NOT NULL,
    username   varchar(150) NOT NULL,
    email      varchar      NOT NULL,
    first_name text NULL,
    last_name  text NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

INSERT INTO public.customer (username, email, first_name, last_name)
VALUES ('johndoe', 'johndoe@example.com', 'John', 'Doe'),
       ('janedoe', 'janedoe@example.com', 'Jane', 'Doe'),
       ('alice', 'alice@example.com', 'Alice', 'Smith'),
       ('bob', 'bob@example.com', 'Bob', 'Johnson'),
       ('charlie', 'charlie@example.com', 'Charlie', 'Brown'),
       ('david', 'david@example.com', 'David', 'Williams'),
       ('eve', 'eve@example.com', 'Eve', 'Davis'),
       ('frank', 'frank@example.com', 'Frank', 'Miller'),
       ('grace', 'grace@example.com', 'Grace', 'Wilson'),
       ('henry', 'henry@example.com', 'Henry', 'Moore');