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
import javax.swing.JOptionPane;
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
    private TableView<Servico> tableViewServicoAguardo;
    @FXML
    private TableView<Servico> tableViewServicoManutencao;
    @FXML
    private TableView<Servico> tableViewServicoFinalizado;
    @FXML
    private TableView<Servico> tableViewServicoSolucao;
    
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSecao;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoTecnico;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoIdMaquina;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSituacao;
    
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSecao1;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoTecnico1;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoIdMaquina1;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSituacao1;
    
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSecao2;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoTecnico2;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoIdMaquina2;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSituacao2;
    
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSecao3;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoTecnico3;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoIdMaquina3;
    @FXML
    private TableColumn<Servico, String> tabColumnServicoSituacao3;
    
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
    @FXML
    private Button buttonDarBaixa;
    @FXML
    private Button buttonAtualizar;
    @FXML
    private Button buttonInserir;

    private List<Servico> listServicoAguardo;
    private List<Servico> listServicoManutencao;
    private List<Servico> listServicoFinalizado;
    private List<Servico> listServicoSolucao;
    private ObservableList<Servico> ObservableListServicoAguardo;
    private ObservableList<Servico> ObservableListServicoMatutencao;
    private ObservableList<Servico> ObservableListServicoFinalizado;
    private ObservableList<Servico> ObservableListServicoSolucao;
    
    private String tipoServico;

    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        servicoDAO.setConnection(connection);
        carregarTableViewServico();

        //Listen acionado sempre que um item da lista for selecionado na TableView Materias
        tableViewServicoAguardo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));
        tableViewServicoManutencao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));
        tableViewServicoFinalizado.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));
        tableViewServicoSolucao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));

    }

    public void buttonFechaJanela() {
        Main.changeScreen("inicio");
    }

    public void crudMaterial(ActionEvent e) {
        Main.changeScreen("crudMaterial");
    }

    public void crudUsuario(ActionEvent e) {
        Main.changeScreen("crudUsuario");
    }

    public void crudMaquina(ActionEvent e) {
        Main.changeScreen("crudMaquina");
    }
    
    public void graficos(ActionEvent e){
        Main.changeScreen("graficos");
    }

    public void carregarTableViewServico() {
        tabColumnServicoSecao.setCellValueFactory(new PropertyValueFactory<>("secaoRequerente"));
        tabColumnServicoTecnico.setCellValueFactory(new PropertyValueFactory<>("tecnicoResponsavel"));
        tabColumnServicoIdMaquina.setCellValueFactory(new PropertyValueFactory<>("patrimonioMaquina"));
        tabColumnServicoSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        
        tabColumnServicoSecao1.setCellValueFactory(new PropertyValueFactory<>("secaoRequerente"));
        tabColumnServicoTecnico1.setCellValueFactory(new PropertyValueFactory<>("tecnicoResponsavel"));
        tabColumnServicoIdMaquina1.setCellValueFactory(new PropertyValueFactory<>("patrimonioMaquina"));
        tabColumnServicoSituacao1.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        
        tabColumnServicoSecao2.setCellValueFactory(new PropertyValueFactory<>("secaoRequerente"));
        tabColumnServicoTecnico2.setCellValueFactory(new PropertyValueFactory<>("tecnicoResponsavel"));
        tabColumnServicoIdMaquina2.setCellValueFactory(new PropertyValueFactory<>("patrimonioMaquina"));
        tabColumnServicoSituacao2.setCellValueFactory(new PropertyValueFactory<>("situacao"));
        
        tabColumnServicoSecao3.setCellValueFactory(new PropertyValueFactory<>("secaoRequerente"));
        tabColumnServicoTecnico3.setCellValueFactory(new PropertyValueFactory<>("tecnicoResponsavel"));
        tabColumnServicoIdMaquina3.setCellValueFactory(new PropertyValueFactory<>("patrimonioMaquina"));
        tabColumnServicoSituacao3.setCellValueFactory(new PropertyValueFactory<>("situacao"));

        listServicoAguardo = servicoDAO.listarEmAguardo();
        ObservableListServicoAguardo = FXCollections.observableArrayList(listServicoAguardo);
        tableViewServicoAguardo.setItems(ObservableListServicoAguardo);
        
        listServicoFinalizado = servicoDAO.listarFinalizado();
        ObservableListServicoFinalizado = FXCollections.observableArrayList(listServicoFinalizado);
        tableViewServicoFinalizado.setItems(ObservableListServicoFinalizado);
        
        listServicoManutencao = servicoDAO.listarEmManutencao();
        ObservableListServicoMatutencao = FXCollections.observableArrayList(listServicoManutencao);
        tableViewServicoManutencao.setItems(ObservableListServicoMatutencao);
        
        listServicoSolucao = servicoDAO.listarSemSolucao();
        ObservableListServicoSolucao = FXCollections.observableArrayList(listServicoSolucao);
        tableViewServicoSolucao.setItems(ObservableListServicoSolucao);
    }

    public void selecionarItemTableViewUsuario(Servico servico) {
        
        this.tipoServico = servico.getSituacao();
        
        if (servico != null) {
            labelServicoDataEntrada.setText(melhoraData(servico.getDataEntrada()));
            if (servico.getDataSaida() != null) {
                labelServicoDataSaida.setText(melhoraData(servico.getDataSaida()));
            } else {
                labelServicoDataSaida.setText("-");
            }
            labelServicoTecResp.setText(servico.getTecnicoResponsavel());
            labelServicoSecPert.setText(servico.getSecaoRequerente());
            labelServicoObservacoes.setText(servico.getObservacao());
            labelServicoSituacao.setText(servico.getSituacao());
            labelServicoIdMaquina.setText(String.valueOf(servico.getPatrimonioMaquina()));
            
            String situacao = servico.getSituacao();
            if(situacao.equals("FINALIZADO") || situacao.equals("SEM SOLUÇÃO")){
                buttonDarBaixa.setDisable(true);
                buttonInserir.setDisable(true);
                buttonAtualizar.setDisable(true);
            }else{
                buttonDarBaixa.setDisable(false);
                buttonInserir.setDisable(false);
                buttonAtualizar.setDisable(false);
            }
            
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

    public String melhoraData(Date data) {
        String dataMelhorada = "";

        String dataAntiga = String.valueOf(data);

        //AAAA-MM-DD
        String d[] = dataAntiga.split("-");

        dataMelhorada = d[2] + "-" + d[1] + "-" + d[0];

        return dataMelhorada;
    }

    @FXML
    public void handleButtonInserir() throws IOException {
        Servico servico = new Servico();
        boolean buttonConfirmarClick = showCadServicoDialog(servico);
        if (buttonConfirmarClick) {
            servicoDAO.inserir(servico);
            carregarTableViewServico();
        }
    }
    
    @FXML
    public void handleButtonRemove() throws IOException { 
        if (this.tipoServico.equals("EM AGUARDO")) {
            Servico servico = tableViewServicoAguardo.getSelectionModel().getSelectedItem();
            if (servico != null) {
                servicoDAO.remover(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("FINALIZADO")){
            Servico servico = tableViewServicoFinalizado.getSelectionModel().getSelectedItem();
            if (servico != null) {
                servicoDAO.remover(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("EM MANUTENCAO")){
            Servico servico = tableViewServicoManutencao.getSelectionModel().getSelectedItem();
            if (servico != null) {
                servicoDAO.remover(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("SEM SOLUÇÃO")){
            Servico servico = tableViewServicoSolucao.getSelectionModel().getSelectedItem();
            if (servico != null) {
                servicoDAO.remover(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }
    }

    public void handleButtonAlterarObsSituacao() throws IOException {
        if (this.tipoServico.equals("EM AGUARDO")) {
            Servico servico = tableViewServicoAguardo.getSelectionModel().getSelectedItem();
            if (servico != null) {
                boolean buttonConfirmarClick = showAltCadServicoDialog(servico);
                if (buttonConfirmarClick) {
                    servicoDAO.alterarObservacao(servico);
                    carregarTableViewServico();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("FINALIZADO")){
            Servico servico = tableViewServicoFinalizado.getSelectionModel().getSelectedItem();
            if (servico != null) {
                boolean buttonConfirmarClick = showAltCadServicoDialog(servico);
                if (buttonConfirmarClick) {
                    servicoDAO.alterarObservacao(servico);
                    carregarTableViewServico();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("EM MANUTENCAO")){
            Servico servico = tableViewServicoManutencao.getSelectionModel().getSelectedItem();
            if (servico != null) {
                boolean buttonConfirmarClick = showAltCadServicoDialog(servico);
                if (buttonConfirmarClick) {
                    servicoDAO.alterarObservacao(servico);
                    carregarTableViewServico();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if (this.tipoServico.equals("SEM SOLUÇÃO")) {
            Servico servico = tableViewServicoSolucao.getSelectionModel().getSelectedItem();
            if (servico != null) {
                boolean buttonConfirmarClick = showAltCadServicoDialog(servico);
                if (buttonConfirmarClick) {
                    servicoDAO.alterarObservacao(servico);
                    carregarTableViewServico();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }
    }

    public void darBaixa() {
        if (this.tipoServico.equals("EM AGUARDO")) {
            Servico servico = tableViewServicoAguardo.getSelectionModel().getSelectedItem();
            System.out.println(servico.getId());
            if (servico != null) {
                String[] situacao = new String[2];
                situacao[0] = "FINALIZADO";
                situacao[1] = "SEM SOLUÇÃO";

                int reply = JOptionPane.showConfirmDialog(null, "    TEVE SOLUÇÃO?", "SITUAÇÃO", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    servico.setSituacao(situacao[0]);
                } else {
                    servico.setSituacao(situacao[1]);
                }

                Date data = new Date();
                servico.setDataSaida(new java.sql.Date(data.getTime()));
                servicoDAO.alterarSituação(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }else if(this.tipoServico.equals("EM MANUTENCAO")){
            Servico servico = tableViewServicoManutencao.getSelectionModel().getSelectedItem();
            if (servico != null) {
                String[] situacao = new String[2];
                situacao[0] = "FINALIZADO";
                situacao[1] = "SEM SOLUÇÃO";

                int reply = JOptionPane.showConfirmDialog(null, "    TEVE SOLUÇÃO?", "SITUAÇÃO", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.YES_OPTION) {
                    servico.setSituacao(situacao[0]);
                } else {
                    servico.setSituacao(situacao[1]);
                }

                Date data = new Date();
                servico.setDataSaida(new java.sql.Date(data.getTime()));
                servicoDAO.alterarSituação(servico);
                carregarTableViewServico();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecionar um Serviço!");
                alert.show();
            }
        }
    }

    public boolean showAltCadServicoDialog(Servico servico) throws IOException {
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

    public boolean showCadServicoDialog(Servico servico) throws IOException {
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