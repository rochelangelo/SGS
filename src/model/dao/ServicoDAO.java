/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Servico;

/**
 *
 * @author r-r20
 */
public class ServicoDAO {
    
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Servico servico){
        String sql = "INSERT INTO servicos (dataentrada, datasaida, tecnicoresponsavel, secaoresponsavel, observacoes, situacao, idmaquina)VALUES(?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, (Date) servico.getDataEntrada());
            stmt.setDate(2, (Date) servico.getDataSaida());
            stmt.setString(3, servico.getTecnicoResponsavel());
            stmt.setString(4, servico.getSecaoRequerente());
            stmt.setString(5, servico.getObservacao());
            stmt.setString(6, servico.getSituacao());
            stmt.setInt(7, servico.getIdMaquina());
            stmt.execute();
            return true;
                        
        }catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarSituacaoObservacao(Servico servico) {
        String sql = "UPDATE servicos SET observacoes=?, situacao=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getObservacao());
            stmt.setString(2, servico.getSituacao());
            stmt.setInt(3, servico.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Servico servico) {
        String sql = "DELETE FROM servicos WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Servico> listar() {
        String sql = "SELECT * FROM servicos";
        List<Servico> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Servico servico = new Servico();
                servico.setId(resultado.getInt("id"));
                servico.setDataEntrada(resultado.getDate("dataentrada"));
                servico.setDataSaida(resultado.getDate("datasaida"));
                servico.setTecnicoResponsavel(resultado.getString("tecnicoresponsavel"));
                servico.setSecaoRequerente(resultado.getString("secaoresponsavel"));
                servico.setObservacao(resultado.getString("observacoes"));
                servico.setSituacao(resultado.getString("situacao"));
                servico.setIdMaquina(resultado.getInt("idmaquina"));
                retorno.add(servico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Servico buscar(Servico servico) {
        String sql = "SELECT * FROM servicos WHERE id=?";
        Servico retorno = new Servico();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, servico.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                servico.setId(resultado.getInt("id"));
                servico.setDataEntrada(resultado.getDate("dataentrada"));
                servico.setDataSaida(resultado.getDate("datasaida"));
                servico.setTecnicoResponsavel(resultado.getString("tecnicoresponsavel"));
                servico.setSecaoRequerente(resultado.getString("secaoresponsavel"));
                servico.setObservacao(resultado.getString("observacoes"));
                servico.setSituacao(resultado.getString("situacao"));
                servico.setIdMaquina(resultado.getInt("idmaquina"));
                retorno = servico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
