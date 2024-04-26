package com.teste.primeiroexemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.services.ProdutoService;
import com.teste.primeiroexemplo.shared.ProdutoDTO;
import com.teste.primeiroexemplo.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){

        List<ProdutoDTO> produtosDTO = produtoService.obterTodos();

        List<ProdutoResponse> produtoResponse = produtosDTO.stream()
        .map(produtoDTO -> new ModelMapper().map(produtoDTO, ProdutoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto){
        return produtoService.adicionar(produto);
    }

    @GetMapping("/{id}")
    public Optional<Produto> obterPorID(@PathVariable Integer id){
        return produtoService.obterPorID(id);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Integer id){
        produtoService.deletar(id);
        return "Produto com ID: " + id + " foi Deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Integer id,@RequestBody Produto produto){
        return produtoService.atualizar(id, produto);
    }
}
