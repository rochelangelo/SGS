/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Maquina;

/**
 *
 * @author r-r20
 */
public class MaquinaDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Maquina maquina){
        String sql = "INSERT INTO maquinas (numero_patrimonio, secao_pertencente, militar_responsavel)VALUES(?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, maquina.getNumPatrimomio());
            stmt.setString(2, maquina.getSecaoPertencente());
            stmt.setString(3, maquina.getMilitarResponsavel());
            stmt.execute();
            return true;
        }catch (SQLException ex){
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar (Maquina maquina){
        String sql = "UPDATE maquinas SET numero_patrimonio=?, secao_pertencente=?, militar_responsavel=? WHERE id=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, maquina.getNumPatrimomio());
            stmt.setString(2, maquina.getSecaoPertencente());
            stmt.setString(3, maquina.getMilitarResponsavel());
            stmt.setInt(4, maquina.getId());
            stmt.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Maquina maquina) {
        String sql = "DELETE FROM maquinas WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, maquina.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Maquina> listar() {
        String sql = "SELECT * FROM maquinas";
        List<Maquina> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Maquina maquina = new Maquina();
                maquina.setId(resultado.getInt("id"));
                maquina.setNumPatrimomio(resultado.getInt("numero_patrimonio"));
                maquina.setSecaoPertencente(resultado.getString("secao_pertencente"));
                maquina.setMilitarResponsavel(resultado.getString("militar_responsavel"));
                retorno.add(maquina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Maquina buscar(Maquina maquina) {
        String sql = "SELECT * FROM maquinas WHERE id=?";
        Maquina retorno = new Maquina();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, maquina.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                maquina.setId(resultado.getInt("id"));
                maquina.setNumPatrimomio(resultado.getInt("numero_patrimonio"));
                maquina.setSecaoPertencente(resultado.getString("secao_pertencente"));
                maquina.setMilitarResponsavel(resultado.getString("militar_responsavel"));
                retorno = maquina;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Maquina buscarbyPatrimonio(int numPatrimonio) {
        String sql = "SELECT * FROM maquinas WHERE numero_patrimonio=?";
        Maquina retorno = new Maquina();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, numPatrimonio);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno.setId(resultado.getInt("id"));
                retorno.setNumPatrimomio(resultado.getInt("numero_patrimonio"));
                retorno.setSecaoPertencente(resultado.getString("secao_pertencente"));
                retorno.setMilitarResponsavel(resultado.getString("militar_responsavel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public boolean buscaExistecia(int numPatrimonio) {
        String sql = "SELECT * FROM maquinas WHERE numero_patrimonio=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, numPatrimonio);
            ResultSet resultado = stmt.executeQuery();
            if(resultado.next()){
               return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaquinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
