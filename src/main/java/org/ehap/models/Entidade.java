package org.ehap.models;

public class Entidade {

    private Integer id;
    private String nome;
    private String codigo;
    private char grupo;

    public Entidade() {
    }

    public Entidade(Integer id, String nome, String codigo, char grupo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.grupo = grupo;
    }

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }
}
