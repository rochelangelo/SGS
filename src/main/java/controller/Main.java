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

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("GERENINFOR");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        loginScreen = new Scene(fxmlLogin, 350, 200);

        Parent fxmlInicio = FXMLLoader.load(getClass().getResource("../view/inicio.fxml"));
        inicioScreen = new Scene(fxmlInicio, 1280, 768);


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
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
