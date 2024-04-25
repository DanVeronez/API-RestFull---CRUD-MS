package com.teste.primeiroexemplo.model;

public class Cliente {
    private Integer id;
    private String nome;
    private Long cpf;
    private String email;
    // private Endereco endereco;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    // public Endereco getEndereco() {
    //     return endereco;
    // }
    // public void setEndereco(Endereco endereco) {
    //     this.endereco = endereco;
    // }
    
}
