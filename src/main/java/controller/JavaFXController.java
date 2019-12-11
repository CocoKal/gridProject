package controller;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import view.JavaFXView;
import java.util.ArrayList;
import java.util.LinkedList;


class JavaFXController {

    private MediaPlayer mediaPlayer;
    private boolean soundIsOn = true;

    private EventHandler<? super MouseEvent> eventHandler;
    private Group root;
    private Stage primaryStage;
    private JavaFXView view;
    private boolean GAME_RUN;

    private GridGame gameState;


    JavaFXController(Stage primaryStage) {

        GAME_RUN = false;
        this.primaryStage = primaryStage;
        setEventHandler();
        resetDisplay();
        Media sound = new Media(getClass().getResource("/mainTheme.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
    }

    private void resetDisplay() {
        getNewRoot();
        root.getChildren().add(new Canvas(Constante.WIDHT, Constante.HEIGHT));
        Scene scene = new Scene(root);
        setEventHandler();
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setEventHandler() {

        if (!GAME_RUN) {
            eventHandler = (EventHandler<MouseEvent>) event -> {

                if (event.getX() <= 300 && event.getX() >= 200) {

                    initGameState(event);
                    resetDisplay();
                }
                if (event.getX() >= 10 && event.getX() <= 50
                        && event.getY() >= 450 && event.getY() <= 490) {
                    settingsSound();
                    resetDisplay();
                }
            };
        }
        else {
            eventHandler = (EventHandler<MouseEvent>) event -> {

                if (!gameState.getBoard().boardIsFull()) {
                    for (int i = 0; i < gameState.getBoardSize(); i++) {
                        for (int j = 0; j < gameState.getBoardSize(); j++) {
                            if (event.getX() <= view.board[i][j].getX() + 40
                                    && event.getX() >= view.board[i][j].getX()
                                    && event.getY() <= view.board[i][j].getY() + 40
                                    && event.getY() >= view.board[i][j].getY()) {

                                if (gameState.moveIsValid(new Point(i,j))) {
                                    doMove(new Point(i,j));
                                    if (gameState.checkForWin()) {
                                        currentPlayerWin();
                                    }
                                }
                                if (gameState.checkForDefeat()) {
                                    currentPlayerLost();
                                }
                            }
                        }
                    }
                    if (event.getX() >= 200 && event.getX() <= 275
                            && event.getY() >= 440 && event.getY() <= 475) {
                        resetAttributs();
                        resetDisplay();
                    }
                    if (event.getX() >= 10 && event.getX() <= 50
                            && event.getY() >= 450 && event.getY() <= 490) {
                        settingsSound();
                        resetDisplay();
                    }
                }
                else {
                    if (gameState.checkForWin()) {
                        gameState.playerWinAPoint();
                    }
                    gameState.resetGame();
                    resetDisplay();
                }
            };
        }
    }

    private void initGameState(MouseEvent event) {
        if (event.getY() <= 270 && event.getY() >= 250) gameState = new Tictactoe();
        if (event.getY() <= 185 && event.getY() >= 150) gameState = new Puissance4();
        if (event.getY() <= 385 && event.getY() >= 350) gameState = new Fobidoshi();

        GAME_RUN = true;
        root = new Group();
    }

    private void settingsSound() {
        if (soundIsOn) mediaPlayer.stop();
        else mediaPlayer.play();
        soundIsOn = ! soundIsOn;
    }

    private void currentPlayerLost() {
        gameState.otherPlayerWinAPoint();
        gameState.resetGame();
        resetDisplay();
    }

    private void currentPlayerWin() {
        gameState.playerWinAPoint();
        gameState.resetGame();
        resetDisplay();
    }

    private void doMove(Point point) {
        gameState.addPiece(point);
        resetDisplay();
        gameState.invertPlayerTurn();
    }

    private void getNewRoot() {
        if (!GAME_RUN) {
            handleMenu();
        }
        else {
            handleGame();
        }
    }

    private void handleGame() {
        view = new JavaFXView();
        view.handleBackground(true);
        view.handleSound(soundIsOn);
        view.handlePlayerPoint(gameState.getScorePlayer1(), gameState.getScorePlayer2());
        if (gameState.getBoard().getSize() == 5) {
            view.player1.setText("Victoire");
            view.player2.setText("Defaite");
        }
        view.handleBoard(gameState.getBoardSize());
        view.handlePiece(gameState);
        view.handleBack();
        addToRoot(view, true);
    }

    private void addToRoot(JavaFXView view, boolean gameIsRunning) {
        root = new Group();
        if (gameIsRunning) {
            root.getChildren().addAll(view.background, view.sound, view.player1, view.player2, view.player1Point, view.player2Point, view.backgroundBack, view.backText);
            addBoardToRoot(view);
            addPieceToRoot(view.piecePlayer1);
            addPieceToRoot(view.piecePlayer2);
        } else {
            root.getChildren().addAll(view.background ,view.sound ,view.title, view.imageG1,view.imageG2,view.imageG3);
        }
    }

    private void addPieceToRoot(LinkedList<ImageView> piecePlayer) {
        for (ImageView image : piecePlayer)
            root.getChildren().add(image);
    }

    private void addBoardToRoot(JavaFXView view) {
        for (int i = 0; i < gameState.getBoardSize(); i++)
            for (int j = 0; j < gameState.getBoardSize(); j++)
                root.getChildren().add(view.board[i][j]);
    }

    private void handleMenu() {
        view = new JavaFXView();
        view.handleBackground(false);
        view.handleSound(soundIsOn);
        view.handleTitle();
        view.handleMenu();
        setEventHandler();
        addToRoot(view, false);
    }

    private void resetAttributs() {
        GAME_RUN = false;
        gameState.resetAtttribut();
    }


}
