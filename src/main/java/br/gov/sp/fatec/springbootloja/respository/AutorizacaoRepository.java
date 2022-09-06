package br.gov.sp.fatec.springbootloja.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootloja.entity.Autorizacao;


public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {

    public Autorizacao findByNome(String autorizacao);
    
}
