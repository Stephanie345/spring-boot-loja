package br.gov.sp.fatec.springbootloja.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vendas")
public class Vendas {

    @Id
    @Column(name = "id_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long id_clientes;

    @Column(name = "data_venda")
    private Date data_venda;

    @Column(name = "valor")
    private BigDecimal  valor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "itens_vendas",
               joinColumns = { @JoinColumn(name="id_venda")},
               inverseJoinColumns = { @JoinColumn(name="id_produto")})
    private Set<Produtos> produtos;

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_clientes() {
        return this.id_clientes;
    }

    public void setId_clientes(Long id_clientes) {
        this.id_clientes = id_clientes;
    }

    public Date getData_venda() {
        return this.data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setData(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<Produtos> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(Set<Produtos> produtos) {
        this.produtos = produtos;
    }

    
}
