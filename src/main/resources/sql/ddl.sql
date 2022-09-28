CREATE OR REPLACE DATABASE belajar_crud;

USE belajar_crud;

CREATE OR REPLACE TABLE user (
    id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nama          VARCHAR(255) NOT NULL,
    tanggal_lahir DATE         NOT NULL
);