package br.gov.sp.fatec.springbootloja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.respository.ProdutosRepository;
import br.gov.sp.fatec.springbootloja.respository.VendasRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private ProdutosRepository produtosRepo;

    @Autowired
    private VendasRepository vendasRepo;
    
    @Transactional
    public Vendas realizarVenda(LocalDate data_venda, BigDecimal valor, String descricao, BigDecimal preco,BigDecimal quantidade, Long clientes ) {
        Produtos prod = produtosRepo.findByDescricao(descricao);
        if(prod == null){
            prod = new Produtos();
            prod.setDescricao(descricao);
            prod.setPreco(preco);
            prod.setQuantidade(quantidade);
            produtosRepo.save(prod);
        }
        Vendas vendas = new Vendas();
        vendas.setData_venda(data_venda);
        vendas.setValor(valor);
        vendas.setProdutos(new HashSet<Produtos>());
        vendas.getProdutos().add(prod);
        vendasRepo.save(vendas);
        return vendas;
    }
    
}
