package com.msreem.dpcoinsgame.dp;

// Encapsulates the DP logic of the game
public class DPGameLogic {

    private int[] coins, playerOneCoinIndices, playerTwoCoinIndices;
    private int[][] dpTable;
    private char[][] directions;

    public DPGameLogic(int[] coins) {
        setCoins(coins);
    }

    // Build DP and directions table.
    public void buildTables() {
        int n = coins.length;
        int[] cum = new int[n+1];
        dpTable = new int[n][n];
        directions = new char[n][n];

        for (int i = 0; i < n; i++) {
            // calculating cumulative sum
            cum[i+1] = cum[i] + coins[i];

            // setting initial value
            dpTable[i][i] = coins[i];
            if (i > 0) {
                int lowerVal = dpTable[i][i], leftVal = dpTable[i-1][i-1];
                if (leftVal > lowerVal) {
                    dpTable[i-1][i] = leftVal;
                    directions[i-1][i] = 'D';
                } else {
                    dpTable[i-1][i] = lowerVal;
                    directions[i-1][i] = 'L';
                }
            }
        }

        for (int k = 2; k <= n-1; k++)
            for (int i = 0; i+k < n; i++) {
                int sum1 = coins[i] + cum[i+k+1]-cum[i+1] - dpTable[i+1][i+k],
                        sum2 = coins[i+k] + cum[i+k]-cum[i] - dpTable[i][i+k-1];
                dpTable[i][i+k] = Math.max(sum1, sum2);

                if (sum1 > sum2) // by picking first coin
                    directions[i][i+k] = 'D';  // go down

                else if (sum1 < sum2) // by picking last coin
                    directions[i][i+k] = 'L';  // go left

                else { // sum1 = sum2
                    if (coins[i] > coins[i+k])
                        directions[i][i+k] = 'D';
                    else
                        directions[i][i+k] = 'L';
                }
            }
    }

    // Identifies coins' indices selected by each player.
    public void calculatePlayersCoins() {
        int n = coins.length;
        playerOneCoinIndices = new int[n/2];
        playerTwoCoinIndices = new int[n/2];
        int i1 = 0, i2 = 0;
        int l = 0, r = n-1;
        while (l <= r) {
            char dir = directions[l][r];
            if ((r-l) % 2 != 0) { // playerOne's turn
                playerOneCoinIndices[i1] = dir == 'L' ? r : l;
                i1++;
            } else { // playerTwo's turn
                playerTwoCoinIndices[i2] = dir == 'L' ? r : l;
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
        return playerOneCoinIndices;
    }

    public int[] getPlayerTwoCoins() {
        return playerTwoCoinIndices;
    }
}
