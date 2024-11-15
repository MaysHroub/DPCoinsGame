package com.msreem.dpcoinsgame;

import com.msreem.dpcoinsgame.panes.WelcomePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new WelcomePane(), 1200, 700);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        // Font.getFamilies().forEach(System.out::println);
    }

    public static void main(String[] args) {
        launch();
    }
}