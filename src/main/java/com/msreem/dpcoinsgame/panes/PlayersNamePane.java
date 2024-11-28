package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// Pane for displaying the coin game for the 'two-player' option.
public class PlayersNamePane extends BorderPane {

    public PlayersNamePane() {
        init();
    }

    private void init() {
        Label promptName1Label = new Label("Enter Player 1 Name"),
                promptName2Label = new Label("Enter Player 2 Name"),
                alertLabel = new Label();

        alertLabel.setId("alert");

        ImageView marioIV = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\mario.png"),
                luigiIV = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\luigi.png");

        TextField nameOneTF = new TextField(),
                    nameTwoTF = new TextField();

        Button nextBtn = new Button("NEXT"),
                backBtn = new Button("BACK");

        nextBtn.setOnAction(e -> {
            if (isInputValid(nameOneTF, alertLabel) && isInputValid(nameTwoTF, alertLabel)) {
                if (nameOneTF.getText().equalsIgnoreCase(nameTwoTF.getText())) {
                    alertLabel.setText("The two names must not be the same.");
                    return;
                }
                NavigationManager.getInstance().getGameState().setPlayerNames(new String[]{nameOneTF.getText(), nameTwoTF.getText()});
                NavigationManager.getInstance().navigateTo(PaneId.INPUT_OPTIONS);
            }
        });
        backBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.START));
        backBtn.setId("back-button");

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
        setTop(backBtn);

        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        setPadding(new Insets(20, 20, 50, 20));

        Animation.installTranslateYTransition(nextBtn, .8, nextBtn.getTranslateY()+100, nextBtn.getTranslateY());
        Animation.installTranslateXTransition(playerOneVB, .8, playerOneVB.getTranslateX()-70, playerOneVB.getTranslateX());
        Animation.installTranslateXTransition(playerTwoVB, .8, playerTwoVB.getTranslateX()+70, playerTwoVB.getTranslateX());
    }

    // Validates the input entered by the user in the text-fields.
    private boolean isInputValid(TextField inputTF, Label alertLabel) {
        if (inputTF.getText() == null || inputTF.getText().isEmpty()) {
            alertLabel.setText("Enter your names in the text field.");
            return false;
        } else if (inputTF.getText().length() > 20) {
            alertLabel.setText("The name must be 1-12 characters.");
            return false;
        }
        return true;
    }

}
