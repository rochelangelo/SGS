package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    private static Scene loginScreen;
    private static Scene inicioScreen;
    private static Scene crudMaterial;
    private static Scene crudUsuario;
    private static Scene crudServico;
    private static Scene crudMaquina;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("GERENINFOR");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        loginScreen = new Scene(fxmlLogin, 350, 200);

        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("../view/inicio.fxml"));
        inicioScreen = new Scene(fxmlInicio, 1280, 768);
        
        Parent fxmlCrudMaterial = FXMLLoader.load(getClass().getResource("../view/crudMaterial.fxml"));
        crudMaterial = new Scene(fxmlCrudMaterial, 1280, 768);
        
        Parent fxmlCrudUsuario = FXMLLoader.load(getClass().getResource("../view/crudUsuario.fxml"));
        crudUsuario = new Scene(fxmlCrudUsuario, 1280, 768);
        
        Parent fxmlCrudServico = FXMLLoader.load(getClass().getResource("../view/crudServico.fxml"));
        crudServico = new Scene(fxmlCrudServico, 1280, 768);
        
        Parent fxmlCrudMaquina = FXMLLoader.load(getClass().getResource("../view/crudMaquina.fxml"));
        crudMaquina = new Scene(fxmlCrudMaquina, 1280, 768);
        


        primaryStage.setScene(loginScreen);
        primaryStage.show();
    }

    public static void changeScreen(String tela){
        switch (tela){
            case "inicio":
                stage.setScene(inicioScreen);
                stage.setResizable(false);
                stage.setMaximized(true);
                break;
               
            case "crudMaterial":
                stage.setScene(crudMaterial);
                stage.setResizable(false);
                stage.setMaximized(true);
                break;
                
            case "crudUsuario":
                stage.setScene(crudUsuario);
                stage.setResizable(false);
                stage.setMaximized(true);
                break;
                
            case "crudServico":
                stage.setScene(crudServico);
                stage.setResizable(false);
                stage.setMaximized(true);
                break;
                
            case "crudMaquina":
                stage.setScene(crudMaquina);
                stage.setResizable(false);
                stage.setMaximized(true);
                break;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
