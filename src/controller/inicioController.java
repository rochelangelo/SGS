/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.Material;
import model.dao.MaterialDAO;
import model.dao.database.Database;
import model.dao.database.DatabaseFactory;

/**
 *
 * @author r-r20
 */
public class inicioController {

    public void crudMaterial(ActionEvent e){
        Main.changeScreen("crudMaterial");
    }

}
