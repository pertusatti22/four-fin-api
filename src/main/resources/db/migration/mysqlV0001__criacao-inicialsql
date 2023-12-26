create table categoria(
    id bigint not null auto_increment,
    nome varchar(60) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

create table conta(
    id bigint not null auto_increment,
    nome varchar(60) not null,
    valor_inicial decimal(19,2),
    ativo tinyint(1) not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;

create table transacao(
    id bigint not null auto_increment,
    anotacao varchar(255) not null,
    data_transacao datetime not null,
    valor decimal(19,2) not null,
    tipo_transacao varchar(20) not null,
    conta_id bigint,
    categoria_id bigint,   
    
    primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario(
    id bigint not null auto_increment,
    nome varchar(255),
    email varchar(255) not null,
    senha varchar(255) not null,
    data_cadastro datetime not null,
    
    primary key (id)
) engine=InnoDB default charset=utf8;