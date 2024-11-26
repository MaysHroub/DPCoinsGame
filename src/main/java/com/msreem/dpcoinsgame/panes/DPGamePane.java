package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.dp.DPGameLogic;
import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.util.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class DPGamePane extends StackPane {

    private TableView<String> playerOneCoinsTable, playerTwoCoinsTable;
    private Label playerTurnL, playerOneScoreL, playerTwoScoreL;
    private DPGameLogic dpLogic;
    private Coin[] coins;
    private int l, r;
    private int i1, i2;
    private int turn;
    private int playerOneScore, playerTwoScore;


    public DPGamePane() {
        init();
    }

    private void init() {
        int[] coinValues = NavigationManager.getInstance().getGameState().getCoinValues();
        dpLogic = new DPGameLogic(coinValues);
        dpLogic.calculateTable();
        dpLogic.calculatePlayersCoins();

        int n = coinValues.length;
        l = 0; r = n-1;
        i1 = n/2 - 1; i2 = n/2 - 1;

        playerTurnL = new Label("Mario's Turn");
        playerOneScoreL = new Label("SCORE: " + playerOneScore);
        playerTwoScoreL = new Label("SCORE: " + playerTwoScore);
        playerOneScoreL.setStyle("-fx-font-size: 18;");
        playerTwoScoreL.setStyle("-fx-font-size: 18;");

        ImageView greyRobotImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\grey-robot-fight.png"),
                redRobotImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\red-robot-fight.png");

        greyRobotImg.setFitHeight(180);
        greyRobotImg.setPreserveRatio(true);
        redRobotImg.setFitHeight(180);
        redRobotImg.setPreserveRatio(true);


        VBox greyRobotVB = new VBox(20, new Label("ROBOT ALPHA"), greyRobotImg),
                redRobotVB = new VBox(20, new Label("ROBOT OMEGA"), redRobotImg);
        greyRobotVB.setAlignment(Pos.CENTER);
        redRobotVB.setAlignment(Pos.CENTER);

        BorderPane upperLayout = new BorderPane();
        upperLayout.setCenter(playerTurnL);
        upperLayout.setLeft(greyRobotVB);
        upperLayout.setRight(redRobotVB);

        BorderPane.setMargin(greyRobotVB, new Insets(0, 0, 0, 100));
        BorderPane.setMargin(redRobotVB, new Insets(0, 100, 0, 0));


        coins = new Coin[n];
        for (int i = 0; i < n; i++)
            coins[i] = new Coin(coinValues[i], i);

        HBox coinsHB = new HBox(10);
        coinsHB.getChildren().addAll(Arrays.asList(coins));
        coinsHB.setAlignment(Pos.CENTER);
        coinsHB.setStyle("-fx-border-color: white;");


        playerOneCoinsTable = new TableView<>();
        playerOneCoinsTable.setPrefHeight(180);
        TableColumn<String, String> greyRobotCol = new TableColumn<>("GREY ROBOT Coins");
        greyRobotCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        greyRobotCol.setPrefWidth(200);
        greyRobotCol.setSortable(false);
        playerOneCoinsTable.getColumns().add(greyRobotCol);
        playerOneCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        playerTwoCoinsTable = new TableView<>();
        playerTwoCoinsTable.setPrefHeight(180);
        TableColumn<String, String> redRobotCol = new TableColumn<>("RED ROBOT Coins");
        redRobotCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        redRobotCol.setPrefWidth(200);
        redRobotCol.setSortable(false);
        playerTwoCoinsTable.getColumns().add(redRobotCol);
        playerTwoCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Button resetBtn = new Button("RESET"),
                homeBtn = new Button(" HOME "),
                nextMoveBtn = new Button("NEXT MOVE"),
                dpTableBtn = new Button("SHOW DP TABLE");

        homeBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.START));
        nextMoveBtn.setOnAction(e -> playNextMove());

        HBox lowerBtnsHB = new HBox(40, resetBtn, homeBtn);
        lowerBtnsHB.setAlignment(Pos.CENTER);
        VBox buttonsVB = new VBox(30, nextMoveBtn, dpTableBtn, lowerBtnsHB);
        buttonsVB.setAlignment(Pos.CENTER);

        VBox playerOneCoinsVB = new VBox(20, playerOneScoreL, playerOneCoinsTable);
        playerOneCoinsVB.setAlignment(Pos.CENTER);

        VBox playerTwoCoinsVB = new VBox(20, playerTwoScoreL, playerTwoCoinsTable);
        playerTwoCoinsVB.setAlignment(Pos.CENTER);

        BorderPane lowerLayout = new BorderPane();
        lowerLayout.setCenter(buttonsVB);
        lowerLayout.setLeft(playerOneCoinsVB);
        lowerLayout.setRight(playerTwoCoinsVB);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(coinsHB);
        layout.setBottom(lowerLayout);
        layout.setPadding(new Insets(30));

        //BorderPane toast = createMessageToast("Grey Robot is selected to start first", null);
        BorderPane instructionToast = createMessageToast("Click on the 'NEXT MOVE' button to see the robots' next moves in the game.", null);

        getChildren().addAll(layout, instructionToast);

        Animation.installTranslateXTransition(greyRobotVB, 1, greyRobotVB.getTranslateX()-200, greyRobotVB.getTranslateX());
        Animation.installTranslateXTransition(redRobotVB, 1, redRobotVB.getTranslateX()+200, redRobotVB.getTranslateX());
    }

    private void playNextMove() {
        int coinIdx = dpLogic.getPlayerOneCoins()[turn == 0 ? i1 : i2];
        if (coinIdx == l) l++;
        else r--;
        String coin = "Coin " + coins[coinIdx].getValue();

        if (turn == 0) {
            // colour the coin to be the same as the robot colour and disable it maybe...
            playerOneCoinsTable.getItems().add(coin);
            playerOneScore += coins[coinIdx].getValue();
            playerOneScoreL.setText("SCORE: " + playerOneScore);
        }
        else {
            // colour the coin to be the same as the player colour and disable it maybe...
            playerTwoCoinsTable.getItems().add(coin);
            playerTwoScore += coins[coinIdx].getValue();
            playerTwoScoreL.setText("SCORE: " + playerOneScore);
        }

        turn ^= turn;

        if (l > r)
            announceWinner();
    }

    private void announceWinner() {
        String message = "DRAW Between Robot and Player!";
//        ImageView img = drawImg;
        if (playerOneScore > playerTwoScore) {
            message = "Robot has WON!";
//            img = marioWinImg;
        }
        else if (playerOneScore < playerTwoScore) {
            message = "Player has WON!";
//            img = luigiWinImg;
        }

        BorderPane toast = createMessageToast(message, null);
        getChildren().add(toast);
    }

    private BorderPane createMessageToast(String message, ImageView img) {
        BorderPane messageBP = new BorderPane();
        messageBP.setStyle("-fx-background-color: rgba(80, 80, 80, 0.95); -fx-background-radius: 10;");

        Label messageL = new Label(message);
        messageL.setWrapText(true);
        messageL.setAlignment(Pos.CENTER);
        messageL.setStyle("-fx-font-size: 16;");

        Button closeBtn = new Button("CLOSE");
        closeBtn.setId("back-button");
        closeBtn.setOnAction(e -> {
            messageBP.setDisable(true);
            messageBP.setVisible(false);
        });

        if (img == null)
            messageBP.setCenter(messageL);
        else {
            messageBP.setCenter(img);
            messageBP.setBottom(messageL);
        }
        messageBP.setTop(closeBtn);
        messageBP.setPadding(new Insets(10));
        messageBP.maxWidthProperty().bind(widthProperty().divide(2));
        messageBP.maxHeightProperty().bind(heightProperty().divide(2));
        BorderPane.setAlignment(closeBtn, Pos.TOP_RIGHT);
        BorderPane.setAlignment(messageL, Pos.CENTER);

        Animation.installFadeTransition(messageBP, 1.5);

        return messageBP;
    }

}
