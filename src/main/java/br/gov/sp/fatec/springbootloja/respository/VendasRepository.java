package br.gov.sp.fatec.springbootloja.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootloja.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{

    public List<Vendas> findByProdutosDescricao(String descricao);
    
}
