package br.gov.sp.fatec.springbootloja.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private BigDecimal  preco;

    @Column(name = "quant_estoque")
    private BigDecimal  quantidade;

    @Column(name = "cod_produto")
    private Long  codProduto;

     
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "produtos")
    private Set<Vendas> vendas;

     
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodProduto() {
        return this.codProduto;
    }

    public void setCodProduto(Long codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    
    public Set<Vendas> getVendas() {
        return this.vendas;
    }

    public void setVendas(Set<Vendas> vendas) {
        this.vendas = vendas;
    }
}
