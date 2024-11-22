package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.util.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class TwoPlayerPane extends StackPane {

    private TableView<Label> playerOneCoinsTable, playerTwoCoinsTable;
    private Label playerTurnL, playerOneScoreL, playerTwoScoreL;
    private int playerTurn;
    private Coin[] coins;
    private int l, r;
    private int playerOneScore, playerTwoScore;


    public TwoPlayerPane() {
        init();
    }

    private void init() {
        NavigationManager navigationManager = NavigationManager.getInstance();
        String[] names = navigationManager.getGameState().getPlayerNames();

        Label playerOneNameL = new Label(names[0]),
                playerTwoNameL = new Label(names[1]);

        playerTurn = (int) (Math.random() * 2);

        playerTurnL = new Label(names[playerTurn] + "'s Turn");

        playerOneScoreL = new Label("SCORE: " + playerOneScore);
        playerTwoScoreL = new Label("SCORE: " + playerTwoScore);

        ImageView marioImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\mario_fight.png"),
                luigiImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\luigi_fight.png");

        marioImg.setFitHeight(170);
        marioImg.setPreserveRatio(true);
        luigiImg.setFitHeight(170);
        luigiImg.setPreserveRatio(true);


        VBox playerOneVB = new VBox(10, playerOneNameL, marioImg),
                playerTwoVB = new VBox(10, playerTwoNameL, luigiImg);
        playerOneVB.setAlignment(Pos.CENTER);
        playerTwoVB.setAlignment(Pos.CENTER);

        BorderPane upperLayout = new BorderPane();
        upperLayout.setCenter(playerTurnL);
        upperLayout.setLeft(playerOneVB);
        upperLayout.setRight(playerTwoVB);

        BorderPane.setMargin(playerOneVB, new Insets(0, 0, 0, 100));
        BorderPane.setMargin(playerTwoVB, new Insets(0, 100, 0, 0));

        int[] coinValues = navigationManager.getGameState().getCoinValues();
        coins = new Coin[coinValues.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = new Coin(coinValues[i], i);
            coins[i].setOnMouseClicked(e -> updateScore( ((Coin)e.getSource()).getIndex() ));
            if (i != 0 && i != coins.length-1) coins[i].setDisable(true);
        }
        l = 0; r = coins.length-1;

        HBox coinsHB = new HBox(10);
        coinsHB.getChildren().addAll(Arrays.asList(coins));
        coinsHB.setAlignment(Pos.CENTER);
        coinsHB.setStyle("-fx-border-color: white;");


        playerOneCoinsTable = new TableView<>();
        playerOneCoinsTable.setPrefHeight(180);
        playerOneCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Label, String> playerOneCol = new TableColumn<>(names[0] + " Coins");
        playerOneCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        playerOneCol.setPrefWidth(200);
        playerOneCol.setSortable(false);
        playerOneCoinsTable.getColumns().add(playerOneCol);


        playerTwoCoinsTable = new TableView<>();
        playerTwoCoinsTable.setPrefHeight(180);
        playerTwoCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Label, String> playerTwoCol = new TableColumn<>(names[1] + " Coins");
        playerTwoCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        playerTwoCol.setPrefWidth(200);
        playerTwoCol.setSortable(false);
        playerTwoCoinsTable.getColumns().add(playerTwoCol);

        VBox playerOneCoinsVB = new VBox(20, playerOneScoreL, playerOneCoinsTable);
        playerOneCoinsVB.setAlignment(Pos.CENTER);

        VBox playerTwoCoinsVB = new VBox(20, playerTwoScoreL, playerTwoCoinsTable);
        playerTwoCoinsVB.setAlignment(Pos.CENTER);


        Button resetBtn = new Button("RESET"),
                homeBtn = new Button("HOME");

        homeBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.START));

        VBox buttonsVB = new VBox(30, resetBtn, homeBtn);
        buttonsVB.setAlignment(Pos.CENTER);

        BorderPane lowerLayout = new BorderPane();
        lowerLayout.setCenter(buttonsVB);
        lowerLayout.setLeft(playerOneCoinsVB);
        lowerLayout.setRight(playerTwoCoinsVB);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(coinsHB);
        layout.setBottom(lowerLayout);
        layout.setPadding(new Insets(30));

        BorderPane.setMargin(coinsHB, new Insets(20, 0, 20, 0));

        BorderPane toast = createMessageToast("Based on random selection, Player " + names[playerTurn] + " was selected to start first");

        getChildren().addAll(layout, toast);

        Animation.installTranslateXTransition(playerOneVB, 1, playerOneVB.getTranslateX()-200, playerOneVB.getTranslateX());
        Animation.installTranslateXTransition(playerTwoVB, 1, playerTwoVB.getTranslateX()+200, playerTwoVB.getTranslateX());
    }

    private void updateScore(int indexOfClickedCoin) {
        // remove the coin, enable the adjacent one, update score/table of current player and change turns
        coins[indexOfClickedCoin].setDisable(true);
        coins[indexOfClickedCoin].setVisible(false);
        if (indexOfClickedCoin == l && l < coins.length-1)
            coins[++l].setDisable(false);
        else if (indexOfClickedCoin == r && r > 0)
            coins[--r].setDisable(false);

        Label coinL = new Label("Coin " + coins[indexOfClickedCoin].getValue());
        if (playerTurn == 0) {
            playerOneCoinsTable.getItems().add(coinL);
            playerOneScore += coins[indexOfClickedCoin].getValue();
            playerOneScoreL.setText("SCORE: " + playerOneScore);
        }
        else {
            playerTwoCoinsTable.getItems().add(coinL);
            playerTwoScore += coins[indexOfClickedCoin].getValue();
            playerTwoScoreL.setText("SCORE: " + playerTwoScore);
        }

        if (l > r) {
            announceWinner();
            return;
        }

        playerTurn ^= 1;
        playerTurnL.setText(NavigationManager.getInstance().getGameState().getPlayerNames()[playerTurn] + "'s Turn");
    }

    private void announceWinner() {
        String message = "";
        if (playerOneScore == playerTwoScore)
            message = "DRAW Between The Two Players!";
        else if (playerOneScore > playerTwoScore)
            message = "Player " + NavigationManager.getInstance().getGameState().getPlayerNames()[0] + " has WON!";
        else
            message = "Player " + NavigationManager.getInstance().getGameState().getPlayerNames()[1] + " has WON!";

        BorderPane toast = createMessageToast(message);
        getChildren().add(toast);
    }

    private BorderPane createMessageToast(String message) {
        BorderPane messageBP = new BorderPane();
        messageBP.setStyle("-fx-background-color: rgba(80, 80, 80, 0.95); -fx-background-radius: 10;");

        Label messageL = new Label(message);
        messageL.setWrapText(true);
        messageL.setAlignment(Pos.CENTER);
        messageL.setStyle("-fx-font-size: 16;");

        Button closeBtn = new Button("CLOSE");
        // closeBtn.setStyle("-fx-background-color: transparent;");
        closeBtn.setId("back-button");
        closeBtn.setOnAction(e -> {
            messageBP.setDisable(true);
            messageBP.setVisible(false);
        });

        messageBP.setCenter(messageL);
        messageBP.setTop(closeBtn);
        messageBP.setPadding(new Insets(10));
        messageBP.maxWidthProperty().bind(widthProperty().divide(3));
        messageBP.maxHeightProperty().bind(heightProperty().divide(3));
        BorderPane.setAlignment(closeBtn, Pos.TOP_RIGHT);
        BorderPane.setAlignment(messageL, Pos.CENTER);

        Animation.installFadeTransition(messageBP, 1.5);

        return messageBP;
    }

}
