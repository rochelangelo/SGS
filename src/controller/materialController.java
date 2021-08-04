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
import model.bean.Material;
import model.dao.MaterialDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

public class materialController implements Initializable {

    @FXML
    private TableView<Material> tableViewMaterial;
    @FXML
    private TableColumn<Material, String> tabColumnMaterialNome;
    @FXML
    private TableColumn<Material, String> tabColumnMaterialQuantidade;
    @FXML
    private Label labelMaterialId;
    @FXML
    private Label labelMaterialNome;
    @FXML
    private Label labelMaterialDescricao;
    @FXML
    private Label labelMaterialDataEntrega;
    @FXML
    private Label labelMaterialQuantidade;
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAtualizar;
    @FXML
    private Button buttonExcluir;

    private List<Material> listMaterial;
    private ObservableList<Material> ObservableListMaterial;

    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final MaterialDAO materialDAO = new MaterialDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        materialDAO.setConnection(connection);
        carregarTableViewMateriais();

        //Listen acionado sempre que um item da lista for selecionado na TableView Materias
        tableViewMaterial.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewMateriais(newValue));

    }

    public void carregarTableViewMateriais() {
        tabColumnMaterialNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabColumnMaterialQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        listMaterial = materialDAO.listar();

        ObservableListMaterial = FXCollections.observableArrayList(listMaterial);
        tableViewMaterial.setItems(ObservableListMaterial);
    }

    public void selecionarItemTableViewMateriais(Material material) {
        if (material != null) {
            labelMaterialId.setText(String.valueOf(material.getId()));
            labelMaterialNome.setText(material.getNome());
            labelMaterialDescricao.setText(material.getDescricao());
            labelMaterialDataEntrega.setText(melhoraData(material.getDataEntrada()));
            labelMaterialQuantidade.setText(String.valueOf(material.getQuantidade()));
        } else {
            labelMaterialId.setText("");
            labelMaterialNome.setText("");
            labelMaterialDescricao.setText("");
            labelMaterialDataEntrega.setText("");
            labelMaterialQuantidade.setText("");
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
    
    public void buttonFechaJanela(){
        Main.changeScreen("inicio");
    }
    public void crudUsuario(ActionEvent e){
        Main.changeScreen("crudUsuario");
    }
    public void crudServico(ActionEvent e){
        Main.changeScreen("crudServico");
    }
    public void crudMaquina(ActionEvent e){
        Main.changeScreen("crudMaquina");
    }
    
    @FXML
    public void handleButtonInserir() throws IOException{
        Material material = new Material();
        boolean buttonConfirmarClick = showCadMaterialDialog(material);
        if(buttonConfirmarClick){
            materialDAO.inserir(material);
            carregarTableViewMateriais();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException{
        Material material = tableViewMaterial.getSelectionModel().getSelectedItem();
        if(material != null){
            boolean buttonConfirmarClick = showCadMaterialDialog(material);
            if(buttonConfirmarClick){
                materialDAO.alterar(material);
                carregarTableViewMateriais();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar um Material!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemove() throws IOException{
        Material material = tableViewMaterial.getSelectionModel().getSelectedItem();
        if(material != null){
            materialDAO.remover(material);
            carregarTableViewMateriais();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecionar um Material!");
            alert.show();
        }
    }
    
    public boolean showCadMaterialDialog(Material material) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadMaterialDialogController.class.getResource("../view/cadMaterialDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        
        Stage dialogStage = new Stage();
        dialogStage.setTitle("SGS-Cadastro Material");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        CadMaterialDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMaterial(material);
        
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmClick();
    }
    

}
