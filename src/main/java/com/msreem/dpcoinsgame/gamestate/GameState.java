package com.msreem.dpcoinsgame.gamestate;

// To preserve the values of players' names and coins across panes.
public class GameState {

    private String[] playerNames;
    private int[] coinValues;
    private boolean launchDPGame;

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

    public boolean isLaunchDPGame() {
        return launchDPGame;
    }

    public void setLaunchDPGame(boolean launchDPGame) {
        this.launchDPGame = launchDPGame;
    }
}
