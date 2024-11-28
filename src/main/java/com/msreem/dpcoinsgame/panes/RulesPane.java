package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.animation.Animation;
import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

// Pane displaying the rules.
public class RulesPane extends VBox {

    public RulesPane() {
        init();
    }

    private void init() {
        Label titleL = new Label("Game Rules"),
                rulesL = new Label("This is a two-player strategy game involving an even number " +
                        "of coins arranged in a row.\nPlayers take turns alternately, and on each turn, " +
                        "a player must choose either the first or the last coin from the row to add " +
                        "to their total.\nThe goal is to maximize the total value of coins collected.\n" +
                        "The player who moves first must carefully strategize to ensure they can secure " +
                        "the maximum possible total, regardless of the opponent's decisions.\n" +
                        "For the DP approach, both players are assumed to use optimal strategies to maximize their gains.");

        titleL.setId("head1");

        rulesL.setWrapText(true);
        rulesL.setId("new-font-label");

        Button backBtn = new Button("BACK");
        backBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.START));

        getChildren().addAll(titleL, rulesL, backBtn);
        setSpacing(30);
        setPadding(new Insets(30, 160, 30, 160));
        setAlignment(Pos.CENTER);

        Animation.installFadeTransition(rulesL, 1);
        Animation.installFadeTransition(backBtn, .5);
        Animation.installTranslateYTransition(backBtn, .5, backBtn.getTranslateY()+50, backBtn.getTranslateY());
    }

}
