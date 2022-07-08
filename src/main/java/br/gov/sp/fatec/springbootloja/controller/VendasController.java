package br.gov.sp.fatec.springbootloja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.springbootloja.entity.Vendas;
import br.gov.sp.fatec.springbootloja.service.SegurancaService;


@RestController
@RequestMapping(value="/vendas")
@CrossOrigin
public class VendasController {

    @Autowired
    private SegurancaService segurancaService;

    @JsonView(View.VendasCompleto.class)
    @GetMapping
    public List<Vendas> buscarTodos(){
        return segurancaService.buscarTodasVendas();
        
    }
   
    
    
}
