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
        Label label = new Label(value + "");
        getChildren().addAll(label);
        setId("coin");
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }
}
