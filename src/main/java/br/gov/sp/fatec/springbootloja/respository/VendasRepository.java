package br.gov.sp.fatec.springbootloja.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootloja.entity.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{

    public List<Vendas> findByProdutosDescricao(String descricao);

    @Query("select c from Vendas c inner join c.produtos p where p.descricao =?1")
    public List<Vendas> buscaPorNomeDescricao(String descricao);
    
}
