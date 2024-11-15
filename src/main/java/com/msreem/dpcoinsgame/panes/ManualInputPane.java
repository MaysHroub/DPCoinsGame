package com.msreem.dpcoinsgame.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ManualInputPane extends BorderPane {

    private Label alertLabel;


    public ManualInputPane() {
        init();
    }

    private void init() {
        Label promptLabel = new Label("Enter a group of even number of coins\n(comma separated and up to 20 coins)");
        alertLabel = new Label();
        alertLabel.setId("alert");
        TextField inputTF = new TextField();
        inputTF.setPromptText("Eg. 4,15,7,3,8,9");

        Button nextBtn = new Button("NEXT");
        nextBtn.setOnAction(e -> validateInput(inputTF.getText()));

        VBox vBox = new VBox(40, promptLabel, inputTF, alertLabel);
        vBox.setAlignment(Pos.CENTER);

        setCenter(new Group(vBox));
        setBottom(nextBtn);

        BorderPane.setAlignment(nextBtn, Pos.CENTER);

        BorderPane.setMargin(nextBtn, new Insets(0, 0, 50, 0));
    }

    private int[] validateInput(String input) {
        if (input == null) return null;
        String[] tokens = input.split(",");
        if (tokens.length == 0 || tokens.length % 2 != 0) {
            alertLabel.setText("Please enter even number of coins and comma separated :)");
            return null;
        }
        int[] coins = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            try {
                coins[i] = Integer.parseInt(tokens[i]);
            } catch (NumberFormatException e) {
                alertLabel.setText("Please enter only integers :/");
                return null;
            }
        }
        alertLabel.setText("");
        return coins;
    }

}
