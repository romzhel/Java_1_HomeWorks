package HomeWork_Lesson8;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameParametersSelectionWindow {
    private GameParameters gameParameters;

    public GameParametersSelectionWindow() {
        Stage stage = new Stage();
        GridPane root = new GridPane();
        root.setHgap(30);
        root.setVgap(20);
        root.setPadding(new Insets(20));

        ToggleGroup tgSymbol = new ToggleGroup();
        RadioButton rbSymbolX = new RadioButton("X");
        RadioButton rbSymbolO = new RadioButton("O");
        rbSymbolX.setToggleGroup(tgSymbol);
        rbSymbolO.setToggleGroup(tgSymbol);
        rbSymbolX.setSelected(true);
        root.addRow(0, new Label("Ваш значок: "), rbSymbolX, rbSymbolO);

        ToggleGroup tgSecondPlayer = new ToggleGroup();
        RadioButton rbPC = new RadioButton("Компьютер");
        RadioButton rbHuman = new RadioButton("Человек");
        rbPC.setToggleGroup(tgSecondPlayer);
        rbHuman.setToggleGroup(tgSecondPlayer);
        rbPC.setSelected(true);
        root.addRow(1, new Label("Ваш соперник: "), rbPC, rbHuman);

        Slider slSize = new Slider();
        slSize.setMin(3.0);
        slSize.setMax(10.0);
        slSize.setShowTickLabels(true);
        slSize.setShowTickMarks(true);
        slSize.setMajorTickUnit(1);
        slSize.setMinorTickCount(0);
        slSize.setSnapToTicks(true);
        root.add(new Label("Размер поля:"), 0, 2);
        root.add(slSize, 1, 2, 2, 1);

        Slider slWinLen = new Slider();
        slWinLen.setMin(slSize.getMin());
        slWinLen.setMax(slSize.getValue());
        slWinLen.setShowTickLabels(true);
        slWinLen.setShowTickMarks(true);
        slWinLen.setMajorTickUnit(1);
        slWinLen.setMinorTickCount(0);
        slWinLen.setSnapToTicks(true);
        root.add(new Label("Длина линии для выигрыша:"), 0, 3);
        root.add(slWinLen, 1, 3, 2, 1);

        slSize.valueProperty().addListener((observable, oldValue, newValue) -> slWinLen.setMax((double) newValue));

        Button btnStartGame = new Button("Начать игру");
        btnStartGame.setPrefWidth(200);
        btnStartGame.setOnAction((event) -> stage.close());
        root.add(btnStartGame, 1, 5, 2, 1);

        Scene scene = new Scene(root, 500, 300);

        stage.setScene(scene);
        stage.setTitle("Выбор параметров игры");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        gameParameters = new GameParameters(2);
        Player player1 = new PlayerHuman("Основной игрок");
        player1.setSymbol(rbSymbolX.isSelected() ? 'X' : 'O');
        Player player2 = rbPC.isSelected() ? new PlayerPC("Компьютер") : new PlayerHuman("Второй человек");
        player2.setSymbol(rbSymbolX.isSelected() ? 'O' : 'X');
        gameParameters.setPlayers(new Player[]{player1, player2});
        gameParameters.setGameAreaSize((int) slSize.getValue());
        gameParameters.setWinLineLength((int) slWinLen.getValue());
    }

    public GameParameters getGameParameters() {
        return gameParameters;
    }
}
