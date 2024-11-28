package com.msreem.dpcoinsgame.animation;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    public static void installFadeTransition(Node node, double durationInSecs) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(durationInSecs), node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(false);
        fadeTransition.play();
    }

    public static void installTranslateYTransition(Node node, double durationInSecs, double fromY, double toY) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(durationInSecs), node);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(toY);
        translateTransition.play();
    }

    public static void installTranslateXTransition(Node node, double durationInSecs, double fromX, double toX) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(durationInSecs), node);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        translateTransition.play();
    }

}
