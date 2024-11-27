package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.dp.DPGameLogic;
import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import java.io.File;

public class DPTablePane extends BorderPane {

    public DPTablePane() {
        init();
    }

    private void init() {
        NavigationManager navigationManager = NavigationManager.getInstance();

        Button backBtn = new Button("BACK");
        backBtn.setId("back-button");
        backBtn.setOnAction(e -> navigationManager.navigateTo(PaneId.DP_GAME));

        TextArea dpTableTA = new TextArea();
        dpTableTA.setEditable(false);

        DPGameLogic dpGameLogic = new DPGameLogic(navigationManager.getGameState().getCoinValues());
        dpGameLogic.calculateTable();
        int[][] dp = dpGameLogic.getDpTable();
        char[][] dirs = dpGameLogic.getDirections();

        StringBuilder strbld = new StringBuilder();

        strbld.append("\t");
        for (int i = 0; i < dpGameLogic.getCoins().length; i++)
            strbld.append(String.format("%4d\t", dpGameLogic.getCoins()[i]));

        strbld.append("\n\n");

        for (int i = 0; i < dp.length; i++) {
            strbld.append(String.format("%4d\t", dpGameLogic.getCoins()[i]));
            for (int j = 0; j < dp[i].length; j++) {
                if (i == j)
                    strbld.append(String.format("%4d\t", dp[i][j]));
                else if (j > i)
                    strbld.append(String.format("%4d(%c)\t", dp[i][j], dirs[i][j]));
                else
                    strbld.append("\t");
            }
            strbld.append("\n\n");
        }
        dpTableTA.setText(strbld.toString());

        setTop(backBtn);
        setCenter(dpTableTA);

        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);
        BorderPane.setMargin(dpTableTA, new Insets(40, 0, 0, 0));

        setPadding(new Insets(40));
    }

}
