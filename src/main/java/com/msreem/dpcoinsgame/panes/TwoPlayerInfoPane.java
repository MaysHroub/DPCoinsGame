package com.msreem.dpcoinsgame.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TwoPlayerInfoPane extends BorderPane {

    public TwoPlayerInfoPane() {
        init();
    }

    private void init() {
        Label promptName1Label = new Label("Enter Player 1 Name"),
                promptName2Label = new Label("Enter Player 2 Name"),
                alertLabel = new Label();
        ImageView marioIV = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\mario.png"),
                luigiIV = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\luigi.png");
        TextField nameOneTF = new TextField(),
                    nameTwoTF = new TextField();
        Button nextBtn = new Button("NEXT");

        marioIV.setPreserveRatio(true);
        marioIV.setFitHeight(250);
        luigiIV.setPreserveRatio(true);
        luigiIV.setFitHeight(250);

        VBox playerOneVB = new VBox(20, marioIV, promptName1Label, nameOneTF);
        VBox playerTwoVB = new VBox(20, luigiIV, promptName2Label, nameTwoTF);

        playerOneVB.setAlignment(Pos.CENTER);
        playerTwoVB.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(100, playerOneVB, playerTwoVB);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, alertLabel, nextBtn);
        vBox.setAlignment(Pos.CENTER);

        setCenter(hBox);
        setBottom(vBox);

        setPadding(new Insets(20, 20, 50, 20));
    }

}
