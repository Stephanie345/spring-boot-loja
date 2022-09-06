drop schema if exists db_loja;
create schema db_loja;

use db_loja;

drop user if exists 'stephanie'@'localhost';

create user stephanie@localhost identified by 'stephanie123@';

grant select, insert, delete, update on db_loja.* to stephanie@localhost;


CREATE TABLE IF NOT EXISTS db_loja.clientes (
  id_cliente BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  cpf VARCHAR(11) NOT NULL unique,
  telefone VARCHAR(15) NULL,
  PRIMARY KEY (id_cliente));
  
  CREATE TABLE IF NOT EXISTS db_loja.vendas (
  id_venda BIGINT NOT NULL AUTO_INCREMENT,
  data_venda DATE NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  id_cliente BIGINT NOT NULL,
  PRIMARY KEY (id_venda),
  INDEX fk_vendas_clientes_id_cliente (id_cliente ASC),
  CONSTRAINT fk_vendas_clientes
    FOREIGN KEY (id_cliente)
    REFERENCES db_loja.clientes (id_cliente)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS db_loja.produtos (
  id_produto BIGINT NOT NULL AUTO_INCREMENT,
  cod_produto BIGINT NOT NULL unique,
  descricao VARCHAR(120) NOT NULL,
  preco DECIMAL(8,2) NOT NULL,
  quant_estoque DECIMAL(4,0) NOT NULL,
  PRIMARY KEY (id_produto));
  
CREATE TABLE IF NOT EXISTS db_loja.itens_vendas (
  id_venda BIGINT NOT NULL,
  id_produto BIGINT NOT NULL,
  INDEX fk_vendas_has_produtos_produtos1_id_produto (id_produto ASC),
  INDEX fk_vendas_has_produtos_vendas1_id_venda (id_venda ASC),
  CONSTRAINT fk_vendas_has_produtos_vendas1
    FOREIGN KEY (id_venda)
    REFERENCES db_loja.vendas (id_venda)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_vendas_has_produtos_produtos1
    FOREIGN KEY (id_produto)
    REFERENCES db_loja.produtos (id_produto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE IF NOT EXISTS db_loja.usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

CREATE TABLE IF NOT EXISTS db_loja.aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

CREATE TABLE IF NOT EXISTS db_loja.uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);


insert into clientes(nome,cpf,telefone) values ('Sofia','1245678909','1234567890');
insert into vendas(data_venda,valor,id_cliente) values ('2021-03-29','2.5','1');
insert into produtos(descricao,preco,quant_estoque,cod_produto) values ('linha','2.5','10','1');
insert into itens_vendas values(1,1);
insert into usr_usuario (usr_nome, usr_senha)
    values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
    values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao values (1, 1);

