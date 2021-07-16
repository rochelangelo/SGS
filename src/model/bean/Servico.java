/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author r-r20
 */
public class Servico {
    
    private int id;
    private Date dataEntrada;
    private Date dataSaida;
    private String tecnicoResponsavel;
    private String secaoRequerente;
    private String observacao;
    private String situacao;
    private int idMaquina;

    public Servico() {
    }

    public Servico(int id, Date dataEntrada, Date dataSaida, String tecnicoResponsavel, String secaoRequerente, String observacao, String situacao, int idMaquina) {
        this.id = id;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.secaoRequerente = secaoRequerente;
        this.observacao = observacao;
        this.situacao = situacao;
        this.idMaquina = idMaquina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(String tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public String getSecaoRequerente() {
        return secaoRequerente;
    }

    public void setSecaoRequerente(String secaoRequerente) {
        this.secaoRequerente = secaoRequerente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }
    
    
    
    
}
