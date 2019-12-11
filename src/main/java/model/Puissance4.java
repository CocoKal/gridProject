package model;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Puissance4 implements GridGame {

    private Board board = new Board(6);
    private Point lastCaseAdd;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private boolean IS_PLAYER1_TURN = true;


    @Override
    public boolean checkForWin() {
        String[][] field = new String[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (!board.getCase(i, j).getPiece().isEmpty())
                    if (board.getCase(i, j).getPiece().isPlayerOne()) {
                        field[i][j] = "x";
                    } else field[i][j] = "o";
                else field[i][j] = "";
            }
        }

        for (int j = 3; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (field[i][j - 3].equals(field[i][j - 2]) &&
                        field[i][j - 3].equals(field[i][j - 1]) &&
                        field[i][j - 3].equals(field[i][j]) &&
                        !field[i][j - 3].equals("")) {
                    return true;

                }
            }
        }
        for (int j = 3; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (field[j - 3][i].equals(field[j - 2][i]) &&
                        field[j - 3][i].equals(field[j - 1][i]) &&
                        field[j - 3][i].equals(field[j][i]) &&
                        !field[j - 3][i].equals("")) {
                    return true;

                }
            }
        }

        for (int i = 3; i < 6; i++) {
            if (field[i - 3][i - 3].equals(field[i - 2][i - 2]) &&
                    field[i - 3][i - 3].equals(field[i - 1][i - 1]) &&
                    field[i - 3][i - 3].equals(field[i][i]) &&
                    !field[i - 3][i - 3].equals("")) {
                return true;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (field[i - 3][i].equals(field[i - 2][i - 1]) &&
                    field[i - 3][i].equals(field[i - 1][i - 2]) &&
                    field[i - 3][i].equals(field[i][i - 3]) &&
                    !field[i - 3][i].equals("")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkForDefeat() {
        return false;
    }

    @Override
    public boolean moveIsValid(Point point) {
        return !board.colIsFull(point.getX());
    }

    @Override
    public void addPiece(Point point) {
        lastCaseAdd = new Point(point.getX() , board.topOfCol(point.getX()));
        board.getCase(point.getX() , board.topOfCol(point.getX())).setNewPiece(IS_PLAYER1_TURN);

    }

    @Override
    public void playerWinAPoint() {
        if (IS_PLAYER1_TURN) scorePlayer1++;
        else scorePlayer2++;
    }

    @Override
    public void otherPlayerWinAPoint() {
        if (IS_PLAYER1_TURN) scorePlayer2++;
        else scorePlayer1++;
    }

    @Override
    public void invertPlayerTurn() {
        IS_PLAYER1_TURN = !IS_PLAYER1_TURN;
    }

    @Override
    public void resetGame() {
        board = new Board(6);
        IS_PLAYER1_TURN = true;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Point getLastCaseAdd() {
        return lastCaseAdd;
    }

    @Override
    public void getScaleTransition(ScaleTransition scale) {
    }

    public void getTranslateTransition (TranslateTransition trans) {
        trans.setDuration(Duration.seconds(0.3));
        trans.setByY(130);
    }

    @Override
    public int getBoardSize() {
        return board.getSize();
    }

    @Override
    public int getScorePlayer1() {
        return scorePlayer1;
    }

    @Override
    public int getScorePlayer2() {
        return scorePlayer2;
    }

    @Override
    public boolean isPlayerOneTurn() {
        return IS_PLAYER1_TURN;
    }

    @Override
    public void resetAtttribut() {
        board = null;
        scorePlayer2 = 0;
        scorePlayer1 = 0;
    }
}
