CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL
);

CREATE TABLE conta (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    valor_inicial NUMERIC(19,2),
    ativo BOOLEAN NOT NULL
);

CREATE TABLE transacao (
    id BIGSERIAL PRIMARY KEY,
    anotacao VARCHAR(255) NOT NULL,
    data_transacao TIMESTAMP NOT NULL,
    valor NUMERIC(19,2) NOT NULL,
    tipo_transacao VARCHAR(20) NOT NULL,
    conta_id BIGINT REFERENCES conta(id),
    categoria_id BIGINT REFERENCES categoria(id)
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL
);
