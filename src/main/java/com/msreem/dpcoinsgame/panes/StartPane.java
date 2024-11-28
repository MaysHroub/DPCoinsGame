package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

// Main pane, the first one showed to the user, displays the two game options: two-player and pro-vs-computer.
public class StartPane extends BorderPane {

    public StartPane() {
        init();
    }

    private void init() {
        Button rulesBtn = new Button("RULES");
        Button twoPlayerBtn = new Button("  TWO PLAYERS  ");
        Button dpGameBtn = new Button("PRO VS. COMPUTER");

        rulesBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.RULES));
        twoPlayerBtn.setOnAction(e -> {
            NavigationManager.getInstance().getGameState().setLaunchDPGame(false);
            NavigationManager.getInstance().navigateTo(PaneId.PLAYERS_NAME_INPUT);
        });
        dpGameBtn.setOnAction(e -> {
            NavigationManager.getInstance().getGameState().setLaunchDPGame(true);
            NavigationManager.getInstance().navigateTo(PaneId.INPUT_OPTIONS);
        });

        ImageView twoPlayersImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\two-players.png");
        twoPlayersImg.setFitHeight(200);
        twoPlayersImg.setPreserveRatio(true);
        VBox twoPlayersVB = new VBox(10, twoPlayersImg, twoPlayerBtn);
        twoPlayersVB.setAlignment(Pos.CENTER);

        ImageView playerVsRobotImg = new ImageView("C:\\Users\\ismae\\IdeaProjects\\DPCoinsGame\\src\\main\\resources\\images\\player-vs-comp.png");
        playerVsRobotImg.setFitHeight(200);
        playerVsRobotImg.setPreserveRatio(true);
        VBox playerVsRobotVB = new VBox(10, playerVsRobotImg, dpGameBtn);
        playerVsRobotVB.setAlignment(Pos.CENTER);

        HBox buttonsHB = new HBox(200, twoPlayersVB, playerVsRobotVB);
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
        Animation.installTranslateXTransition(twoPlayersVB, .8, twoPlayersVB.getTranslateX()-70, twoPlayersVB.getTranslateX());
        Animation.installTranslateXTransition(playerVsRobotVB, .8, playerVsRobotVB.getTranslateX()+70, playerVsRobotVB.getTranslateX());
        Animation.installFadeTransition(buttonsHB, 1);
        Animation.installTranslateYTransition(rulesBtn, .8, rulesBtn.getTranslateY()+50, rulesBtn.getTranslateY());
    }

}
