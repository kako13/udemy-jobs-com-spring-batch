DROP TABLE PESSOA IF EXISTS;

CREATE TABLE PESSOA (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(500),
    email VARCHAR(500),
    data_nascimento VARCHAR(500),
    idade INT
    );