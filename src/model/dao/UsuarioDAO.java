/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Usuario;

/**
 *
 * @author r-r20
 */
public class UsuarioDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, graduacao, funcao, senha, login)VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getGraduacao());
            stmt.setString(3, usuario.getFuncao());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, formataDados(usuario.getLogin()));
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static String formataDados(String dado) {
        dado = dado.replaceAll(" ", "");
        return dado;
    }

    public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome=?, graduacao=?, funcao=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getGraduacao());
            stmt.setString(3, usuario.getFuncao());
            stmt.setInt(4, usuario.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterarSenha(Usuario usuario) {
        String sql = "UPDATE usuarios SET senha=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Usuario usuario) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setGraduacao(resultado.getString("graduacao"));
                usuario.setFuncao(resultado.getString("funcao"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setLogin(resultado.getString("login"));
                retorno.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Usuario buscar(Usuario usuario) {
        String sql = "SELECT * FROM usuarios WHERE id=?";
        Usuario retorno = new Usuario();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setGraduacao(resultado.getString("graduacao"));
                usuario.setFuncao(resultado.getString("funcao"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setLogin(resultado.getString("login"));
                retorno = usuario;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Boolean existenciaUsuario(String nome) {
        String sql = "SELECT * FROM usuarios WHERE nome=?";
        boolean resul = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet resultado = stmt.executeQuery();
            String nResul = resultado.getString("nome");
            if (nResul.equals(nome)) {
                resul = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resul;
    }

}
