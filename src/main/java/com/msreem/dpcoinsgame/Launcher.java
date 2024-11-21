package com.msreem.dpcoinsgame;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.panes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new RulesPane(), 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
        NavigationManager.init(scene);
        //NavigationManager.getInstance().navigateTo(PaneTag.DP_GAME);
    }

    public static void main(String[] args) {
        launch();
    }
}