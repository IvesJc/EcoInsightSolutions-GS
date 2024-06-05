package org.ehap.models;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"nomeUsuario", "nome", "senha"})
public class Usuario {

    private String nomeUsuario;
    private String nome;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nomeUsuario, String nome, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
