set foreign_key_checks = 0;

delete from transacao;
delete from conta;
delete from categoria;
delete from usuario;

set foreign_key_checks = 1;
    
alter table transacao auto_increment = 1;
alter table categoria auto_increment = 1;
alter table conta auto_increment = 1;
alter table usuario auto_increment = 1;
    
insert into conta (nome, valor_inicial, ativo) values ('Banco do Brasil', 5000,true);
insert into conta (nome, valor_inicial, ativo) values ('SICREDI', 3000, true);
insert into conta (nome, valor_inicial, ativo) values ('NuBank', 150, true);

insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Transporte');

insert into usuario (nome, email, senha, data_cadastro) values ('Jack', 'pertusatti22@gmail.com', '123456', utc_timestamp);

INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Aluguel', '2023-03-20', -1500.00, 'SAIDA', 1, 1);
INSERT INTO transacao (anotacao, data_transacao, valor, tipo_transacao, conta_id, categoria_id) VALUES ('Gasolina', '2023-03-15', -300.00, 'SAIDA', 1, 1);
