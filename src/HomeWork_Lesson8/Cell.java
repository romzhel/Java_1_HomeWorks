package HomeWork_Lesson8;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Cell extends Pane {
    private char symbol = ' ';
    private int column;
    private int row;
    private int weight;
    private Label lWeight;


    public Cell(int col, int row, double size) {
        super();
        setPrefSize(size, size);
        column = col;
        this.row = row;

        lWeight = new Label();
        lWeight.setLayoutX(size / 2);
        lWeight.setLayoutY(size / 2);
        getChildren().add(lWeight);

        setOnMouseClicked(event -> PlayersTurnsProcessor.nextTurn(Cell.this));
    }

    public void changeTo(char symbol) {
        this.symbol = symbol;

        double lineWidth = getHeight() / 6;

        if (symbol == 'X') {
            Line line1 = new Line(lineWidth, lineWidth, getHeight() - lineWidth, getHeight() - lineWidth);
            Line line2 = new Line(getHeight() - lineWidth, lineWidth, lineWidth, getHeight() - lineWidth);
            line1.setStroke(Color.BLUE);
            line2.setStroke(Color.BLUE);
            line1.setStrokeWidth(lineWidth);
            line2.setStrokeWidth(lineWidth);
            line1.setOpacity(1);
            line2.setOpacity(1);

            getChildren().addAll(line1, line2);
        } else if (symbol == 'O') {
            Circle circle = new Circle(getHeight() / 2, getHeight() / 2, getHeight() / 2 - lineWidth);
            circle.setFill(null);
            circle.setStroke(Color.GREEN);
            circle.setStrokeWidth(lineWidth);

            getChildren().addAll(circle);
        }
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        lWeight.setText(String.valueOf(weight));
    }

    public void applyWeight(int weight) {
        setWeight(this.weight + weight);
    }

    public void mark() {
        setStyle("-fx-background-color: YELLOW;");
    }


    @Override
    public String toString() {
        return String.format("[%d, %d] = %c, %d", column, row, symbol, weight);
    }
}
