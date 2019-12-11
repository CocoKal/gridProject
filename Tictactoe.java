package model;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Tictactoe implements GridGame {

    private Board board = new Board(3);
    private Point lastCaseAdd;
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private boolean IS_PLAYER1_TURN = true;


    @Override
    public boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!board.getCase(i, j).getPiece().isEmpty())
                    if (board.getCase(i, j).getPiece().isPlayerOne()) {
                        field[i][j] = "x";
                    } else field[i][j] = "o";
                else field[i][j] = "";
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) &&
                    field[i][0].equals(field[i][2]) &&
                    !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) &&
                    field[0][i].equals(field[2][i]) &&
                    !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2]) &&
                !field[0][0].equals("")) {
            return true;
        }
        return field[0][2].equals(field[1][1]) &&
                field[0][2].equals(field[2][0]) &&
                !field[0][2].equals("");
    }

    @Override
    public boolean checkForDefeat() {
        return false;
    }

    @Override
    public boolean moveIsValid(Point point) {
        return board.getCase(point.getX(), point.getY()).isEmpty();
    }


    @Override
    public void addPiece(Point point) {
        board.getCase(point.getX(), point.getY()).setNewPiece(IS_PLAYER1_TURN);
        lastCaseAdd = point;
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
        board = new Board(3);
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
        scale.setDuration(Duration.seconds(0.1));
        scale.setToX(0.5);
        scale.setToY(0.5);
    }

    @Override
    public void getTranslateTransition(TranslateTransition trans) {

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
