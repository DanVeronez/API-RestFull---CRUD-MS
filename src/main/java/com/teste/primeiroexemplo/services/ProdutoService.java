package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo que retorna todos os produtos
     * @return todos os produtos
     */
    public List<Produto> obterTodos(){
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto através do seu ID
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorID(Integer id){
        return produtoRepository.obterPorID(id);
    }

    /**
     * Metodo que cadastra produtos
     * @param produto a ser adicionado
     * @return retorna o produto adicionado
     */
    public Produto adicionar(Produto produto){
        return produtoRepository.adicionar(produto);
    }

    /**
     * Metodo para deletar um produto
     * @param id do produto que será deletado
     */
    public void deletar(Integer id){
        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto a ser atualizado com as alteraçãoes
     * @return retorna produto atualizado
     */
    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }

}
