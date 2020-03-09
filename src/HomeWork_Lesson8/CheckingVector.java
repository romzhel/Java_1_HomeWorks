package HomeWork_Lesson8;

public class CheckingVector {
    private int deltaX;
    private int deltaY;

    public CheckingVector(int deltaX, int deltaY/*, int moveX, int moveY*/) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }
}
