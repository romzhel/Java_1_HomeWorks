package HomeWork_Lesson8;

import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class GameArea extends GridPane {
    private static final double SIZE = 600;
    private Line[] cellLines;
    private int sideSize;
    private int winLength;

    public GameArea() {
        super();
        setPrefSize(SIZE, SIZE);
    }

    public void init(GameParameters gameParameters) {
        sideSize = gameParameters.getGameAreaSize();
        Line[] temp = new Line[sideSize * 6];
        winLength = gameParameters.getWinLineLength();

        if (getChildren().size() > 1) {
            getChildren().retainAll(getChildren().get(0));
        }

        setGridLinesVisible(true);

        for (int y = 0; y < gameParameters.getGameAreaSize(); y++) {
            for (int x = 0; x < gameParameters.getGameAreaSize(); x++) {
                addRow(y, new Cell(x, y, SIZE / gameParameters.getGameAreaSize()));
            }
        }

        int cellLineIndex = 0;
        for (int x = 0; x < sideSize; x++) {
            temp[cellLineIndex++] = new Line(x, 0, this, new CheckingVector(0, 1));
            temp[cellLineIndex++] = new Line(x, 0, this, new CheckingVector(1, 1));
            temp[cellLineIndex++] = new Line(x, 0, this, new CheckingVector(-1, 1));
        }

        for (int y = 0; y < sideSize; y++) {
            temp[cellLineIndex++] = new Line(0, y, this, new CheckingVector(1, 0));

            Line line = new Line(0, y + 1, this, new CheckingVector(1, 1));
            if (line.getCells().length > 0) {
                temp[cellLineIndex++] = line;
            } else {
                cellLineIndex--;
            }

        }

        for (int x = 0; x < sideSize; x++) {
            Line line = new Line(x + 1, sideSize - 1, this, new CheckingVector(1, -1));
            if (line.getCells().length > 0) {
                temp[cellLineIndex++] = line;
            } else {
                cellLineIndex--;
            }
        }

        cellLines = Arrays.copyOfRange(temp, 0, cellLineIndex - 1);
    }

    public Line getWinLine(char symbol, int length) {
        for (Line line : cellLines) {
            if (line.toString().matches(String.format("^.*[%c]{%d,}.*", symbol, length))) {
                return line;
            }
        }
        return null;
    }

    public void resetCellsWeight() {
        for (Line line : cellLines) {
            line.resetCellsWeight();
        }
    }

    public Cell getCell(int column, int row) {
        return (Cell) getChildren().get(row * sideSize + column + 1);
    }

    public int getSideSize() {
        return sideSize;
    }

    public Line[] getCellLines() {
        return cellLines;
    }

    public int getWinLength() {
        return winLength;
    }

    public Cell getAnyEmptyCell() {
        for (Line line : cellLines) {
            Cell cell = line.getAnyEmptyCell();
            if (cell != null) {
                return cell;
            }
        }
        return null;
    }
}
