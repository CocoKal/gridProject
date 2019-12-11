package model;

public class Piece {
    private boolean isPlayerOne;
    private boolean isEmpty;

    Piece() {
        isEmpty = true;
    }

    Piece(boolean isPlayerOne) {
        isEmpty = false;
        this.isPlayerOne = isPlayerOne;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isPlayerOne() {
        return isPlayerOne;
    }

    void setEmpty(boolean empty) {
        isEmpty = empty;
    }

}
