package com.msreem.dpcoinsgame.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin extends StackPane {

    private int value;
    private int index;


    public Coin(int value, int index) {
        this.value = value;
        this.index = index;
        Circle circle = new Circle(22);
        circle.setStyle("-fx-fill: gold; -fx-stroke: orange; -fx-stroke-width: 3; -fx-text-alignment: center;");
        Label label = new Label(value + "");
        getChildren().addAll(circle, label);
        setId("coin");
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }
}
