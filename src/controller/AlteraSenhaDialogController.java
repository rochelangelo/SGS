/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.bean.Usuario;

/**
 *
 * @author r-r20
 */
public class AlteraSenhaDialogController {
    
    @FXML
    private Label labelSenhaAtual;
    @FXML
    private Label labelNovaSenha;
    @FXML
    private Label labelConfirmacao;
    @FXML
    private PasswordField passwordSenhaAtual;
    @FXML
    private PasswordField passwordNovaSenha;
    @FXML
    private PasswordField passwordConfirmacao;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Usuario usuario;

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
    }
    
    
    @FXML
    public void handleButtonConfirmar() throws ParseException {
        String senhaAtual = usuario.getSenha();        
        String senhaAtualDigitada = passwordSenhaAtual.getText();
        String novaSenha = passwordNovaSenha.getText();
        String confirmacao = passwordConfirmacao.getText();
        
        if(senhaAtual.equals(senhaAtualDigitada)){
            if(!senhaAtual.equals(novaSenha)){
                if(novaSenha.equals(confirmacao)){
                    usuario.setSenha(passwordNovaSenha.getText());
                    
                    buttonConfirmClick = true;
                    dialogStage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Novas senhas não CORRESPONDEM");
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nova senha IGUAl a ATUAL");
                alert.show(); 
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Senha atual não CORRESPONDENTE");
            alert.show();
        }
        
        
    }
    
    @FXML
    public void handleButtonConfirma() throws ParseException {
        String senhaAtual = usuario.getSenha();        
        String senhaAtualDigitada = passwordSenhaAtual.getText();
        String novaSenha = passwordNovaSenha.getText();
        String confirmacao = passwordConfirmacao.getText();
        
        if(senhaAtual.equals(senhaAtualDigitada)){
            if(!senhaAtual.equals(novaSenha)){
                if(novaSenha.equals(confirmacao)){
                    usuario.setSenha(passwordNovaSenha.getText());
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Novas senhas não CORRESPONDEM");
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nova senha IGUAl a ATUAL");
                alert.show(); 
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Senha atual não CORRESPONDENTE");
            alert.show();
        }
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
    
    
}
