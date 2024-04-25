package com.teste.primeiroexemplo.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Cliente;
import com.teste.primeiroexemplo.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> obterTodosClientes(){
        return clienteRepository.obterTodosClientes();
    }

    public Optional<Cliente> obterClientePorID(Integer id){
        return clienteRepository.obterClientePorID(id);
    }

    public Cliente adicionarCliente(Cliente cliente){
        return clienteRepository.adicionarCliente(cliente);
    }

    public String deletarCliente(Integer id){
        clienteRepository.deletarCliente(id);
        return "Cliente " + id +" removido com sucesso!";
    }

    public Cliente alterar(Integer id, Cliente cliente){
        cliente.setId(id);
        return clienteRepository.alterar(cliente);
    }
}
