package model;

public class Case {
    private Piece piece;
    private boolean isEmpty;

    Case() {
        piece = new Piece();
        isEmpty = true;
    }

    public Piece getPiece() {
        return piece;
    }

    boolean isEmpty() {
        return isEmpty;
    }

    boolean isPlayerOne() {
        return piece.isPlayerOne();
    }

    void setEmpty(boolean empty) {
        isEmpty = empty;
        piece.setEmpty(empty);
    }

    void setPiece(Piece piece) {
        this.piece = piece;
        isEmpty = false;
    }

    void setNewPiece(boolean isPlayerOne) {
        this.piece = new Piece(isPlayerOne);
        isEmpty = false;
    }
}
