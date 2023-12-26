-- Desativa a verificação de chave estrangeira temporariamente
SET session_replication_role = 'replica';

-- Limpa os dados das tabelas
DELETE FROM transacao;
DELETE FROM conta;
DELETE FROM categoria;
DELETE FROM usuario;

-- Ativa a verificação de chave estrangeira
SET session_replication_role = 'origin';

-- Reseta os contadores auto-incrementáveis
SELECT setval('transacao_id_seq', 1, false);
SELECT setval('categoria_id_seq', 1, false);
SELECT setval('conta_id_seq', 1, false);
SELECT setval('usuario_id_seq', 1, false);

-- Insere dados nas tabelas
INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('Banco do Brasil', 5000, true);
INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('SICREDI', 3000, true);
INSERT INTO conta (nome, valor_inicial, ativo) VALUES ('NuBank', 150, true);

INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Transporte');

INSERT INTO usuario (nome, email, senha, data_cadastro) VALUES ('Jack', 'pertusatti22@gmail.com', '123456', current_timestamp);

INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Aluguel', '2023-03-20', -1500.00, 'SAIDA', 1, 1);
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Gasolina', '2023-03-15', -300.00, 'SAIDA', 1, 1);
