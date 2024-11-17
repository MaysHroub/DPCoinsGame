package com.msreem.dpcoinsgame.panes;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin extends StackPane {

    public Coin(int value) {
        Circle circle = new Circle(23);
        circle.setStyle("-fx-fill: gold; -fx-stroke: orange; -fx-stroke-width: 3;");
        Label label = new Label(value + "");
        label.setStyle("-fx-font-size: 14;");
        getChildren().addAll(circle, label);
    }

}
