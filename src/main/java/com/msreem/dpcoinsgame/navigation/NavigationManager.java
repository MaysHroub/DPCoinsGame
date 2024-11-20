package com.msreem.dpcoinsgame.navigation;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class NavigationManager {

    private static NavigationManager instance;
    // TO-DO: implement your own arraylist class
    private ArrayList<Pane> panes;

    private NavigationManager() {}


    public static NavigationManager getInstance() {
        if (instance == null)
            instance = new NavigationManager();
        return instance;
    }

}
