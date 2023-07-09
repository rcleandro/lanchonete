create table pedido
(
    id                bigint      not null auto_increment,
    cliente_cpf       bigint,
    lanche_id         bigint,
    acompanhamento_id bigint,
    bebida_id         bigint,
    progresso         varchar(20) not null,
    data              datetime    not null,
    primary key (id),
    foreign key (cliente_cpf) references cliente (cpf),
    foreign key (lanche_id) references produto (id),
    foreign key (acompanhamento_id) references produto (id),
    foreign key (bebida_id) references produto (id)
);

insert into pedido values(1, '12345678900', 1, null, 2, 'RECEBIDO', '2023-07-08T22:43:27.257867');
