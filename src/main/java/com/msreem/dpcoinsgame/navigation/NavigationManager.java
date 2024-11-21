package com.msreem.dpcoinsgame.navigation;

import com.msreem.dpcoinsgame.panes.*;
import com.msreem.dpcoinsgame.panetag.PaneTag;
import javafx.scene.Scene;


public class NavigationManager {

    private static NavigationManager instance;
    private Scene scene;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null)
            throw new AssertionError("You must call init() first");
        return instance;
    }

    public static void init(Scene scene) {
        if (instance == null) {
            instance = new NavigationManager();
            instance.scene = scene;
        }
    }

    public void navigateTo(PaneTag tag) {
        switch (tag) {
            case START -> scene.setRoot(new WelcomePane());
            case INPUT_OPTIONS -> scene.setRoot(new InputOptionsPane());
            case USER_INPUT -> scene.setRoot(new ManualInputPane());
            case RANDOM_INPUT -> scene.setRoot(new RandomInputPane());
            case PLAYERS_NAME_INPUT -> scene.setRoot(new TwoPlayerInfoPane());
            case PLAYERS_GAME -> scene.setRoot(new TwoPlayerPane());
            case DP_GAME -> scene.setRoot(new TwoRobotPane());
        }
    }

}
