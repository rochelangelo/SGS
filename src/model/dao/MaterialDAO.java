/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.sun.javafx.logging.PlatformLogger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Material;

/**
 *
 * @author r-r20
 */
public class MaterialDAO {
    
    private Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public void setConnection(Connection connection){
        this.connection = connection;
    }
    
    public boolean inserir(Material material){
        String sql = "INSERT INTO materiais (nome, descricao, dataentrada, quantidade)VALUES(?,?,?,?)";
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getDescricao());
            stmt.setDate(3, (Date) material.getDataEntrada());
            stmt.setInt(4, material.getQuantidade());
            stmt.execute();
            return true;
        }catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Material material) {
        String sql = "UPDATE materiais SET nome=?, descricao=?, dataentrada=?, quantidade=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getDescricao());
            stmt.setDate(3, (Date) material.getDataEntrada());
            stmt.setInt(4, material.getQuantidade());
            stmt.setInt(5, material.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Material material) {
        String sql = "DELETE FROM materiais WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, material.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Material> listar() {
        String sql = "SELECT * FROM materiais";
        List<Material> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Material material = new Material();
                material.setId(resultado.getInt("id"));
                material.setNome(resultado.getString("nome"));
                material.setDescricao(resultado.getString("descricao"));
                material.setDataEntrada(resultado.getDate("dataentrada"));
                material.setQuantidade(resultado.getInt("quantidade"));
                retorno.add(material);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public Material buscar(Material material) {
        String sql = "SELECT * FROM materiais WHERE id=?";
        Material retorno = new Material();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, material.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                material.setId(resultado.getInt("id"));
                material.setNome(resultado.getString("nome"));
                material.setDescricao(resultado.getString("descricao"));
                material.setDataEntrada(resultado.getDate("dataentrada"));
                material.setQuantidade(resultado.getInt("quantidade"));
                retorno = material;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
