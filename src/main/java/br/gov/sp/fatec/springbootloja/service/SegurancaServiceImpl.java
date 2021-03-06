package br.gov.sp.fatec.springbootloja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.respository.ClientesRepository;
import br.gov.sp.fatec.springbootloja.respository.ProdutosRepository;
import br.gov.sp.fatec.springbootloja.respository.VendasRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private ProdutosRepository produtosRepo;

    @Autowired
    private VendasRepository vendasRepo;
    
    @Autowired
    private ClientesRepository clientesRepo;

    @Transactional
    public Vendas realizarVenda(LocalDate data_venda, BigDecimal valor, String descricao, String cpf, Long codProduto, String nome)  {
        Clientes cli = clientesRepo.findByCpf(cpf);
        if(cli == null){
            Clientes clientes = new Clientes();
            clientes.setCpf(cpf);
            clientes.setNome(nome);
            clientesRepo.save(clientes);
        }
        Vendas vendas = new Vendas();
        vendas.setData_venda(data_venda);
        vendas.setValor(valor);
        vendas.setClientes(cli);
        vendas.setProdutos(new HashSet<Produtos>());
        Produtos produtos = produtosRepo.findByCodProduto(codProduto);
        vendas.getProdutos().add(produtos);
        vendasRepo.save(vendas);
        return vendas;
    }
    
}
