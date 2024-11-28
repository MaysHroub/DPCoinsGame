package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.dp.DPGameLogic;
import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.animation.Animation;
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

// Pane for displaying the coin game with the DP approach, for 'pro vs. computer' option.
public class DPGamePane extends StackPane {

    private TableView<Label> robotCoinsTable, playerCoinsTable;
    private Label turnL, robotScoreL, playerScoreL;
    private ImageView robotWinImg, playerWinImg, drawImg;
    private Button nextMoveBtn;
    private DPGameLogic dpLogic;
    private Coin[] coins;
    private int i1, i2;
    private int turn;
    private int robotScore, playerScore;

    public DPGamePane() {
        init();
    }

    private void init() {
        DPTablePane tablePane = new DPTablePane();
        tablePane.setDisable(true);
        tablePane.setVisible(false);

        int[] coinValues = NavigationManager.getInstance().getGameState().getCoinValues();
        dpLogic = new DPGameLogic(coinValues);
        dpLogic.buildTables();
        dpLogic.calculatePlayersCoins();

        int n = coinValues.length;

        turnL = new Label("BMO's Turn");
        robotScoreL = new Label("SCORE: " + robotScore);
        playerScoreL = new Label("SCORE: " + playerScore);
        robotScoreL.setStyle("-fx-font-size: 18;");
        playerScoreL.setStyle("-fx-font-size: 18;");

        ImageView bmoImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\bmo.png"),
                nerdPlayerImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\nerd-player-fight.png");
        robotWinImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\bmo-win.jpg");
        playerWinImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\nerd-win.jpg");
        drawImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\bmo-player-draw.jpg");

        robotWinImg.setFitHeight(190);
        robotWinImg.setPreserveRatio(true);
        playerWinImg.setFitHeight(190);
        playerWinImg.setPreserveRatio(true);
        drawImg.setFitHeight(190);
        drawImg.setPreserveRatio(true);
        bmoImg.setFitHeight(170);
        bmoImg.setPreserveRatio(true);
        nerdPlayerImg.setFitHeight(170);
        nerdPlayerImg.setPreserveRatio(true);


        VBox robotVB = new VBox(20, new Label("BMO"), bmoImg),
                playerVB = new VBox(20, new Label("MAGNUS"), nerdPlayerImg);
        robotVB.setAlignment(Pos.CENTER);
        playerVB.setAlignment(Pos.CENTER);

        BorderPane upperLayout = new BorderPane();
        upperLayout.setCenter(turnL);
        upperLayout.setLeft(robotVB);
        upperLayout.setRight(playerVB);

        BorderPane.setMargin(robotVB, new Insets(0, 0, 0, 100));
        BorderPane.setMargin(playerVB, new Insets(0, 100, 0, 0));


        coins = new Coin[n];
        for (int i = 0; i < n; i++)
            coins[i] = new Coin(coinValues[i], i);

        HBox coinsHB = new HBox(10);
        coinsHB.getChildren().addAll(Arrays.asList(coins));
        coinsHB.setAlignment(Pos.CENTER);
        coinsHB.setStyle("-fx-border-color: white;");


        robotCoinsTable = new TableView<>();
        robotCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> robotCol = new TableColumn<>("BMO's Coins");
        robotCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        robotCol.setPrefWidth(200);
        robotCol.setSortable(false);
        robotCoinsTable.getColumns().add(robotCol);
        robotCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        robotCoinsTable.getStylesheets().add(
                "data:text/css," +
                ".table-view .column-header {" +
                "  -fx-background-color: #03A398;" +
                "  -fx-text-fill: white;" +
                "}"
        );

        playerCoinsTable = new TableView<>();
        playerCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> playerCol = new TableColumn<>("MAGNUS's Coins");
        playerCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        playerCol.setPrefWidth(200);
        playerCol.setSortable(false);
        playerCoinsTable.getColumns().add(playerCol);
        playerCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playerCoinsTable.getStylesheets().add(
                "data:text/css," +
                        ".table-view .column-header {" +
                        "  -fx-background-color: brown;" +
                        "  -fx-text-fill: white;" +
                        "}"
        );


        Button resetBtn = new Button("RESET"),
                homeBtn = new Button(" HOME "),
                dpTableBtn = new Button("SHOW DP TABLE");
        nextMoveBtn = new Button("NEXT MOVE");

        homeBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.START));
        resetBtn.setOnAction(e -> resetGame());
        dpTableBtn.setOnAction(e -> {
            tablePane.setDisable(false);
            tablePane.setVisible(true);
        });
        nextMoveBtn.setOnAction(e -> playNextMove());

        HBox lowerBtnsHB = new HBox(40, resetBtn, homeBtn);
        lowerBtnsHB.setAlignment(Pos.CENTER);
        VBox buttonsVB = new VBox(30, nextMoveBtn, dpTableBtn, lowerBtnsHB);
        buttonsVB.setAlignment(Pos.CENTER);

        VBox robotCoinsVB = new VBox(20, robotScoreL, robotCoinsTable);
        robotCoinsVB.setAlignment(Pos.CENTER);

        VBox playerCoinsVB = new VBox(20, playerScoreL, playerCoinsTable);
        playerCoinsVB.setAlignment(Pos.CENTER);

        BorderPane lowerLayout = new BorderPane();
        lowerLayout.setCenter(buttonsVB);
        lowerLayout.setLeft(robotCoinsVB);
        lowerLayout.setRight(playerCoinsVB);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(coinsHB);
        layout.setBottom(lowerLayout);
        layout.setPadding(new Insets(30));

        BorderPane.setMargin(coinsHB, new Insets(20, 0, 20, 0));

        BorderPane instructionToast = createMessageToast("Click on the 'NEXT MOVE' button to see the next moves in the game.", null);

        getChildren().addAll(layout, instructionToast, tablePane);

        Animation.installFadeTransition(robotVB, 1);
        Animation.installFadeTransition(playerVB, 1);
        Animation.installTranslateXTransition(robotVB, 1, robotVB.getTranslateX()-100, robotVB.getTranslateX());
        Animation.installTranslateXTransition(playerVB, 1, playerVB.getTranslateX()+100, playerVB.getTranslateX());
    }

    // Resets the current match.
    private void resetGame() {
        turnL.setText(((turn == 0) ? "BMO" : "MAGNUS") + "'s Turn");

        nextMoveBtn.setDisable(false);

        robotCoinsTable.getItems().clear();
        robotCoinsTable.refresh();
        playerCoinsTable.getItems().clear();
        playerCoinsTable.refresh();

        i1 = 0; i2 = 0;

        for (Coin coin : coins) {
            coin.setStyle("-fx-background-color: gold; -fx-border-color: orange;");
            coin.setDisable(false);
        }

        robotScore = 0;
        robotScoreL.setText("SCORE: 0");
        playerScore = 0;
        playerScoreL.setText("SCORE: 0");
    }

    // Updates scores and coins according to the player's move in either turns.
    private void playNextMove() {
        int coinIdx = turn == 0 ? dpLogic.getPlayerOneCoins()[i1++] : dpLogic.getPlayerTwoCoins()[i2++];
        int coinVal = dpLogic.getCoins()[coinIdx];
        Label coinL = new Label("Coin " + coinVal);
        coins[coinIdx].setDisable(true);

        if (turn == 0) {
            coins[coinIdx].setStyle("-fx-background-color: #03A398; -fx-border-color: #03A398;");
            robotCoinsTable.getItems().add(coinL);
            robotScore += coinVal;
            robotScoreL.setText("SCORE: " + robotScore);
            turn = 1;
            turnL.setText("MAGNUS's turn");
        }
        else {
            coins[coinIdx].setStyle("-fx-background-color: #f5b964;");
            playerCoinsTable.getItems().add(coinL);
            playerScore += coinVal;
            playerScoreL.setText("SCORE: " + playerScore);
            turn = 0;
            turnL.setText("BMO's turn");
        }

        int n = dpLogic.getCoins().length;
        if (i1 == n/2 && i2 == n/2) {
            announceWinner();
            nextMoveBtn.setDisable(true);
        }
    }

    // Displays a toast to show the winner.
    private void announceWinner() {
        String message = "DRAW Between BMO and MAGNUS!";
        ImageView img = drawImg;
        if (robotScore > playerScore) {
            message = "BMO has WON!";
            img = robotWinImg;
        }
        else if (robotScore < playerScore) {
            message = "MAGNUS has WON!";
            img = playerWinImg;
        }

        BorderPane toast = createMessageToast(message, img);
        getChildren().add(toast);
    }

    // Returns a pane containing given message and image, representing a toast message.
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

        Animation.installFadeTransition(messageBP, .7);

        return messageBP;
    }

}
