package model;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Fobidoshi implements GridGame {

    private Board board;
    private Point lastCaseAdd = new Point(0,0);
    private int scorePlayer1;
    private int scorePlayer2;
    private boolean IS_PLAYER1_TURN;

    public Fobidoshi() {
        board = new Board(5);
        initBoard();
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        IS_PLAYER1_TURN = true;
    }

    @Override
    public boolean checkForWin() {
        int compteurEmpty;
        for (int i = 0; i < 5; i++) {
            compteurEmpty = 0;
            for (int j = 0; j < 5; j++) {
                if (board.getCase(i,j).isEmpty()) {
                    compteurEmpty++;
                    if (compteurEmpty > 2) return false;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            compteurEmpty = 0;
            for (int j = 0; j < 5; j++) {
                if (board.getCase(j,i).isEmpty()) {
                    compteurEmpty++;
                    if (compteurEmpty > 2) return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean checkForDefeat() {
            String[][] field = new String[5][5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!board.getCase(i, j).getPiece().isEmpty())
                        field[i][j] = "o";
                    else field[i][j] = "";
                }
            }
            int c;
            for (int i = 0; i < 5; i++) {
                c = 0;
                for (int j = 0; j<5; j++) {
                        if (field[i][j].equals("o")) c++;
                        else c = 0;
                        if (c > 3) return true;
                }
            }
            for (int i = 0; i < 5; i++) {
               c = 0;
                for (int j = 0; j<5; j++) {
                    if (field[j][i].equals("o")) c++;
                    else c = 0;
                    if (c > 3) return true;
                }
           }
            return false;
        }

    @Override
    public boolean moveIsValid(Point point) {
        if (board.getCase(point.getX(), point.getY()).isEmpty()) return true;
        else {
            return board.getCase(point.getX(), point.getY()).isPlayerOne();
        }
    }


    @Override
    public void addPiece(Point point) {
        if (board.getCase(point.getX(), point.getY()).isEmpty()) {
            board.getCase(point.getX(), point.getY()).setNewPiece(true);
            lastCaseAdd = point;
        }
        else {
            board.getCase(point.getX(), point.getY()).setEmpty(true);
        }
    }

    @Override
    public void playerWinAPoint() {
        scorePlayer1++;
    }

    @Override
    public void otherPlayerWinAPoint() {
        if (IS_PLAYER1_TURN) scorePlayer2++;
        else scorePlayer1++;
    }

    @Override
    public void invertPlayerTurn() {
    }

    @Override
    public void resetGame() {
        board = new Board(5);
        initBoard();
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
        scale.setDuration(Duration.seconds(0.3));
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

    private void initBoard() {
        int c;
        int random;
        for (int i = 0; i < 5; i++) {
            c = 0;
            for (int j =0; j <5; j++) {
                random = (int)(Math.random() * (11) + 1);
                if (random < 5 && c < 3){
                    board.getCase(i,j).setNewPiece(false);
                    c++;
                }
                if (checkForDefeat()) {
                    board.getCase(i,j).setEmpty(true);
                }
            }
        }
    }
}
