create table produto
(
    id        bigint       not null auto_increment,
    nome      varchar(50)  not null,
    descricao varchar(300) not null,
    preco     double       not null,
    categoria varchar(20)  not null,
    primary key (id)
);

insert into produto values(1, 'X-Burguer', 'Pão, carne, queijo', 9.99, 'LANCHE');
insert into produto values(2, 'Coca-Cola', 'Coca-Cola garrafa 600ml', 6.99, 'BEBIDA');