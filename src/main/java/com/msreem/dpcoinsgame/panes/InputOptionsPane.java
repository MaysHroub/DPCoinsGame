package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.util.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InputOptionsPane extends BorderPane {

    private Button loadFromFileBtn, enterManuallyBtn, generateRandomBtn;

    public InputOptionsPane() {
        init();
    }

    private void init() {

        /*
            TO-DO: Add a label to tell the user about the file format
        */

        Label label = new Label("SELECT YOUR INPUT METHOD");

        loadFromFileBtn = new Button("Load data from file");
        enterManuallyBtn = new Button("Enter data manually");
        generateRandomBtn = new Button("Generate random data");
        Button backBtn = new Button("BACK");

        VBox centerVB = new VBox(40, label, loadFromFileBtn, enterManuallyBtn, generateRandomBtn);
        centerVB.setAlignment(Pos.CENTER);

        setCenter(centerVB);
        setTop(backBtn);

        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        setPadding(new Insets(20));

        Animation.installFadeTransition(label, 1.5);
        Animation.installTranslateYTransition(loadFromFileBtn, 1, loadFromFileBtn.getTranslateY()+200, loadFromFileBtn.getTranslateY());
        Animation.installTranslateYTransition(enterManuallyBtn, 1.2, loadFromFileBtn.getTranslateY()+150, loadFromFileBtn.getTranslateY());
        Animation.installTranslateYTransition(generateRandomBtn, 1.4, loadFromFileBtn.getTranslateY()+100, loadFromFileBtn.getTranslateY());
    }

}
