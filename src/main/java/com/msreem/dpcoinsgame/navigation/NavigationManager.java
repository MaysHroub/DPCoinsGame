package com.msreem.dpcoinsgame.navigation;

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

        }
    }

}
