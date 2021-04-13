package br.gov.sp.fatec.springbootloja.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.sp.fatec.springbootloja.entity.Vendas;

public interface SegurancaService {

    public Vendas realizarVenda(LocalDate data_venda, BigDecimal valor, String descricao, String cpf, Long codProduto, String nome);
    
}
