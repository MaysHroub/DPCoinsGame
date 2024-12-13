# Optimal Coin Game Strategy with Dynamic Programming 🪙
Or known as *"Optimal strategy for a game using dynamic programming".*

## Overview
DPCoinsGame is a dynamic programming-based game where players take turns choosing coins (first or last coins only) strategically to maximize their score. 
This game demonstrates concepts of dynamic programming, user interaction with a GUI built with JavaFX, and game logic implemented using Java.

I used Singleton design pattern in `NaviagationManager` class to navigate between game panes, and applied Single Responsibility Principle to ensure clean, modular, and maintainable code.

## Dynamic Programming Logic
**DP Relation** Used:

![dp relation](https://github.com/user-attachments/assets/be685956-1371-401f-9bf7-24599136ca87)
***i***  is a pointer to the start of the coins array and  ***j***  is a pointer to the end of the array.

**Initial value**:
- If we have one coin, we take it.
- If we have two coins, we take the maximum one.

## Features
- **Dynamic Programming**: Implements a dynamic programming solution for coin selection.
- **Real-Time Updates**: UI updates as players make their moves, displaying scores and coin selections.
- **Animation**: Animates UI components of the screen.
- **Different Input Options**: There are three options, manually, randomly generated, and reading from file.
- **Scoreboard**: Displays current scores for both players.
- **Game End**: Announces the winner once all coins are selected.
- There are two modes in the game: Two-Player mode and DP-Game mode.
  - **Two-Player mode**: A classic turn-based game where two players compete.
  - **DP-Game Mode**: Watch the game play out automatically based on dynamic programming logic. (It's a Computer vs. Computer game)

## Screenshots of main screens
### Start Screen
![Main Screen](https://github.com/user-attachments/assets/a2df3f80-ce3e-4356-b64d-e4b7b6e2b1e1)
### Game Rules
![Game Rules](https://github.com/user-attachments/assets/71d2baf5-e86c-4265-91ec-f9b3f1589de5)
### Data Input Options
![Data Input Options](https://github.com/user-attachments/assets/b3566913-9fdb-4edf-b886-c9376701c366)
### Manual Data Input
![Manual Data Input](https://github.com/user-attachments/assets/42202110-d0b6-4868-82de-312d3114f067)
### DP Game Screen
![DP Game Screen](https://github.com/user-attachments/assets/953229e5-d9f4-4c44-9e95-c8953409c22b)
### DP Table
![DP Table](https://github.com/user-attachments/assets/7b40ca54-cdae-41ae-b77e-8c5d79578b6d)

## Demo Video
[Click here to see the demo :3](https://drive.google.com/file/d/1nR922L5juMeq4ak5F0Rjc_7mFOFwYeeY/view?usp=sharing)

