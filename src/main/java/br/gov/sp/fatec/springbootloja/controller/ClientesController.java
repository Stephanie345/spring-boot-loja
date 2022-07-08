package br.gov.sp.fatec.springbootloja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootloja.entity.Clientes;

import br.gov.sp.fatec.springbootloja.service.SegurancaService;

@RestController
@CrossOrigin
@RequestMapping(value="/clientes")
public class ClientesController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.ClientesResumo.class)
    @GetMapping
    public List<Clientes> buscarTodasClientes(){
        return segurancaService.buscarTodasClientes();
        
    }

    @JsonView(View.ClientesResumo.class)
    @GetMapping(value = "/{id}")
    public Clientes buscarPorId(@PathVariable("id") Long id){
        return segurancaService.buscarClientesPorId(id);
        
    }

    @JsonView(View.ClientesResumo.class)
    @GetMapping(value= "/nome")
    public Clientes buscarPorNome(@RequestParam(value="nome") String nome){
        return segurancaService.buscarClientesPorNome(nome);
    }
    @PostMapping
    public ResponseEntity<Clientes> cadastrarNovoClientes(@RequestBody Clientes clientes,
        UriComponentsBuilder uriComponentsBuilder){
        clientes = segurancaService.criarClientes(clientes.getNome(), clientes.getCpf(), clientes.getTelefone());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            uriComponentsBuilder.path(
                "/clientes/" + clientes.getId()).build().toUri());
        return new ResponseEntity<Clientes>(clientes, responseHeaders, HttpStatus.CREATED);
    }
    
   
  
}
