package com.msreem.dpcoinsgame.panes;

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

public class TwoRobotPane extends StackPane {

    public TwoRobotPane() {
        init();
    }


    private void init() {
        Label robotTurnL = new Label("Mario's Turn");
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
        upperLayout.setCenter(robotTurnL);
        upperLayout.setLeft(greyRobotVB);
        upperLayout.setRight(redRobotVB);

        BorderPane.setMargin(greyRobotVB, new Insets(0, 0, 0, 100));
        BorderPane.setMargin(redRobotVB, new Insets(0, 100, 0, 0));


        Coin[] coins = new Coin[20];
        for (int i = 0; i < 20; i++)
            coins[i] = new Coin(i+1);

        HBox coinsHB = new HBox(10);
        coinsHB.getChildren().addAll(Arrays.asList(coins));
        coinsHB.setAlignment(Pos.CENTER);


        TableView<Label> greyRobotCoinsTable = new TableView<>();
        greyRobotCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> greyRobotCol = new TableColumn<>("GREY ROBOT Coins");
        greyRobotCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        greyRobotCol.setPrefWidth(200);
        greyRobotCol.setSortable(false);
        greyRobotCoinsTable.getColumns().add(greyRobotCol);
        ObservableList<Label> greyRobotCoins = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++)
            greyRobotCoins.add(new Label("Coin " + i));
        greyRobotCoinsTable.setItems(greyRobotCoins);
        greyRobotCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableView<Label> redRobotCoinsTable = new TableView<>();
        redRobotCoinsTable.setPrefHeight(180);
        TableColumn<Label, String> redRobotCol = new TableColumn<>("RED ROBOT Coins");
        redRobotCol.setCellValueFactory(new PropertyValueFactory<>("text"));
        redRobotCol.setPrefWidth(200);
        redRobotCol.setSortable(false);
        redRobotCoinsTable.getColumns().add(redRobotCol);
        ObservableList<Label> redRobotCoins = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++)
            redRobotCoins.add(new Label("Coin " + i));
        redRobotCoinsTable.setItems(redRobotCoins);
        redRobotCoinsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Button resetBtn = new Button("RESET"),
                homeBtn = new Button(" HOME "),
                nextMoveBtn = new Button("NEXT MOVE"),
                dpTableBtn = new Button("SHOW DP TABLE");

        HBox lowerBtnsHB = new HBox(40, dpTableBtn, resetBtn, homeBtn);
        lowerBtnsHB.setAlignment(Pos.CENTER);
        VBox buttonsVB = new VBox(50, nextMoveBtn, lowerBtnsHB);
        buttonsVB.setAlignment(Pos.CENTER);

        BorderPane lowerLayout = new BorderPane();
        lowerLayout.setCenter(buttonsVB);
        lowerLayout.setLeft(greyRobotCoinsTable);
        lowerLayout.setRight(redRobotCoinsTable);

        BorderPane layout = new BorderPane();
        layout.setTop(upperLayout);
        layout.setCenter(coinsHB);
        layout.setBottom(lowerLayout);
        layout.setPadding(new Insets(30));

        BorderPane toast = createMessageToast("Based on random selection, the Grey Robot is selected to start first");
        BorderPane instructionToast = createMessageToast("Click on the 'NEXT MOVE' button to see the robots' next moves in the game.");

        getChildren().addAll(layout, instructionToast);

        Animation.installTranslateXTransition(greyRobotVB, 1, greyRobotVB.getTranslateX()-200, greyRobotVB.getTranslateX());
        Animation.installTranslateXTransition(redRobotVB, 1, redRobotVB.getTranslateX()+200, redRobotVB.getTranslateX());
    }

    private BorderPane createMessageToast(String message) {
        BorderPane messageBP = new BorderPane();
        messageBP.setStyle("-fx-background-color: rgba(80, 80, 80, 0.94); -fx-background-radius: 10;");

        Label messageL = new Label(message);
        messageL.setWrapText(true);
        messageL.setAlignment(Pos.CENTER);
        messageL.setStyle("-fx-font-size: 16;");

        Button closeBtn = new Button("CLOSE");
        closeBtn.setStyle("-fx-background-color: transparent;");

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
