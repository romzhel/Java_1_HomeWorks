package HomeWork_Lesson8;

import javafx.scene.control.Alert;

public class PlayersTurnsProcessor {
    private static GameArea gameArea;
    private static GameParameters gameParameters;
    private static int currentUser;
    private static boolean gameIsFinished;


    public static void startGame(GameArea gameArea, GameParameters gameParameters) {
        PlayersTurnsProcessor.gameArea = gameArea;
        PlayersTurnsProcessor.gameParameters = gameParameters;
        currentUser = 0;
        gameIsFinished = false;

        nextTurn(null);
    }

    public static void nextTurn(Cell cell) {
        if (gameIsFinished) {
            return;
        }

        if (cell == null) {
            gameParameters.getPlayers()[currentUser].makeTurn(gameArea);
            return;
        }

        if (cell.getSymbol() != ' ') {
            if (gameArea.getAnyEmptyCell() != null) {
                displayAlertWindow("Ход игрока: " + gameParameters.getPlayers()[currentUser].getName(),
                        "Данная ячейка уже занята. Выберите другую.");
            } else {
                displayAlertWindow("Игра окончена", String.format("Ничья",
                        gameParameters.getPlayers()[currentUser].getName()));
                gameIsFinished = true;
            }

            return;
        }

        char playerSymbol = gameParameters.getPlayers()[currentUser].getSymbol();
        cell.changeTo(playerSymbol);

        Line winLine = gameArea.getWinLine(playerSymbol, gameParameters.getWinLineLength());
        if (winLine != null) {
            displayAlertWindow("Игра окончена", String.format("Игрок %s побеждает!!!",
                    gameParameters.getPlayers()[currentUser].getName()));
            winLine.markWinCells(playerSymbol, gameParameters.getWinLineLength());
            gameIsFinished = true;
        }

        currentUser = (currentUser + 1) % gameParameters.getPlayers().length;

        gameParameters.getPlayers()[currentUser].makeTurn(gameArea);
    }

    public static void displayAlertWindow(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
