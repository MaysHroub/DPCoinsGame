package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

// Pane for the manual input option.
public class UserInputPane extends BorderPane {

    private Label alertLabel;

    public UserInputPane() {
        init();
    }

    private void init() {
        Label promptLabel = new Label("Enter a group of even number of coins\n" +
                "(comma separated and up to 20 coins.\n" +
                "Coin values are within 1 and 1000)");
        alertLabel = new Label();
        alertLabel.setId("alert");
        TextField inputTF = new TextField();
        inputTF.setPromptText("Eg. 4,15,7,3,8,9");

        Button nextBtn = new Button("NEXT"),
                backBtn = new Button("BACK");

        nextBtn.setOnAction(e -> {
            int[] coinValues = convertToCoins(inputTF.getText());
            if (coinValues != null) {
                NavigationManager navigationManager = NavigationManager.getInstance();
                navigationManager.getGameState().setCoinValues(coinValues);
                PaneId paneId = navigationManager.getGameState().isLaunchDPGame() ? PaneId.DP_GAME : PaneId.PLAYERS_GAME;
                navigationManager.navigateTo(paneId);
            }
        });
        backBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.INPUT_OPTIONS));
        backBtn.setId("back-button");

        VBox vBox = new VBox(40, promptLabel, inputTF, nextBtn, alertLabel);
        vBox.setAlignment(Pos.CENTER);

        setCenter(new Group(vBox));
        setTop(backBtn);

        BorderPane.setAlignment(nextBtn, Pos.CENTER);
        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        setPadding(new Insets(20));

        Animation.installFadeTransition(inputTF, 1);
        Animation.installFadeTransition(nextBtn, 1);
        Animation.installTranslateYTransition(nextBtn, .8, nextBtn.getTranslateY()+50, nextBtn.getTranslateY());
    }

    // Converts input to array of coin values.
    private int[] convertToCoins(String input) {
        if (input == null) return null;
        String[] tokens = input.split(",");
        if (tokens.length == 0 || tokens.length % 2 != 0) {
            alertLabel.setText("Please enter even number of coins and comma separated :)");
            return null;
        }
        if (tokens.length > 20) {
            alertLabel.setText("Please enter up to 20 coins");
            return null;
        }
        int[] coins = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            try {
                coins[i] = Integer.parseInt(tokens[i]);
                if (coins[i] <= 0 || coins[i] > 1000) {
                    alertLabel.setText("Coin value range must be between 1 and 1000.");
                    return null;
                }
            } catch (NumberFormatException e) {
                alertLabel.setText("Please enter only integers.");
                return null;
            }
        }
        alertLabel.setText("");
        return coins;
    }

}
