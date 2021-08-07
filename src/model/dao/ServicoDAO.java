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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String sql = "INSERT INTO servicos (dataentrada, datasaida, tecnicoresponsavel, secaoresponsavel, observacoes, situacao, patrimonio_maquina)VALUES(?,?,?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, (Date) servico.getDataEntrada());
            stmt.setDate(2, (Date) servico.getDataSaida());
            stmt.setString(3, servico.getTecnicoResponsavel());
            stmt.setString(4, servico.getSecaoRequerente());
            stmt.setString(5, servico.getObservacao());
            stmt.setString(6, servico.getSituacao());
            stmt.setInt(7, servico.getPatrimonioMaquina());
            stmt.execute();
            return true;
                        
        }catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarObservacao(Servico servico) {
        String sql = "UPDATE servicos SET observacoes=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getObservacao());
            stmt.setInt(2, servico.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterarSituação(Servico servico) {
        String sql = "UPDATE servicos SET situacao=?, datasaida=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getSituacao());
            stmt.setDate(2, (Date) servico.getDataSaida());
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
                servico.setPatrimonioMaquina(resultado.getInt("patrimonio_maquina"));
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
                servico.setPatrimonioMaquina(resultado.getInt("patrimonio_maquina"));
                retorno = servico;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Map<Integer, ArrayList> listaQuantidadeServicosMes(){
        String sql = "SELECT count(id), extract (year from dataEntrada) as ano, extract(month from dataEntrada) as mes from servicos group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next()){
                ArrayList linha = new ArrayList();
                if(!retorno.containsKey(resultado.getInt("ano"))){
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                }else{
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
                    
            return retorno;
        }catch(SQLException ex){
            Logger.getLogger(ServicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
        
    }
    
}
