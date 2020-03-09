package HomeWork_Lesson8;

public class PlayerPC extends Player {
    private AI ai = new AI();

    public PlayerPC(String name) {
        super(name);
    }

    @Override
    public void makeTurn(GameArea gameArea) {
        gameArea.resetCellsWeight();

        Cell turnCell = gameArea.getAnyEmptyCell();
        for (Line line : gameArea.getCellLines()) {
            ai.analiseLine(line, getSymbol(), gameArea.getWinLength(), 10);
            ai.analiseLine(line, getSymbol() == 'X' ? 'O' : 'X', gameArea.getWinLength(), 10);

            Cell bestLineCell = line.getBestCell();
            if (bestLineCell.getWeight() > turnCell.getWeight()) {
                turnCell = bestLineCell;
            }
        }

        PlayersTurnsProcessor.nextTurn(turnCell);
    }
}
