insert into conta (nome, valor_inicial) values ('Banco do Brasil', 5000);
insert into conta (nome, valor_inicial) values ('SICREDI', 3000);
insert into conta (nome, valor_inicial) values ('NuBank', 150);

insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Transporte');

insert into usuario (nome, email, senha) values ('Jackson', 'pertusatti22@gmail.com', '123456');

INSERT INTO Transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Aluguel', '2023-03-20', -1500.00, 'SAIDA', 1, 1);
INSERT INTO Transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Gasolina', '2023-03-15', -300.00, 'SAIDA', 1, 1);
