/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.bean.Maquina;
import model.dao.MaquinaDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;

/**
 *
 * @author r-r20
 */
public class MaquinaController implements Initializable {
    
    @FXML
    private TableView<Maquina> tableViewMaquina;
    @FXML
    private TableColumn<Maquina, String> tabColumnMaquinaSecao;
    @FXML
    private TableColumn<Maquina, String> tabColumnMaquinaMilitar;
    @FXML
    private Label labelMaquinaId;
    @FXML
    private Label labelMaquinaPatrimonio;
    @FXML
    private Label labelMaquinaSecao;
    @FXML
    private Label labelMaquinaMilitar;
    
    private List<Maquina> listMaquina;
    private ObservableList<Maquina> ObservableListMaquina;

    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MaquinaDAO maquinaDAO = new MaquinaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maquinaDAO.setConnection(connection);
        carregarTableViewMaquina();

        //Listen acionado sempre que um item da lista for selecionado na TableView
        tableViewMaquina.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewMaquina(newValue));

    }
    
    public void buttonFechaJanela(){
        Main.changeScreen("inicio");
    }
    public void crudUsuario(ActionEvent e){
        Main.changeScreen("crudUsuario");
    }
    public void crudServico(ActionEvent e){
        Main.changeScreen("crudServico");
    }
    public void crudMaterial(ActionEvent e){
        Main.changeScreen("crudMaterial ");
    }
    public void graficos(ActionEvent e){
        Main.changeScreen("graficos");
    }
    
    public void selecionarItemTableViewMaquina(Maquina maquina) {
        if (maquina != null) {
            labelMaquinaId.setText(String.valueOf(maquina.getId()));
            labelMaquinaPatrimonio.setText(String.valueOf(maquina.getNumPatrimomio()));
            labelMaquinaSecao.setText(maquina.getSecaoPertencente());
            labelMaquinaMilitar.setText(maquina.getMilitarResponsavel());
        } else {
            labelMaquinaId.setText("");
            labelMaquinaPatrimonio.setText("");
            labelMaquinaSecao.setText("");
            labelMaquinaMilitar.setText("");
        }
    }

    private void carregarTableViewMaquina() {
        tabColumnMaquinaSecao.setCellValueFactory(new PropertyValueFactory<>("secaoPertencente"));
        tabColumnMaquinaMilitar.setCellValueFactory(new PropertyValueFactory<>("militarResponsavel"));

        listMaquina = maquinaDAO.listar();

        ObservableListMaquina = FXCollections.observableArrayList(listMaquina);
        tableViewMaquina.setItems(ObservableListMaquina);
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Maquina maquina = new Maquina();
        boolean buttonConfirmarClick = showCadMaquinaDialog(maquina);
        if(buttonConfirmarClick){
            maquinaDAO.inserir(maquina);
            carregarTableViewMaquina();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException{
        Maquina maquina = tableViewMaquina.getSelectionModel().getSelectedItem();
        if(maquina != null){
            boolean buttonConfirmarClick = showCadMaquinaDialog(maquina);
            if(buttonConfirmarClick){
                maquinaDAO.alterar(maquina);
                carregarTableViewMaquina();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar uma Maquina!");
            alert.show();
        }
    }
    
    
    @FXML
    public void handleButtonRemove() throws IOException{
        Maquina maquina = tableViewMaquina.getSelectionModel().getSelectedItem();
        if(maquina != null){
            maquinaDAO.remover(maquina);
            carregarTableViewMaquina();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar uma Maquina!");
            alert.show();
        }
    }
    
    @FXML
    public void ObterCodigoBarras() throws IOException{
        Maquina maquina = tableViewMaquina.getSelectionModel().getSelectedItem();
        if(maquina != null){
            Maquina.codigoBarras(maquina.getNumPatrimomio(), maquina.getSecaoPertencente());
            carregarTableViewMaquina();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar uma Maquina!");
            alert.show();
        }
    }

    public boolean showCadMaquinaDialog(Maquina maquina) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadMaquinaDialogController.class.getResource("../view/cadMaquinaDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Maquina");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        CadMaquinaDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMaquina(maquina);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }
    
}
