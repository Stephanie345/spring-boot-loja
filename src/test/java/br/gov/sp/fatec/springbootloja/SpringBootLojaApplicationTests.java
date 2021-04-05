package br.gov.sp.fatec.springbootloja;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.respository.ClientesRepository;
import br.gov.sp.fatec.springbootloja.respository.ProdutosRepository;
import br.gov.sp.fatec.springbootloja.respository.VendasRepository;


@SpringBootTest
@Transactional
@Rollback
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
        clientes.setCpf("59345270808");
        clientes.setTelefone("(12)32098765");
        clientesRepo.save(clientes);
        assertNotNull(clientes.getId());
    }
    @Test
    void testaInsercaoVendas() {
        Vendas vendas = new Vendas();
        Clientes clientes = new Clientes();
        clientes.setNome("Stephanie");
        clientes.setCpf("75645570808");
        clientes.setTelefone("(12)32598765");
        clientesRepo.save(clientes);
        vendas.setData_venda(LocalDate.parse("2021-04-05"));
        vendas.setValor(BigDecimal.valueOf(3.5));
        vendas.setClientes(clientes);
        vendas.setProdutos(new HashSet<Produtos>());
        Produtos prod = new Produtos();
        prod.setDescricao("tinta");
        prod.setPreco(BigDecimal.valueOf(3.5));
        prod.setQuantidade(BigDecimal.valueOf(10));
        produtosRepo.save(prod);
        vendas.getProdutos().add(prod);
        vendasRepo.save(vendas);
        assertNotNull(vendas.getProdutos().iterator().next().getId());
    }
    @Test
    void testaInsercaoProdutos() {
        Vendas vendas = new Vendas();
        Clientes clientes = new Clientes();
        clientes.setNome("Stephanie");
        clientes.setCpf("15155572808");
        clientes.setTelefone("(12)34598765");
        clientesRepo.save(clientes);
        vendas.setData_venda(LocalDate.parse("2021-04-05"));
        vendas.setValor(BigDecimal.valueOf(3.5));
        vendas.setClientes(clientes);
        vendasRepo.save(vendas);
        Produtos prod = new Produtos();
        prod.setDescricao("tinta");
        prod.setPreco(BigDecimal.valueOf(3.5));
        prod.setQuantidade(BigDecimal.valueOf(10));
        prod.setVendas(new HashSet<Vendas>());
        prod.getVendas().add(vendas);
        produtosRepo.save(prod);
        assertNotNull(prod.getVendas().iterator().next().getId());
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

    @Test
    void testaBuscaClienteNomeConatins() {
        List<Clientes> clientes = clientesRepo.findByNomeContainsIgnoreCase("E");
        assertFalse(clientes.isEmpty());
    }

    @Test
    void testaBuscaClienteNome() {
        Clientes clientes = clientesRepo.findByNome("Sofia");
        assertNotNull(clientes);
    }

    @Test
    void testaBuscaClienteNomeCpf() {
        Clientes clientes = clientesRepo.findByNomeAndTelefone("Stephanie", "(12)34598765");
        assertNotNull(clientes);
    }

    @Test
    void testaBuscaVendasDescricaoProdutos() {
        List<Vendas> vendas = vendasRepo.findByProdutosDescricao("linha");
        assertFalse(vendas.isEmpty());
    }



}
