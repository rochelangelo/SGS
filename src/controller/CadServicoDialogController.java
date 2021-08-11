/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bean.Maquina;
import model.bean.Servico;
import model.bean.Usuario;
import model.dao.MaquinaDAO;
import model.dao.UsuarioDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

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
    private ComboBox<String> tecnico;
    @FXML
    private TextField textFieldServicoSecReq;
    @FXML
    private TextField textFieldServicoObs;
    @FXML
    private TextField textFieldServicoPatrimonioMaquina;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Servico servico;
    
    private List<String> listUsuario;
    private ObservableList<String> ObservableListUsuario;
    
    
     //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MaquinaDAO maquinaDAO = new MaquinaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maquinaDAO.setConnection(connection);
        usuarioDAO.setConnection(connection);
        listUsuario = usuarioDAO.listarNome();
        ObservableListUsuario = FXCollections.observableList(listUsuario);
        
        this.tecnico.setItems(ObservableListUsuario);
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
        this.textFieldServicoPatrimonioMaquina.setText(String.valueOf(servico.getPatrimonioMaquina()));
        this.textFieldServicoSecReq.setText(servico.getSecaoRequerente());
        this.tecnico.setValue(servico.getTecnicoResponsavel());
        this.textFieldServicoObs.setText(servico.getObservacao());
    }
    
    @FXML
    public void populaForm(ActionEvent e) throws IOException{
        if(maquinaDAO.buscaExistecia(Integer.parseInt(textFieldServicoPatrimonioMaquina.getText()))){
            Maquina maquina = maquinaDAO.buscarbyPatrimonio(Integer.parseInt(textFieldServicoPatrimonioMaquina.getText()));
            this.textFieldServicoSecReq.setText(maquina.getSecaoPertencente());
        }else{
            inseriMaquina(Integer.parseInt(textFieldServicoPatrimonioMaquina.getText()));
        }
    }
    
    public void inseriMaquina(int patrimonio) throws IOException{
        Maquina maquina = new Maquina();
        boolean buttonConfirmarClick = showCadMaquinaDialog(maquina, patrimonio);
        if(buttonConfirmarClick){
            maquinaDAO.inserir(maquina);
            Maquina.codigoBarras(maquina.getNumPatrimomio(), maquina.getSecaoPertencente());
        }
    }
    
    public boolean showCadMaquinaDialog(Maquina maquina, int patrimonio) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadMaquinaDialogController.class.getResource("../view/cadMaquinaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Maquina");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        CadMaquinaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        maquina.setNumPatrimomio(patrimonio);
        controller.setMaquina(maquina);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }

    
    @FXML
    public void handleButtonConfirmar() throws ParseException {
        
        Date data = new Date();
        servico.setDataEntrada(new java.sql.Date(data.getTime()));
        servico.setDataSaida(null);
        servico.setSecaoRequerente(textFieldServicoSecReq.getText());
        servico.setTecnicoResponsavel(tecnico.getValue());
        servico.setObservacao(textFieldServicoObs.getText());
        servico.setPatrimonioMaquina(Integer.parseInt(textFieldServicoPatrimonioMaquina.getText()));
        servico.setSituacao("EM AGUARDO"); // "EM AGUARDO" "EM MANUTENCAO" "FINALIZADO" "SEM SOLUCAO"
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
    
    
}
