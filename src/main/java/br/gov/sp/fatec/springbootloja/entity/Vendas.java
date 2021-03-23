package br.gov.sp.fatec.springbootloja.entity;

import java.text.DecimalFormat;
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
@Table(name = "vendas")
public class Vendas {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private String data;

    @Column(name = "valor")
    private DecimalFormat  valor;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Produtos> produtos;

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DecimalFormat getValor() {
        return this.valor;
    }

    public void setData(DecimalFormat valor) {
        this.valor = valor;
    }

    public Set<Produtos> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(Set<Produtos> produtos) {
        this.produtos = produtos;
    }

    
}
