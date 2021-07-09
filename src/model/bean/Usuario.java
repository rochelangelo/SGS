/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;


/**
 *
 * @author r-r20
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String graduacao;
    private String funcao;

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

    public String getGraduacao() {
        return graduacao;
    }

    public void setGraduacao(String graduacao) {
        this.graduacao = graduacao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Usuario() {
    }

    public Usuario(int id, String nome, String graduacao, String funcao) {
        this.id = id;
        this.nome = nome;
        this.graduacao = graduacao;
        this.funcao = funcao;
    }
    
    
    
}
