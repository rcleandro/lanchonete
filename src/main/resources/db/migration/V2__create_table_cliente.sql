create table cliente
(
    cpf   varchar(11) not null,
    nome  varchar(50) not null,
    email varchar(50) not null,
    primary key (cpf)
);

insert into cliente values('12345678900', 'Leandro Rodrigues', 'leandro@email.com');