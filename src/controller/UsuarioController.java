/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bean.Usuario;
import model.dao.MaterialDAO;
import model.dao.UsuarioDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

/**
 * FXML Controller class
 *
 * @author r-r20
 */
public class UsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tableViewUsuario;
    @FXML
    private TableColumn<Usuario, String> tabColumnUsuarioNome;
    @FXML
    private TableColumn<Usuario, String> tabColumnUsuarioGraduacao;
    @FXML
    private Label labelUsuarioId;
    @FXML
    private Label labelUsuarioNome;
    @FXML
    private Label labelUsuarioGraduacao;
    @FXML
    private Label labelUsuarioFuncao;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAtualizar;
    @FXML
    private Button buttonExcluir;

    private List<Usuario> listUsuario;
    private ObservableList<Usuario> ObservableListUsuario;

    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO.setConnection(connection);
        carregarTableViewUsuarios();

        //Listen acionado sempre que um item da lista for selecionado na TableView Materias
        tableViewUsuario.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));

    }

    public void carregarTableViewUsuarios() {
        tabColumnUsuarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabColumnUsuarioGraduacao.setCellValueFactory(new PropertyValueFactory<>("graduacao"));

        listUsuario = usuarioDAO.listar();

        ObservableListUsuario = FXCollections.observableArrayList(listUsuario);
        tableViewUsuario.setItems(ObservableListUsuario);
    }

    public void selecionarItemTableViewUsuario(Usuario usuario) {
        if (usuario != null) {
            labelUsuarioId.setText(String.valueOf(usuario.getId()));
            labelUsuarioNome.setText(usuario.getNome());
            labelUsuarioGraduacao.setText(usuario.getGraduacao());
            labelUsuarioFuncao.setText(usuario.getFuncao());
        } else {
            labelUsuarioId.setText("");
            labelUsuarioNome.setText("");
            labelUsuarioGraduacao.setText("");
            labelUsuarioFuncao.setText("");
        }
    }
    
    public void buttonFechaJanela(){
        Main.changeScreen("inicio");
    }
    public void crudMaterial(ActionEvent e){
        Main.changeScreen("crudMaterial");
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Usuario usuario = new Usuario();
        boolean buttonConfirmarClick = showCadUsuarioDialog(usuario);
        if(buttonConfirmarClick){
            usuarioDAO.inserir(usuario);
            carregarTableViewUsuarios();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException{
        Usuario usuario = tableViewUsuario.getSelectionModel().getSelectedItem();
        if(usuario != null){
            boolean buttonConfirmarClick = showCadUsuarioDialog(usuario);
            if(buttonConfirmarClick){
                usuarioDAO.alterar(usuario);
                carregarTableViewUsuarios();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar um Usuario!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonAlterarSenha() throws IOException{
        Usuario usuario = tableViewUsuario.getSelectionModel().getSelectedItem();
        if(usuario != null){
            boolean buttonConfirmarClick = showAltSenhaDialog(usuario);
            if(buttonConfirmarClick){
                usuarioDAO.alterarSenha(usuario);
                carregarTableViewUsuarios();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar um Usuario!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemove() throws IOException{
        Usuario usuario = tableViewUsuario.getSelectionModel().getSelectedItem();
        if(usuario != null){
            usuarioDAO.remover(usuario);
            carregarTableViewUsuarios();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar um Usuario!");
            alert.show();
        }
    }
    
    public boolean showCadUsuarioDialog(Usuario usuario) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadUsuarioDialogController.class.getResource("../view/cadUsuarioDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Usuario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        CadUsuarioDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUsuario(usuario);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }
    
    public boolean showAltSenhaDialog(Usuario usuario) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AlteraSenhaDialogController.class.getResource("../view/alteraSenhaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Usuario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        AlteraSenhaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUsuario(usuario);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }
    
}
