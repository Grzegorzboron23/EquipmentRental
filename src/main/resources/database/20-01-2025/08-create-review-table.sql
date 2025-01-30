--liquibase formatted sql
--changeset gb:8

CREATE TABLE IF NOT EXISTS reviews(
     id UUID PRIMARY KEY,
     rating INT CHECK (rating BETWEEN 1 AND 5),
     comment VARCHAR(255),
     userId UUID NOT NULL,
     equipmentId UUID NOT NULL,
     FOREIGN KEY (userId) REFERENCES  users_equipment_rental(id) ON DELETE CASCADE,
     FOREIGN KEY (equipmentId) REFERENCES equipment(id) ON DELETE CASCADE
);
