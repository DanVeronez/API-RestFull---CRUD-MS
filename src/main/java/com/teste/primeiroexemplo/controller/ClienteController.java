package com.teste.primeiroexemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.primeiroexemplo.model.Cliente;
import com.teste.primeiroexemplo.services.ClienteService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    
    @GetMapping
    public List<Cliente> obterTodosClientes(){
        return clienteService.obterTodosClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obterClientePorID(@PathVariable Integer id){
        return clienteService.obterClientePorID(id);
    }

    @PostMapping()
    public Cliente adicionarCliente(@RequestBody Cliente cliente) {
        return clienteService.adicionarCliente(cliente);
    }
    
    @DeleteMapping("/{id}")
    public String removeCliente(@PathVariable Integer id){
        return clienteService.deletarCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteService.alterar(id, cliente);
    }
}
