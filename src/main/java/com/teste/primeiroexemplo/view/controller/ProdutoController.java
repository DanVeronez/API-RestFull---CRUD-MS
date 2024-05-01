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
import com.teste.primeiroexemplo.view.model.ProdutoRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorID(@PathVariable Integer id){

        Optional<ProdutoDTO> produtoDtO = produtoService.obterPorID(id);

        ProdutoResponse produtoResponse = new ModelMapper().map(produtoDtO.get(), ProdutoResponse.class);

        return new ResponseEntity<>(Optional.of(produtoResponse),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest){

        ModelMapper modelMapper = new ModelMapper();

        ProdutoDTO produtoDTO = modelMapper.map(produtoRequest, ProdutoDTO.class);

        produtoDTO = produtoService.adicionar(produtoDTO);

        ProdutoResponse produtoResponse = modelMapper.map(produtoDTO , ProdutoResponse.class);

        return new ResponseEntity<>(produtoResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Integer id, @RequestBody ProdutoRequest produtorRequest){

        ModelMapper modelMapper = new ModelMapper();

        ProdutoDTO produtoDTO = modelMapper.map(produtorRequest, ProdutoDTO.class);

        produtoDTO = produtoService.atualizar(id, produtoDTO);

        ProdutoResponse produtoResponse = modelMapper.map(produtoDTO, ProdutoResponse.class);

        return new ResponseEntity<>(produtoResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id){
        return new ResponseEntity<>(produtoService.deletar(id),HttpStatus.NO_CONTENT);
    }
}