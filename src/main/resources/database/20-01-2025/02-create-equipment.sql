--liquibase formatted sql
--changeset gb:2


CREATE TABLE equipment(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

