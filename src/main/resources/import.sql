insert into conta (nome, valor_inicial) values ('Banco do Brasil', 5000);
insert into conta (nome, valor_inicial) values ('SICREDI', 3000);
insert into conta (nome, valor_inicial) values ('NuBank', 150);

insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Transporte');

insert into transacao (anotacao, valor, categoria_id, conta_id) values('Almoço', -50.0, 1,1);

insert into usuario (nome, email, senha) values ('Jackson', 'pertusatti22@gmail.com', '123456');
