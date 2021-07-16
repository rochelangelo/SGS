/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Servico;

/**
 *
 * @author r-r20
 */
public class CadServicoDialogController implements Initializable {
    
    @FXML
    private Label labelServicoTecResp;
    @FXML
    private Label labelServicoSecReq;
    @FXML
    private Label labelServicoObservacoes;
    @FXML
    private TextField textFieldServicoTecRep;
    @FXML
    private TextField textFieldServicoSecReq;
    @FXML
    private TextField textFieldServicoObs;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Servico servico;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
        this.textFieldServicoSecReq.setText(servico.getSecaoRequerente());
        this.textFieldServicoTecRep.setText(servico.getTecnicoResponsavel());
        this.textFieldServicoObs.setText(servico.getObservacao());
    }
    
    @FXML
    public void handleButtonConfirmar() throws ParseException {
        
        Date data = new Date();
        servico.setDataEntrada(data);
        servico.setSecaoRequerente(textFieldServicoSecReq.getText());
        servico.setTecnicoResponsavel(textFieldServicoTecRep.getText());
        servico.setObservacao(textFieldServicoObs.getText());
        servico.setSituacao("EM AGUARDO"); // "EM AGUARDO" "EM MANUTENCAO" "FINALIZADO" "SEM SOLUCAO"
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
    
    
}
