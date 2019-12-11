package model;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;

public interface GridGame {

    boolean checkForWin();
    boolean checkForDefeat();
    boolean moveIsValid(Point point);
    void addPiece(Point point);
    void playerWinAPoint();
    void otherPlayerWinAPoint();
    void invertPlayerTurn();
    void resetGame();

    Board getBoard();
    Point getLastCaseAdd();
    void getScaleTransition(ScaleTransition scale);
    void getTranslateTransition(TranslateTransition trans);
    int getBoardSize();
    int getScorePlayer1();
    int getScorePlayer2();
    boolean isPlayerOneTurn();

    void resetAtttribut();

}
