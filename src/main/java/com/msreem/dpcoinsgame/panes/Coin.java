package com.msreem.dpcoinsgame.panes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin extends StackPane {

    public Coin(int value) {
        Circle circle = new Circle(10, Color.GOLD);
        Label label = new Label(value + "");
        getChildren().addAll(circle, label);
    }

}
