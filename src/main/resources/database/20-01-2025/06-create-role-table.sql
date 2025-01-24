--liquibase formatted sql
--changeset gb:6


ALTER TABLE users_equipment_rental
ADD COLUMN role VARCHAR(50) NOT NULL DEFAULT 'USER'