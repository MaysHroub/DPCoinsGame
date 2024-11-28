package com.msreem.dpcoinsgame;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.panes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// This is the main class which launches the application.
public class Launcher extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new StartPane(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);

        NavigationManager.init(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}