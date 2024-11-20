package com.msreem.dpcoinsgame.navigation;

import com.msreem.dpcoinsgame.panetag.PaneTag;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

public class NavigationManager {

    private static NavigationManager instance;
    private Scene scene;

    private NavigationManager() {}

    public static NavigationManager getInstance() {
        if (instance == null)
            instance = new NavigationManager();
        return instance;
    }

    public void init(Scene scene) {
        this.scene = scene;
    }

    public void navigateTo(PaneTag tag) {
        switch (tag) {

        }
    }

}
