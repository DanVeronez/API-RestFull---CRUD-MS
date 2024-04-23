package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Produto;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<Produto>(); 
    private Integer ultimoId = 0;

    /**
     * Metodo que retorna todos os produtos
     * @return todos os produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto através do seu ID
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorID(Integer id){
        return produtos.stream()
                        .filter(produto -> produto.getId() == id)
                        .findFirst();
    }

    /**
     * Metodo que cadastra produtos
     * @param produto a ser adicionado
     * @return retorna o produto adicionado
     */
    public Produto adicionar(Produto produto){

        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar um produto
     * @param id do produto que será deletado
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto a ser atualizado com as alteraçãoes
     * @return retorna produto atualizado
     */
    public Produto atualizar(Produto produto){

        Optional<Produto> produtoEncontrado = obterPorID(produto.getId());
        
        if (produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto não Encontrado!");
        }
        
        deletar(produto.getId());

        produtos.add(produto);

        return produto;
    }
}
