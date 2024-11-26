package org.conava.dsv.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 1300, 800));
        primaryStage.setTitle("Data Structure Visualizer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}