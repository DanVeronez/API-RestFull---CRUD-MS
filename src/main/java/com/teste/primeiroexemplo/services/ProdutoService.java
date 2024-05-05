package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import com.teste.primeiroexemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo que retorna todos os produtos
     * @return todos os produtos
     */
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Metodo que retorna o produto através do seu ID
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<ProdutoDTO> obterPorID(Integer id){

        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado!");
        }

        ProdutoDTO produtoDTO = new ModelMapper().map(produto.get(),ProdutoDTO.class);

        return Optional.of(produtoDTO);
    }

    /**
     * Metodo que cadastra produtos
     * @param produto a ser adicionado
     * @return retorna o produto adicionado
     */
    public ProdutoDTO adicionar(ProdutoDTO produtodDto){

        produtodDto.setId(null);

        ModelMapper modelMapper = new ModelMapper();

        Produto produto = modelMapper.map(produtodDto, Produto.class);

        produto = produtoRepository.save(produto);

        produtodDto.setId(produto.getId());

        return produtodDto;
    }

    /**
     * Metodo para deletar um produto
     * @param id do produto que será deletado
     */
    public String deletar(Integer id){

        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel deletar o produto " + id + " - Produto não encontrado.");
        }

        produtoRepository.deleteById(id);

        return "Produto com ID: " + id + " foi Deletado com sucesso!";
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto a ser atualizado com as alteraçãoes
     * @return retorna produto atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO){
        
        Optional<Produto> produtoExists = produtoRepository.findById(id);

        if (produtoExists.isEmpty()) {
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado!");
        }

        produtoDTO.setId(id);
        
        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTO, Produto.class);

        produtoRepository.save(produto);

        return produtoDTO;
    }

}
