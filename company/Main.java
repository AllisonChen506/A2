package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
	// write your code here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = Admin.getInstance().getBorderpane();
        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Control Panel");
        primaryStage.show();

    }
}
