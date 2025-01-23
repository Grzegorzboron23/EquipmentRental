--liquibase formatted sql
--changeset gb:4

CREATE TABLE IF NOT EXISTS users_equipment(
    userId UUID NOT NULL,
    equipmentId UUID NOT NULL,
    PRIMARY KEY (userId, equipmentId),
    FOREIGN KEY (userId) REFERENCES  users_equipment_rental(id),
    FOREIGN KEY (equipmentId) REFERENCES equipment(id)
);

