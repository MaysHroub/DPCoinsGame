package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.util.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class RandomInputPane extends BorderPane {

    private Label alertLabel;


    public RandomInputPane() {
        init();
    }

    private void init() {
        Label promptLabel = new Label("Enter the number of coins\n(an EVEN number between 2 and 20)");
        alertLabel = new Label();
        alertLabel.setId("alert");
        alertLabel.setWrapText(true);
        alertLabel.prefWidthProperty().bind(promptLabel.widthProperty());
        alertLabel.setAlignment(Pos.CENTER);
        TextField inputTF = new TextField();
        inputTF.setPromptText("Eg. 4");

        Button nextBtn = new Button("NEXT");
        Button generateBtn = new Button("GENERATE RANDOM COINS");
        Button backBtn = new Button("BACK");

        nextBtn.setOnAction(e -> {
            int[] coinValues = generateCoins(inputTF.getText());
            if (coinValues != null) {
                NavigationManager.getInstance().getGameState().setCoinValues(coinValues);
                NavigationManager.getInstance().navigateTo(PaneId.PLAYERS_GAME);
                // NavigationManager.getInstance().navigateTo(PaneId.DP_GAME);
            }
        });
        generateBtn.setOnAction(e -> generateCoins(inputTF.getText()));
        backBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.INPUT_OPTIONS));
        backBtn.setId("back-button");

        VBox vBox = new VBox(40, promptLabel, inputTF, generateBtn, alertLabel);
        vBox.setAlignment(Pos.CENTER);

        setCenter(new Group(vBox));
        setBottom(nextBtn);
        setTop(backBtn);

        BorderPane.setAlignment(nextBtn, Pos.CENTER);
        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        setPadding(new Insets(20));

        Animation.installFadeTransition(inputTF, 1.2);
        Animation.installTranslateYTransition(generateBtn, .7, generateBtn.getTranslateY()+100, generateBtn.getTranslateY());
        Animation.installTranslateYTransition(nextBtn, .8, nextBtn.getTranslateY()+100, nextBtn.getTranslateY());
    }

    private int[] generateCoins(String numberOfCoins) {
        if (numberOfCoins == null || numberOfCoins.isEmpty()) {
            alertLabel.setText("Please enter a number");
            return null;
        }
        int n = 0;
        try {
            n = Integer.parseInt(numberOfCoins);
        } catch (NumberFormatException e) {
            alertLabel.setText("Please enter only numbers :/");
            return null;
        }
        if (n < 1 || n > 20 || n % 2 != 0) {
            alertLabel.setText("Please enter an even number between 2 and 20");
            return null;
        }
        int[] coins = new int[n];
        for (int i = 0; i < n; i++)
            coins[i] = 1 + (int) (Math.random() * 199);

        alertLabel.setText(Arrays.toString(coins));
        return coins;
    }

}
