package com.msreem.dpcoinsgame;

import com.msreem.dpcoinsgame.panes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new TwoRobotPane(), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        // Font.getFamilies().forEach(System.out::println);
    }

    public static void main(String[] args) {
        launch();
    }
}