package HomeWork_Lesson8;

public class GameParameters {
    private Player[] players;
    private int gameAreaSize;
    private int winLineLength;

    public GameParameters(int playerCount) {
        players = new Player[playerCount];
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getGameAreaSize() {
        return gameAreaSize;
    }

    public void setGameAreaSize(int gameAreaSize) {
        this.gameAreaSize = gameAreaSize;
    }

    public int getWinLineLength() {
        return winLineLength;
    }

    public void setWinLineLength(int winLineLength) {
        this.winLineLength = winLineLength;
    }

    @Override
    public String toString() {
        return String.format("symbol: %c, second player: %s, size: %d, win length: %d", players[0].getSymbol(),
                players[1].getName(), gameAreaSize, winLineLength);
    }
}
