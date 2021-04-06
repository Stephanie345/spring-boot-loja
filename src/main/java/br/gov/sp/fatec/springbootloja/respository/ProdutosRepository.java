package br.gov.sp.fatec.springbootloja.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootloja.entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{

    public Produtos findByDescricao(String produtos);

    @Query("select p from Produtos p inner join p.vendas v inner join v.clientes c where c.cpf =?1")
    public List<Produtos> buscaPorProdutosCpf(String cpf);
    
}