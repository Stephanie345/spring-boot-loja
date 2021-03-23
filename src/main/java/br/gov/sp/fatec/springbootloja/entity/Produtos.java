package br.gov.sp.fatec.springbootloja.entity;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Produtos")
public class Produtos {
    
    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private DecimalFormat  preco;

    @Column(name = "quant_estoque")
    private DecimalFormat  quantidade;

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DecimalFormat getPreco() {
        return this.preco;
    }

    public void setPreco(DecimalFormat preco) {
        this.preco = preco;
    }

    public DecimalFormat getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(DecimalFormat quantidade) {
        this.quantidade = quantidade;
    }
}
