package com.msreem.dpcoinsgame.panes;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TwoPlayerPane extends StackPane {

    public TwoPlayerPane() {
        init();
    }

    private void init() {
        Label playerOneName = new Label("MARIO"),
                playerTwoName = new Label("LUIGI"),
                playerTurn = new Label("Mario's Turn");
        ImageView marioImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\mario_fight.png"),
                luigiImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\luigi_fight.png");

        marioImg.setFitHeight(200);
        marioImg.setPreserveRatio(true);
        luigiImg.setFitHeight(200);
        luigiImg.setPreserveRatio(true);

        VBox playerOneVB = new VBox(20, playerOneName, marioImg),
                playerTwoVB = new VBox(20, playerTwoName, luigiImg);

        BorderPane upperLayout = new BorderPane();
        upperLayout.setCenter(playerTurn);
        upperLayout.setLeft(playerOneVB);
        upperLayout.setRight(playerTwoVB);
    }

}
