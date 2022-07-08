package br.gov.sp.fatec.springbootloja.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootloja.controller.View;




@Entity
@Table(name = "vendas")
public class Vendas {
    
    @JsonView(View.VendasCompleto.class)
    @Id
    @Column(name = "id_venda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(View.VendasResumo.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cliente")
    private Clientes clientes;

    @Column(name = "data_venda")
    private LocalDate data_venda;

    @Column(name = "valor")
    private BigDecimal  valor;

    @JsonView(View.VendasResumo.class)
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

    public Clientes getClientes() {
        return this.clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public LocalDate getData_venda() {
        return this.data_venda;
    }

    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<Produtos> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(Set<Produtos> produtos) {
        this.produtos = produtos;
    }

    
}
