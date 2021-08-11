/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Usuario;

/**
 * FXML Controller class
 *
 * @author r-r20
 */
public class CadUsuarioDialogController implements Initializable {
    
    @FXML
    private Label labelUsuarioNome;
    @FXML
    private Label labelUsuarioGraduacao;
    @FXML
    private Label labelUsuarioFuncao;
    @FXML
    private TextField textFieldUsuarioNome;
    @FXML
    private TextField textFieldUsuarioFuncao;
    @FXML
    private ComboBox<String> graduacao;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;
    
    
    private List<String> listGraduacao = new ArrayList<String>();
    private ObservableList<String> ObservableListGraduacao;
     

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listGraduacao.add("1º Tenente");
        listGraduacao.add("2º Tenente");
        listGraduacao.add("1º Sargento");
        listGraduacao.add("2º Sargento");
        listGraduacao.add("3º Sargento");
        listGraduacao.add("Cabo");
        listGraduacao.add("Soldado");
        
        
        ObservableListGraduacao = FXCollections.observableList(listGraduacao);
        
        this.graduacao.setItems(ObservableListGraduacao);
    }    

    /**
     * @return the dialogStage
     */
    public Stage getDialogStage() {
        return dialogStage;
    }

    /**
     * @param dialogStage the dialogStage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * @return the buttonConfirmClick
     */
    public boolean isButtonConfirmClick() {
        return buttonConfirmClick;
    }

    /**
     * @param buttonConfirmClick the buttonConfirmClick to set
     */
    public void setButtonConfirmClick(boolean buttonConfirmClick) {
        this.buttonConfirmClick = buttonConfirmClick;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.textFieldUsuarioNome.setText(usuario.getNome());
        this.graduacao.setValue(usuario.getGraduacao());
        this.textFieldUsuarioFuncao.setText(usuario.getFuncao());
    }
    
    @FXML
    public void handleButtonConfirmar() throws ParseException {
        String login = textFieldUsuarioNome.getText().toLowerCase();
        
        usuario.setNome(textFieldUsuarioNome.getText());
        usuario.setGraduacao(graduacao.getSelectionModel().getSelectedItem());
        usuario.setFuncao(textFieldUsuarioFuncao.getText());
        usuario.setSenha(login);
        usuario.setLogin(login);
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
}
