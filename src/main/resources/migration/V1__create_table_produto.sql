CREATE TABLE produto (
    id bigint NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    descricao varchar(100) NOT NULL,
    preco double NOT NULL,
    categoria varchar(20) NOT NULL,
    PRIMARY KEY (id)
);