package model.bean;

import java.util.Date;

public class Material {
    private int id;
    private String nome;
    private String descricao;
    private Date dataEntrada;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Material() {
    }

    public Material(int id, String nome, String descricao, Date dataEntrada, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataEntrada = dataEntrada;
        this.quantidade = quantidade;
    }
    
    
}
