package br.gov.sp.fatec.springbootloja.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootloja.entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
    
}