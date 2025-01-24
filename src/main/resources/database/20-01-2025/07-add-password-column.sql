--liquibase formatted sql
--changeset gb:7

ALTER TABLE users_equipment_rental
ADD COLUMN password VARCHAR(100) NOT NULL DEFAULT '$2a$10$Hq79o7FP/zYdgdTnRPyR3uRjYWdBk0NYE6BdW6E9jNV.QOHlwI6Zm';
