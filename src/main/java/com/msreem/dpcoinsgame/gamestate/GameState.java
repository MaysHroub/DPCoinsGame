package com.msreem.dpcoinsgame.gamestate;

public class GameState {

    private String[] playerNames;
    private int[] coinValues;

    public String[] getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public int[] getCoinValues() {
        return coinValues;
    }

    public void setCoinValues(int[] coinValues) {
        this.coinValues = coinValues;
    }
}
