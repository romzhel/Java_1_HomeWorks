package HomeWork_Lesson8;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line {
    private Cell[] cells;

    public Line(int cx, int cy, GameArea gameArea, CheckingVector vector) {
        Cell[] temp = new Cell[gameArea.getSideSize()];
        int tempIndex = 0;

        for (int dx = cx, dy = cy; dx >= 0 && dx < gameArea.getSideSize() && dy >= 0 && dy < gameArea.getSideSize();
             dx += vector.getDeltaX(), dy += vector.getDeltaY()) {

            temp[tempIndex++] = gameArea.getCell(dx, dy);
        }

        cells = Arrays.copyOfRange(temp, 0, tempIndex);
        temp = null;
    }

    public Cell[] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        StringBuilder lineBuilder = new StringBuilder();
        for (Cell cell : cells) {
            lineBuilder.append(cell.getSymbol());
        }

        return lineBuilder.toString();
    }

    public Cell getCell(int index) {
        return index < getLength() ? cells[index] : null;
    }

    public char getSymbol(int index) {
        return index < getLength() ? cells[index].getSymbol() : '-';
    }

    public int getLength() {
        return cells.length;
    }

    public void resetCellsWeight() {
        for (Cell cell : cells) {
            cell.setWeight(0);
        }
    }

    public Cell getBestCell() {
        Cell bestCell = cells[0];
        for (int index = 1; index < cells.length; index++) {
            if (cells[index].getWeight() > bestCell.getWeight()) {
                bestCell = cells[index];
            }
        }

        return bestCell;
    }

    public Cell getAnyEmptyCell() {
        for (Cell cell : cells) {
            if (cell.getSymbol() == ' ') {
                return cell;
            }
        }
        return null;
    }

    public void markWinCells(char symbol, int length) {
        Pattern pattern = Pattern.compile(String.format("[%c]{%d,}", symbol, length));
        Matcher matcher = pattern.matcher(toString());

        if (matcher.find()) {
            for (int index = matcher.start(); index < matcher.end(); index++) {
                cells[index].mark();
            }
        }
    }
}
