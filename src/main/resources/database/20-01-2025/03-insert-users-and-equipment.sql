--liquibase formatted sql
--changeset gb:3

CREATE EXTENSION IF NOT EXISTS pgcrypto;


INSERT INTO users_equipment_rental (id, username, email)
VALUES (gen_random_uuid(), 'Grzegorz', 'gpol1@gmail.com');

INSERT INTO users_equipment_rental (id, username, email)
VALUES (gen_random_uuid(), 'Grzegorz2', 'gpol2@gmail.com');

INSERT INTO users_equipment_rental (id, username, email)
VALUES (gen_random_uuid(), 'Grzegorz3', 'gpol3@gmail.com');

INSERT INTO equipment (id, name, description, price)
VALUES (gen_random_uuid(), 'Eq1', 'Nice eq', 3.80);

INSERT INTO equipment (id, name, description, price)
VALUES (gen_random_uuid(), 'Eq2', 'Nice eq', 55.80);

INSERT INTO equipment (id, name, description, price)
VALUES (gen_random_uuid(), 'Eq3', 'Nice eq', 4.80);
