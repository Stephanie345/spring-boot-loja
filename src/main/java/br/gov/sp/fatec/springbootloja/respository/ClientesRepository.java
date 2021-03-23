package br.gov.sp.fatec.springbootloja.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootloja.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    
}
