alter table transacao disable trigger all;
alter table conta disable trigger all;
alter table categoria disable trigger all;
alter table usuario disable trigger all;

DELETE FROM transacao;
DELETE FROM conta;
DELETE FROM categoria;
DELETE FROM usuario;

alter sequence transacao_id_seq restart with 1;
alter sequence conta_id_seq restart with 1;
alter sequence categoria_id_seq restart with 1;
alter sequence usuario_id_seq restart with 1;

INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('Banco do Brasil', 5000, true);
INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('SICREDI', 3000, true);
INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('NuBank', 1500, true);

INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Transporte');

INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('Jack', 'pertusatti22@gmail.com', '123456', current_timestamp);
    
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Aluguel', current_timestamp, -1500.00, 'SAIDA', 1, 1);
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Gasolina', current_timestamp, -300.00, 'SAIDA', 1, 1);
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Comida', current_timestamp, -1500.00, 'SAIDA', 2, 2);
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Comida', current_timestamp, -300.00, 'SAIDA', 2, 2);