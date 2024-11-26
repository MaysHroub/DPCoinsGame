package com.msreem.dpcoinsgame.dp;

public class DPGameLogic {

    private int[] coins, playerOneCoins, playerTwoCoins;
    private int[][] dpTable;
    private char[][] directions;

    public DPGameLogic(int[] coins) {
        setCoins(coins);
    }


    public void calculateTable() {
        int n = coins.length;
        int[] cum = new int[n+1];
        dpTable = new int[n][n];

        for (int i = 0; i < n; i++)
            cum[i+1] = cum[i] + coins[i];

        for (int i = 0; i < n; i++)
            dpTable[i][i] = coins[i];

        for (int k = 0; k < n; k++)
            for (int i = 0, j = k+1; j < n; i++, j++) {
                int sum1 = coins[i] + cum[j+1]-cum[i+1] - dpTable[i+1][j],
                        sum2 = coins[j] + cum[j]-cum[i] - dpTable[i][j-1];
                dpTable[i][j] = Math.max(sum1, sum2);
                if (sum1 > sum2)
                    directions[i][j] = 'D';
                else if (sum1 < sum2)
                    directions[i][j] = 'L';
                else {
                    if (coins[i] > coins[j])
                        directions[i][j] = 'D';
                    else
                        directions[i][j] = 'L';
                }
            }
    }

    public void calculatePlayersCoins() {
        int n = coins.length;
        playerOneCoins = new int[n/2];
        playerTwoCoins = new int[n/2];
        int i1 = 0, i2 = 0;
        int l = 0, r = n-1;
        while (l <= r) {
            char dir = directions[l][r];
            if ((r-l) % 2 != 0) {
                playerOneCoins[i1] = dir == 'L' ? coins[r] : coins[l];
                i1++;
            } else {
                playerTwoCoins[i2] = dir == 'L' ? coins[r] : coins[l];
                i2++;
            }
            if (dir == 'L') r--;
            else l++;
        }
    }

    public int[] getCoins() {
        return coins;
    }

    public void setCoins(int[] coins) {
        this.coins = coins;
    }

    public int[][] getDpTable() {
        return dpTable;
    }

    public char[][] getDirections() {
        return directions;
    }

    public int[] getPlayerOneCoins() {
        return playerOneCoins;
    }

    public int[] getPlayerTwoCoins() {
        return playerTwoCoins;
    }
}
