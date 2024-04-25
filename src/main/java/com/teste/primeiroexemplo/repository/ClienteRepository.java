package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Cliente;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;

@Repository
public class ClienteRepository {
    /**
     * Simula um banco de dados
     */
    List<Cliente> clientes = new ArrayList<Cliente>();
    private Integer ultimoId = 0;

    /**
     * Consulta todos os clientes cadastrados no banco de dados
     * @return Lista de Clientes cadastrados
     */
    public List<Cliente> obterTodosClientes(){
        return clientes;
    }

    /**
     * Consulta um cliente especifico no banco de dados
     * @param id do cliente a ser encontrado
     * @return retorna o cliente encontrado
     */
    public Optional<Cliente> obterClientePorID(Integer id){

        Optional<Cliente> clienteEncontrado = clientes.stream()
                                                      .filter(cliente -> cliente.getId() == id)
                                                      .findFirst();

        if (clienteEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Cliente " + id + " não encontrado!");
        }

        return clienteEncontrado;
    }

    /**
     * Adiciona um no cliente no banco de dados
     * @param cliente a ser adicionado
     * @return retorna caso sucesso os dados do cliente
     */
    public Cliente adicionarCliente(Cliente cliente){

        cliente.setId(++ultimoId);
        clientes.add(cliente);

        return cliente;
    }

    /**
     * Remove um cliente do banco de dados
     * @param id do cliente a ser removido
     */
    public void deletarCliente(Integer id){
        Optional<Cliente> clienteRemovido = obterClientePorID(id);
        clientes.remove(clienteRemovido.get());
    }

    /**
     * Atualiza os dados de um cliente no banco de dados
     * @param cliente a ser alterado
     * @return retorna os dados do cliente com as alterações
     */
    public Cliente alterar(Cliente cliente){

        deletarCliente(cliente.getId());
        clientes.add(cliente);

        return cliente;
    }
}
