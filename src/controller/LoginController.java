package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class LoginController {

    @FXML
    public void logar(ActionEvent e){
        Main.changeScreen("inicio");
    }
}
