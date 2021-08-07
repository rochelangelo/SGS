/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import model.dao.ServicoDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

/**
 *
 * @author r-r20
 */
public class GraficoSerivocosController implements Initializable {
    
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private CategoryAxis categoriaAxis;
    @FXML
    private NumberAxis numberAxis;
    private ObservableList<String> observableListMeses = FXCollections.observableArrayList();
    
    //Atributos para Manipulacao BD
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] arrayMeses = {"JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "OUT", "SET", "NOV", "DEZ"};
        
        observableListMeses.addAll(Arrays.asList(arrayMeses));
        
        categoriaAxis.setCategories(observableListMeses);
        servicoDAO.setConnection(connection);
        
        Map<Integer, ArrayList> dados = servicoDAO.listaQuantidadeServicosMes();
        for(Map.Entry<Integer, ArrayList> dadosItem : dados.entrySet()){
            XYChart.Series<String, Integer> series = new XYChart.Series<> ();
            series.setName(dadosItem.getKey().toString());
            for(int i = 0; i < dadosItem.getValue().size(); i=i+2){
                String mes;
                Integer quantidade;
                mes = retornaNomeMes((int) dadosItem.getValue().get(i));
                quantidade = (Integer) dadosItem.getValue().get(i + 1);
                series.getData().add(new XYChart.Data<>(mes, quantidade));
            }
            barchart.getData().add(series);
        }
        
    }
    
    public String retornaNomeMes(int mes){
        switch (mes){
            case 1:
                return "JAN";
            case 2:
                return "FEV";
            case 3:
                return "MAR";
            case 4:
                return "ABR";
            case 5:
                return "MAI";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AGO";
            case 9:
                return "SET";
            case 10:
                return "OUT";
            case 11:
                return "NOV";
            case 12:
                return "DEZ";
            default:
                return "";
        }
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
    
    public void crudServico(ActionEvent e){
        Main.changeScreen("crudServico");
    }
    
}
