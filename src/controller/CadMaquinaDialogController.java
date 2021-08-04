/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.stage.Stage;
import model.bean.Maquina;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author r-r20
 */
public class CadMaquinaDialogController implements Initializable {

    @FXML
    private Label labelMaquinaPatrimonio;
    @FXML
    private Label labelMaquinaSecao;
    @FXML
    private Label labelMaquinaMilitar;
    @FXML
    private TextField textFieldMaquinaPatrimonio;
    @FXML
    private TextField textFieldMaquinaSecao;
    @FXML
    private TextField textFieldMaquinaMilitar;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Maquina maquina;

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
     * @return the maquina
     */
    public Maquina getMaquina() {
        return maquina;
    }

    /**
     * @param maquina the maquina to set
     */
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
        this.textFieldMaquinaPatrimonio.setText(String.valueOf(maquina.getNumPatrimomio()));
        this.textFieldMaquinaSecao.setText(maquina.getSecaoPertencente());
        this.textFieldMaquinaMilitar.setText(maquina.getMilitarResponsavel());
    }

    @FXML
    public void handleButtonConfirmar() throws ParseException {
        maquina.setNumPatrimomio(Integer.parseInt(textFieldMaquinaPatrimonio.getText()));
        maquina.setSecaoPertencente(textFieldMaquinaSecao.getText());
        maquina.setMilitarResponsavel(textFieldMaquinaMilitar.getText());

        Icon errorIcon = UIManager.getIcon("OptionPane.informationIcon");

        int reply = JOptionPane.showConfirmDialog(null, "Deseja Imprimir o código de barra", "Cod Barras", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            Maquina.codigoBarras(maquina.getNumPatrimomio(), maquina.getSecaoPertencente());
        }

        buttonConfirmClick = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

}
