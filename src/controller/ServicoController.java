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
import model.bean.Servico;
import model.dao.ServicoDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

/**
 *
 * @author r-r20
 */
public class ServicoController implements Initializable {
    
    @FXML
    private TableView<Servico> tableViewServico;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSecao;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoTecnico;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoIdMaquina;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSituacao;
    @FXML
    private Label labelServicoDataEntrada;
    @FXML
    private Label labelServicoDataSaida;
    @FXML
    private Label labelServicoTecResp;
    @FXML
    private Label labelServicoSecPert;
    @FXML
    private Label labelServicoObservacoes;
    @FXML
    private Label labelServicoSituacao;
    @FXML
    private Label labelServicoIdMaquina;
    
    private List<Servico> listServico;
    private ObservableList<Servico> ObservableListServico;

    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        carregarTableViewServico();

        //Listen acionado sempre que um item da lista for selecionado na TableView Materias
        tableViewServico.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));

    }
    
    public void buttonFechaJanela(){
        Main.changeScreen("inicio");
    }
    public void crudMaterial(ActionEvent e){
        Main.changeScreen("crudMaterial");
    }
    public void crudUsuario(ActionEvent e){
        Main.changeScreen("crudUsuario");
    }
    
    public void carregarTableViewServico() {
        tabColumnServicoSecao.setCellValueFactory(new PropertyValueFactory<>("secaoresponsavel"));
        tabColumnServicoTecnico.setCellValueFactory(new PropertyValueFactory<>("tecnicoresponsavel"));
        tabColumnServicoIdMaquina.setCellValueFactory(new PropertyValueFactory<>("idmaquina"));
        tabColumnServicoSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        listServico = servicoDAO.listar();

        ObservableListServico = FXCollections.observableArrayList(listServico);
        tableViewServico.setItems(ObservableListServico);
    }

    public void selecionarItemTableViewUsuario(Servico servico) {
        if (servico != null) {
            labelServicoDataEntrada.setText(String.valueOf(servico.getDataEntrada()));
            labelServicoDataSaida.setText(String.valueOf(servico.getDataSaida()));
            labelServicoTecResp.setText(servico.getTecnicoResponsavel());
            labelServicoSecPert.setText(servico.getSecaoRequerente());
            labelServicoObservacoes.setText(servico.getObservacao());
            labelServicoSituacao.setText(servico.getSituacao());
            labelServicoIdMaquina.setText(String.valueOf(servico.getIdMaquina()));
        } else {
            labelServicoDataEntrada.setText("");
            labelServicoDataSaida.setText("");
            labelServicoTecResp.setText("");
            labelServicoSecPert.setText("");
            labelServicoObservacoes.setText("");
            labelServicoSituacao.setText("");
            labelServicoIdMaquina.setText("");
        }
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Servico servico = new Servico();
        boolean buttonConfirmarClick = showCadServicoDialog(servico);
        if(buttonConfirmarClick){
            servicoDAO.inserir(servico);
            carregarTableViewServico();
        }
    }
    
    
    
    
    public boolean showCadServicoDialog(Servico servico) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadServicoDialogController.class.getResource("../view/cadServicoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Serviço");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        CadServicoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setServico(servico);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }
    
}
