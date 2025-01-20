--liquibase formatted sql
--changeset gb:1

CREATE TABLE users_equipment_rental(
    id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);