package br.gov.sp.fatec.springbootloja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.springbootloja.entity.Clientes;
import br.gov.sp.fatec.springbootloja.entity.Produtos;
import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.service.SegurancaService;

@RestController
@CrossOrigin
@RequestMapping(value="/clientes")
public class ClientesController {

    @Autowired
    private SegurancaService segurancaService;

    @GetMapping
    public List<Clientes> buscarTodasClientes(){
        return segurancaService.buscarTodasClientes();
        
    }

    @GetMapping(value = "/{id}")
    public Clientes buscarPorId(@PathVariable("id") Long id){
        return segurancaService.buscarClientesPorId(id);
        
    }
    @GetMapping(value= "/nome")
    public Clientes buscarPorNome(@RequestParam(value="nome") String nome){
        return segurancaService.buscarClientesPorNome(nome);
    }
    @PostMapping
    public Clientes cadastrarNovoClientes(@RequestBody Clientes clientes){
        return segurancaService.criarClientes(clientes.getNome(), clientes.getCpf(), clientes.getTelefone());
    }
    
   
  
}
