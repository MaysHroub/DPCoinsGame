package com.msreem.dpcoinsgame.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

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
        playerOneVB.setAlignment(Pos.CENTER);
        playerTwoVB.setAlignment(Pos.CENTER);

        BorderPane upperLayout = new BorderPane();
        upperLayout.setCenter(playerTurn);
        upperLayout.setLeft(playerOneVB);
        upperLayout.setRight(playerTwoVB);

        BorderPane.setMargin(playerOneVB, new Insets(0, 0, 0, 100));
        BorderPane.setMargin(playerTwoVB, new Insets(0, 100, 0, 0));

        Coin[] coins = new Coin[20];
        for (int i = 0; i < 20; i++)
            coins[i] = new Coin(i+1);

        HBox coinsHB = new HBox(10);
        coinsHB.getChildren().addAll(Arrays.asList(coins));
        coinsHB.setAlignment(Pos.CENTER);

        TableView<Label> playerOneCoinsTable = new TableView<>();
        playerOneCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> playerOneCol = new TableColumn<>("Player 1 Coins");
        playerOneCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        playerOneCol.setPrefWidth(200);
        playerOneCol.setSortable(false);
        playerOneCoinsTable.getColumns().add(playerOneCol);
        ObservableList<Label> playerOneCoins = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++)
            playerOneCoins.add(new Label("Coin " + i));
        playerOneCoinsTable.setItems(playerOneCoins);
        playerOneCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableView<Label> playerTwoCoinsTable = new TableView<>();
        playerTwoCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> playerTwoCol = new TableColumn<>("Player 2 Coins");
        playerTwoCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        playerTwoCol.setPrefWidth(200);
        playerTwoCol.setSortable(false);
        playerTwoCoinsTable.getColumns().add(playerTwoCol);
        ObservableList<Label> playerTwoCoins = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++)
            playerTwoCoins.add(new Label("Coin " + i));
        playerTwoCoinsTable.setItems(playerTwoCoins);
        playerTwoCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Button resetBtn = new Button("RESET"),
                homeBtn = new Button("HOME");

        VBox buttonsVB = new VBox(30, resetBtn, homeBtn);
        buttonsVB.setAlignment(Pos.CENTER);

        BorderPane lowerLayout = new BorderPane();
        lowerLayout.setCenter(buttonsVB);
        lowerLayout.setLeft(playerOneCoinsTable);
        lowerLayout.setRight(playerTwoCoinsTable);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(coinsHB);
        layout.setBottom(lowerLayout);
        layout.setPadding(new Insets(30));

        getChildren().add(layout);
    }

}
