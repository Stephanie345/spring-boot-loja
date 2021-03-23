package br.gov.sp.fatec.springbootloja;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.respository.ClientesRepository;

@SpringBootTest
class SpringBootLojaApplicationTests {

    @Autowired
    private ClientesRepository clientesRepo;

	@Test
	void contextLoads() {
	}

    @Test
    void testaInsercao() {
        Clientes clientes = new Clientes();
        clientes.setNome("Stephanie");
        clientes.setCpf("48341260808");
        clientes.setTelefone("(12)32098765");
        clientesRepo.save(clientes);
        assertNotNull(clientes.getId());


    }
}
