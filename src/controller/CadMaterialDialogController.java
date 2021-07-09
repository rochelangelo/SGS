package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.bean.Material;

/**
 * FXML Controller class
 *
 * @author r-r20
 */
public class CadMaterialDialogController implements Initializable {

    @FXML
    private Label labelMaterialNome;
    @FXML
    private Label labelMaterialDescricao;
    @FXML
    private Label labelMaterialDataEntrada;
    @FXML
    private Label labelMaterialQuantidade;
    @FXML
    private TextField textFieldMatrialNome;
    @FXML
    private TextField textFieldMatrialDecricao;
    @FXML
    private DatePicker datePickerMatrial;
    @FXML
    private TextField textFieldMatrialQuantidade;
    @FXML
    private Button buttonCadastrar;
    @FXML
    private Button buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private Material material;

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
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
        this.textFieldMatrialNome.setText(material.getNome());
        this.textFieldMatrialDecricao.setText(material.getDescricao());
        
        Date data = material.getDataEntrada();
        if(data != null){
            LocalDate date = formataLocalDate(data);
            this.datePickerMatrial.setValue(date);
        }
        
        this.textFieldMatrialQuantidade.setText(Integer.toString(material.getQuantidade()));
    }

    public Date formataData(String data) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(data);

        return date;
    }
    
    public LocalDate formataLocalDate(Date data){
        
        String date = new SimpleDateFormat("yyyy-MM-dd").format(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , formatter);
        
        return localDate;
    }

    @FXML
    public void handleButtonConfirmar() throws ParseException {
        String data = datePickerMatrial.getValue().toString();
        material.setNome(textFieldMatrialNome.getText());
        material.setDescricao(textFieldMatrialDecricao.getText());
        material.setDataEntrada(new java.sql.Date(formataData(data).getTime()));
        material.setQuantidade(Integer.parseInt(textFieldMatrialQuantidade.getText()));
        
        buttonConfirmClick = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }

}
