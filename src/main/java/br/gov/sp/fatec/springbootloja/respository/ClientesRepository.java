package br.gov.sp.fatec.springbootloja.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springbootloja.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    public List<Clientes> findByNomeContainsIgnoreCase(String nome);

    public Clientes findByNome(String nome);

    public Clientes findByNomeAndTelefone(String nome, String telefone);
}
