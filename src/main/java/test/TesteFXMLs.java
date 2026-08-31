package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesteFXMLs extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("TesteFXMLs iniciando: carregando login.fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Teste FXML - Login");
        primaryStage.show();
    }
}
