package br.gov.sp.fatec.springbootloja.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springbootloja.entity.Autorizacao;
import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Usuario;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springbootloja.respository.AutorizacaoRepository;
import br.gov.sp.fatec.springbootloja.respository.ClientesRepository;
import br.gov.sp.fatec.springbootloja.respository.ProdutosRepository;
import br.gov.sp.fatec.springbootloja.respository.UsuarioRepository;
import br.gov.sp.fatec.springbootloja.respository.VendasRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {

    @Autowired
    private ProdutosRepository produtosRepo;

    @Autowired
    private VendasRepository vendasRepo;
    
    @Autowired
    private ClientesRepository clientesRepo;

    @Autowired
    private AutorizacaoRepository autRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passEncoder;

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
    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Vendas> buscarTodasVendas(){
        return vendasRepo.findAll();
        
    }
    
    @Override
    public List<Clientes> buscarTodasClientes(){
        return clientesRepo.findAll();
        
    }

    @Override
    public Clientes buscarClientesPorId(Long id) {
        Optional<Clientes> clientesOp = clientesRepo.findById(id);
        if(clientesOp.isPresent()){
            return clientesOp.get();
        }
        throw new RegistroNaoEncontradoException("Cliente não encontrado");
    }

    @Override
    public Clientes buscarClientesPorNome(String nome){
        Clientes clientes = clientesRepo.findByNome(nome);
        if(clientes != null){
            return clientes;
        }
        throw new RegistroNaoEncontradoException("Cliente não encontrado");
    }
    public Clientes criarClientes(String nome, String cpf, String telefone){
        Clientes clientes = new Clientes();
        clientes.setNome(nome);
        clientes.setCpf(cpf);
        clientes.setTelefone(telefone);
        clientesRepo.save(clientes);
        return clientes;
    }

    
    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autRepo.findByNome(autorizacao);
        if(aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }
    
    
  @Override
  @PreAuthorize("hasRole('ADMIN')")
  public List<Usuario> buscarTodosUsuarios() {
    return usuarioRepo.findAll();
  }

  @Override
  @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
  public Usuario buscarUsuarioPorId(Long id) {
    Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
    if (usuarioOp.isPresent()) {
      return usuarioOp.get();
    }
    throw new RegistroNaoEncontradoException("Usuário não encontrado!");
  }

  @Override
  @PreAuthorize("isAuthenticated()")
  public Usuario buscarUsuarioPorNome(String nome) {
    Usuario usuario = usuarioRepo.findByNome(nome);
    if (usuario != null) {
      return usuario;
    }
    throw new RegistroNaoEncontradoException("Usuário não encontrado!");

  }

  @Override
  @PreAuthorize("isAuthenticated()")
  public Autorizacao buscarAutorizacaoPorNome(String nome) {
    Autorizacao autorizacao = autRepo.findByNome(nome);
    if (autorizacao != null) {
      return autorizacao;
    }
    throw new RegistroNaoEncontradoException("Autorização não encontrada!");
  }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepo.findByNome(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
    }
    return User.builder().username(username).password(usuario.getSenha())
        .authorities(usuario.getAutorizacoes().stream()
            .map(Autorizacao::getNome).collect(Collectors.toList())
            .toArray(new String[usuario.getAutorizacoes().size()]))
        .build();
  }
  
}
