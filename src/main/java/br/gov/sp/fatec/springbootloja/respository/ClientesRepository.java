package br.gov.sp.fatec.springbootloja.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.sp.fatec.springbootloja.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    public List<Clientes> findByNomeContainsIgnoreCase(String nome);

    public Clientes findByNome(String nome);

    @Query("select c from Clientes c where c.nome = ?1")
    public Clientes buscaClientePorNome(String nome);


    public Clientes findByNomeAndTelefone(String nome, String telefone);

     @Query("select c from Clientes c where c.nome = ?1 and c.telefone = ?2")
    public Clientes buscaClientePorNomeETelefone(String nome, String telefone);
}
