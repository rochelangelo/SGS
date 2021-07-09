/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TextField textFieldUsuarioGraduacao;
    @FXML
    private TextField textFieldUsuarioFuncao;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        this.textFieldUsuarioGraduacao.setText(usuario.getGraduacao());
        this.textFieldUsuarioFuncao.setText(usuario.getFuncao());
    }
    
    @FXML
    public void handleButtonConfirmar() throws ParseException {
        usuario.setNome(textFieldUsuarioNome.getText());
        usuario.setGraduacao(textFieldUsuarioGraduacao.getText());
        usuario.setFuncao(textFieldUsuarioFuncao.getText());
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
}
