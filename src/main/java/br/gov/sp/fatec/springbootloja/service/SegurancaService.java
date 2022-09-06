package br.gov.sp.fatec.springbootloja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springbootloja.entity.Autorizacao;
import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Usuario;
import br.gov.sp.fatec.springbootloja.entity.Vendas;

public interface SegurancaService extends UserDetailsService{

    public Vendas realizarVenda(LocalDate data_venda, BigDecimal valor, String descricao, String cpf, Long codProduto, String nome);

    public List<Clientes> buscarTodasClientes();
    
    public List<Vendas> buscarTodasVendas();

    public Clientes buscarClientesPorId(Long id);

    public Clientes buscarClientesPorNome(String nome);

    public Clientes criarClientes(String nome, String cpf, String telefone);

    public Usuario criarUsuario(String nome, String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario buscarUsuarioPorNome(String nome);

    public Autorizacao buscarAutorizacaoPorNome(String nome);
    
}
