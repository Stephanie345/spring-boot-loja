create schema db_loja
default character set utf8
default collate utf8_general_ci;

use db_loja;

create user 'root'@'localhost' identified by 'root123';

grant select, insert, delete, update on db_loja.* to user@'localhost';



CREATE TABLE IF NOT EXISTS `db_loja`.`clientes` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` CHAR(11) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_cliente`)
)default charset = utf8
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `db_loja`.`vendas` (
  `id_venda` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id_venda`),
  INDEX `fk_vendas_clientes_idx` (`id_cliente` ASC),
  CONSTRAINT `fk_vendas_clientes`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `db_loja`.`clientes` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)default charset = utf8
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_loja`.`produtos` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(120) NOT NULL,
  `preco` DECIMAL(8,2) NOT NULL,
  `quant_estoque` DECIMAL(4,0) NOT NULL,
  PRIMARY KEY (`id_produto`)
)default charset = utf8
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_loja`.`itens_vendas` (
  `id_venda` INT NOT NULL,
  `id_produto` INT NOT NULL,
  INDEX `fk_vendas_has_produtos_produtos1_idx` (`id_produto` ASC) ,
  INDEX `fk_vendas_has_produtos_vendas1_idx` (`id_venda` ASC),
  CONSTRAINT `fk_vendas_has_produtos_vendas1`
    FOREIGN KEY (`id_venda`)
    REFERENCES `db_loja`.`vendas` (`id_venda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendas_has_produtos_produtos1`
    FOREIGN KEY (`id_produto`)
    REFERENCES `db_loja`.`produtos` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)default charset = utf8
ENGINE = InnoDB