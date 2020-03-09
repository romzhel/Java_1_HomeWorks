package HomeWork_Lesson8;

public abstract class Player {
    private String name;
    private char symbol;

    public Player(String name) {
        this.name = name;
    }

    public void makeTurn(GameArea gameArea) {
    }


    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
