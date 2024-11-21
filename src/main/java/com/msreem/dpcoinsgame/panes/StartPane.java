package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.util.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class StartPane extends BorderPane {

    private Button rulesBtn, twoPlayerBtn, dpGameBtn;

    public StartPane() {
        init();
    }

    private void init() {
        rulesBtn = new Button("RULES");
        twoPlayerBtn = new Button("TWO PLAYERS");
        dpGameBtn = new Button("CPU BATTLE");

        ImageView twoPlayersImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\two-players.png");
        twoPlayersImg.setFitHeight(200);
        twoPlayersImg.setPreserveRatio(true);
        VBox twoPlayersVB = new VBox(10, twoPlayersImg, twoPlayerBtn);
        twoPlayersVB.setAlignment(Pos.CENTER);

        ImageView twoRobotsImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\two-robots.png");
        twoRobotsImg.setFitHeight(200);
        twoRobotsImg.setPreserveRatio(true);
        VBox twoRobotsVB = new VBox(10, twoRobotsImg, dpGameBtn);
        twoRobotsVB.setAlignment(Pos.CENTER);

        HBox buttonsHB = new HBox(200, twoPlayersVB, twoRobotsVB);
        buttonsHB.setAlignment(Pos.CENTER);

        Label head1 = new Label("FIRST OR LAST");
        head1.setId("head1");
        Label head2 = new Label("COIN!");
        head2.setId("head2");

        Glow glow = new Glow();
        glow.setLevel(8);
        head1.setEffect(glow);
        head2.setEffect(glow);

        VBox titleVB = new VBox(head1, head2);
        titleVB.setAlignment(Pos.CENTER);

        this.setTop(titleVB);
        this.setCenter(buttonsHB);
        this.setBottom(rulesBtn);

        BorderPane.setAlignment(rulesBtn, Pos.TOP_CENTER);

        BorderPane.setMargin(titleVB, new Insets(60, 0, 0, 0));
        BorderPane.setMargin(rulesBtn, new Insets(0, 0, 50, 0));

        Animation.installTranslateYTransition(titleVB, .8, titleVB.getTranslateY()-130, titleVB.getTranslateY());
        Animation.installTranslateXTransition(twoPlayersVB, 1, twoPlayersVB.getTranslateX()-70, twoPlayersVB.getTranslateX());
        Animation.installTranslateXTransition(twoRobotsVB, 1, twoRobotsVB.getTranslateX()+70, twoRobotsVB.getTranslateX());
        Animation.installFadeTransition(buttonsHB, 1);
        Animation.installTranslateYTransition(rulesBtn, 1.3, rulesBtn.getTranslateY()+50, rulesBtn.getTranslateY());
    }



}