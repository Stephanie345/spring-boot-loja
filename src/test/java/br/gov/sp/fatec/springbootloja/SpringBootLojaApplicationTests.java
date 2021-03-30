package br.gov.sp.fatec.springbootloja;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.respository.ClientesRepository;
import br.gov.sp.fatec.springbootloja.respository.ProdutosRepository;
import br.gov.sp.fatec.springbootloja.respository.VendasRepository;


@SpringBootTest
class SpringBootLojaApplicationTests {

    @Autowired
    private ClientesRepository clientesRepo;

    @Autowired
    private ProdutosRepository produtosRepo;

    @Autowired
    private VendasRepository vendasRepo;

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

    @Test
    void testaVendas() {
        Produtos produtos = produtosRepo.findById(1L).get();
        assertEquals("2021-03-29", produtos.getVendas().iterator().next().getData_venda().toString());
    }

    @Test
    void testaProdutos() {
        Vendas vendas = vendasRepo.findById(1L).get();
        assertEquals("2.50", vendas.getProdutos().iterator().next().getPreco().toString());
    }


}
