create schema db_loja;

use db_loja;

create user stephanie@localhost identified by 'pass123';

grant select, insert, delete, update on db_loja.* to stephanie@localhost;


CREATE TABLE IF NOT EXISTS db_loja.clientes (
  id_cliente BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  telefone VARCHAR(15) NOT NULL,
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

insert into clientes(nome,cpf,telefone) values ('Sofia','1245678909','1234567890');
insert into vendas(data_venda,valor,id_cliente) values ('2021-03-29','2.5','1');
insert into produtos(descricao,preco,quant_estoque) values ('linha','2.5','10');
insert into itens_vendas values(1,1);

