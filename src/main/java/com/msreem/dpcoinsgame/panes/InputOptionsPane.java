package com.msreem.dpcoinsgame.panes;

import com.msreem.dpcoinsgame.navigation.NavigationManager;
import com.msreem.dpcoinsgame.paneid.PaneId;
import com.msreem.dpcoinsgame.util.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOptionsPane extends BorderPane {

    public InputOptionsPane() {
        init();
    }

    private void init() {
        Label alertL = new Label(),
                label = new Label("SELECT YOUR INPUT METHOD"),
                infoL = new Label("""
                        Accepted file format is numberOfCoins (even), then coin values separated by comma.\
                        
                        Ex: 4,10,2,33,19\
                        
                        Note: if numberOfCoins doesn't equal the actual number of coin values, random values are placed.""");

        infoL.setId("new-font-label");
        infoL.setWrapText(true);
        alertL.setId("alert");
        alertL.setWrapText(true);

        Button loadFromFileBtn = new Button("Load data from file");
        Button enterManuallyBtn = new Button("Enter data manually");
        Button generateRandomBtn = new Button("Generate random data");
        Button backBtn = new Button("BACK");

        loadFromFileBtn.setOnAction(e -> {
            File inputFile = loadInputFile();
            if (inputFile == null) {
                alertL.setText("No file was selected.");
                return;
            }
            int[] coinValues = null;
            try {
                coinValues = parseCoinValuesFromFile(inputFile);
            } catch (FileNotFoundException ex) {
                alertL.setText("There was an error with this file. Please pick another one.");
            } catch (NumberFormatException ex) {
                alertL.setText("The selected file doesn't have the proper data type (only integers).");
            } catch (InputMismatchException ex) {
                alertL.setText("The selected file doesn't have the proper format.");
            }

            if (coinValues == null)
                alertL.setText("The selected file doesn't have the proper format.");
            else {
                NavigationManager navigationManager = NavigationManager.getInstance();
                navigationManager.getGameState().setCoinValues(coinValues);
                PaneId paneId = navigationManager.getGameState().isLaunchDPGame() ? PaneId.DP_GAME : PaneId.PLAYERS_GAME;
                navigationManager.navigateTo(paneId);
            }
        });
        enterManuallyBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.USER_INPUT));
        generateRandomBtn.setOnAction(e -> NavigationManager.getInstance().navigateTo(PaneId.RANDOM_INPUT));
        backBtn.setOnAction(e -> {
            NavigationManager navigationManager = NavigationManager.getInstance();
            PaneId paneId = navigationManager.getGameState().isLaunchDPGame() ? PaneId.START : PaneId.PLAYERS_NAME_INPUT;
            navigationManager.navigateTo(paneId);
        });

        backBtn.setId("back-button");

        VBox fileInputVB = new VBox(30, infoL, loadFromFileBtn, alertL);
        fileInputVB.setPadding(new Insets(0, 60, 0, 60));
        fileInputVB.setAlignment(Pos.CENTER);

        VBox centerVB = new VBox(40, label, fileInputVB, enterManuallyBtn, generateRandomBtn);
        centerVB.setAlignment(Pos.CENTER);

        setCenter(centerVB);
        setTop(backBtn);

        BorderPane.setAlignment(backBtn, Pos.TOP_LEFT);

        setPadding(new Insets(20));

        Animation.installFadeTransition(label, 1.5);
        Animation.installTranslateYTransition(loadFromFileBtn, 1, loadFromFileBtn.getTranslateY()+200, loadFromFileBtn.getTranslateY());
        Animation.installTranslateYTransition(enterManuallyBtn, 1.2, loadFromFileBtn.getTranslateY()+150, loadFromFileBtn.getTranslateY());
        Animation.installTranslateYTransition(generateRandomBtn, 1.4, loadFromFileBtn.getTranslateY()+100, loadFromFileBtn.getTranslateY());
    }

    private int[] parseCoinValuesFromFile(File inputFile) throws FileNotFoundException {
        Scanner input = new Scanner(inputFile);
        input.useDelimiter(",");

        int length = 0;

        if (input.hasNext())
            length = input.nextInt();
        else
            // no data at all in the file
            return null;

        if (length < 2)
            // length < min -> let length = 2 and add random numbers
            return new int[]{(int)(1 + Math.random() * 100), (int)(1 + Math.random() * 100)};
        else if (length > 20)
            // length > max -> let length = 20
            length = 20;
        else if (length % 2 != 0)
            // not an even length -> do decrement it
            length--;

        int[] coinValues = new int[length];

        for (int i = 0; i < length; i++)
            coinValues[i] = input.hasNext() ? input.nextInt() : (int)(1 + Math.random() * 100);

        return coinValues;
    }

    private File loadInputFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\ismae\\OneDrive\\Desktop"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return fileChooser.showOpenDialog(getScene().getWindow());
    }

}
