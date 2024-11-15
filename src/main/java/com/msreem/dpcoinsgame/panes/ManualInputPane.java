package com.msreem.dpcoinsgame.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ManualInputPane extends BorderPane {

    public ManualInputPane() {
        init();
    }

    private void init() {
        Label promptLabel = new Label("Enter a group of even number of coins\n(comma separated and up to 20 coins)");
        Label errorLabel = new Label();
        TextField inputTF = new TextField();
        inputTF.setPromptText("Eg. 4,15,7,3,8,9");

        Button nextBtn = new Button("NEXT");

        VBox vBox = new VBox(40, promptLabel, inputTF, errorLabel);
        vBox.setAlignment(Pos.CENTER);

        setCenter(new Group(vBox));
        setBottom(nextBtn);

        BorderPane.setAlignment(nextBtn, Pos.CENTER);

        BorderPane.setMargin(nextBtn, new Insets(0, 0, 50, 0));
    }

}
