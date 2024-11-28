package com.msreem.dpcoinsgame.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// Represents a coin with a value, index, and a visual label for display.
public class Coin extends StackPane {

    private final int value;
    private final int index;

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
