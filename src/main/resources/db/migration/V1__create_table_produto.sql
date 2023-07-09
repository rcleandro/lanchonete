create table produto
(
    id        bigint       not null auto_increment,
    nome      varchar(50)  not null,
    descricao varchar(300) not null,
    preco     double       not null,
    categoria varchar(20)  not null,
    primary key (id)
);