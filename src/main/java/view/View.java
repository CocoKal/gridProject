package view;

import model.*;

public interface View {
    void handleBackground(boolean GAME_RUN);
    void handleSound(boolean isOn);
    void handleTitle();
    void handleMenu();
    void handleBoard(int size);
    void handlePlayerPoint(int player1Point, int player2Point);
    void handlePiece(GridGame gameState);
    void handleBack();

}
