package model;

public class Board {
    private Case[][] board;
    private int size;

    Board (int size) {
        this.size = size;
        board = new Case[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = new Case();
                }
            }
    }

    public Case[][] getBoard() {
        return board;
    }

    public Case getCase(int x, int y) {
        return board[x][y];
    }

    public int getSize() {
        return size;
    }

    public boolean boardIsFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    int topOfCol(int i) {
        for (int j = size-1; j >= 0; j--) {
            if (board[i][j].isEmpty()) return j;
        }
        return size-1;
    }

    boolean colIsFull(int i) {
        return (!board[i][0].isEmpty());
    }
}
