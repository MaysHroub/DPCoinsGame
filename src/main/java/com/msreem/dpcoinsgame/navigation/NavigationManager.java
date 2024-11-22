package com.msreem.dpcoinsgame.navigation;

import com.msreem.dpcoinsgame.gamestate.GameState;
import com.msreem.dpcoinsgame.panes.*;
import com.msreem.dpcoinsgame.paneid.PaneId;
import javafx.scene.Scene;


public class NavigationManager {

    private static NavigationManager instance;
    private Scene scene;
    private GameState gameState;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null)
            throw new AssertionError("You must call init() first");
        return instance;
    }

    public static void init(Scene scene) {
        if (instance == null) {
            instance = new NavigationManager();
            instance.gameState = new GameState();
            instance.scene = scene;
        }
    }

    // TO-DO: use a hashtable for choosing the pane instead of creating it
    public void navigateTo(PaneId id) {
        switch (id) {
            case START -> scene.setRoot(new StartPane());
            case RULES -> scene.setRoot(new RulesPane());
            case INPUT_OPTIONS -> scene.setRoot(new InputOptionsPane());
            case USER_INPUT -> scene.setRoot(new UserInputPane());
            case RANDOM_INPUT -> scene.setRoot(new RandomInputPane());
            case PLAYERS_NAME_INPUT -> scene.setRoot(new PlayersNamePane());
            case PLAYERS_GAME -> scene.setRoot(new TwoPlayerPane());
            case DP_GAME -> scene.setRoot(new DPGamePane());
        }
    }

    public GameState getGameState() {
        return gameState;
    }
}
