package view;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import model.Board;
import model.GridGame;
import model.Point;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;


public class JavaFXView implements View{

    //Background
    public ImageView background;

    //Sound Setting
    public ImageView sound;

    //Button Back
    public Rectangle backgroundBack = new Rectangle(200 , 440, 75, 35);
    public Text backText = new Text(210, 462, "");

    //Menu Acceuil

        //Titre
    public ImageView title;

        //Choix du jeu
    public ImageView imageG1;
    public ImageView imageG2;
    public ImageView imageG3;

    //Board
    public Rectangle[][] board;

    //Player name + Points
    public Text player1 = new Text(20,20,"Player 1");
    public Text player2 = new Text(420,20,"Player 2");
    public Text player1Point = new Text(20, 40 , "Point: 0");
    public Text player2Point = new Text(480, 40 , "Point: 0");

    //Pièces

    public LinkedList<ScaleTransition> scale = new LinkedList<>();
    public LinkedList<ImageView> piecePlayer1 = new LinkedList<>();
    public LinkedList<ImageView> piecePlayer2 = new LinkedList<>();


    @Override
    public void handleBackground(boolean GAME_RUN) {
        String url ="";
        if (!GAME_RUN) {
            url += "/backgroundMenu.jpg";
        }else {
            url += "/backgroundGame.jpg";
        }
        InputStream input = this.getClass().getResourceAsStream(url);
        Image image = new Image(input, 500, 500, false, false);
        background = new ImageView(image);
    }

    @Override
    public void handleSound(boolean isOn) {
        String url = "";
        if (isOn) url += "/volumeOn.png";
        else url += "/volumeOff.png";
        InputStream input = this.getClass().getResourceAsStream(url);
        Image image = new Image(input, 30, 30 , false, false);
        sound = new ImageView(image);
        sound.setX(20);
        sound.setY(460);

    }

    @Override
    public void handleTitle() {
        String url = "/title.png";
        InputStream input = this.getClass().getResourceAsStream(url);
        Image image = new Image(input);
        title = new ImageView(image);
        title.setX(50);
        title.setY(30);
    }

    @Override
    public void handleMenu() {

        String url1 = "/morpion.png";
        InputStream input1 = this.getClass().getResourceAsStream(url1);
        Image image1 = new Image(input1);
        imageG1 = new ImageView(image1);
        imageG1.setX(177);
        imageG1.setY(250);

        String url2 = "/puissance4.png";
        InputStream input2 = this.getClass().getResourceAsStream(url2);
        Image image2 = new Image(input2);
        imageG2 = new ImageView(image2);
        imageG2.setX(175);
        imageG2.setY(150);

        String url3 = "/fobidoshi.png";
        InputStream input3 = this.getClass().getResourceAsStream(url3);
        Image image3 = new Image(input3);
        imageG3 = new ImageView(image3);
        imageG3.setX(175);
        imageG3.setY(350);
    }

    @Override
    public void handleBoard(int size) {
        board = new Rectangle[size][size];
        int boardStartX = 0;
        int boardStartY = 0;
        switch (size) {
            case 3:
                boardStartX = 180;
                boardStartY = 170;
                break;
            case 5:
                boardStartX = 140;
                boardStartY = 135;
                break;

            case 6:
                boardStartX = 130;
                boardStartY = 130;
                break;
        }

        for (int i = 0; i < size; i++) {
            for (int j =0; j < size; j++) {
                board[i][j] = new Rectangle(boardStartX,boardStartY, 40, 40);
                board[i][j].setX(boardStartX + i * 40);
                board[i][j].setY(boardStartY + j * 40);
                board[i][j].setFill(Color.rgb(80,0,115, 0.75));
                board[i][j].setStroke(Color.BLACK);
            }
        }
    }

    @Override
    public void handlePlayerPoint(int player1Point, int player2Point) {
        player1.setFill(Color.WHITE);
        player2.setFill(Color.WHITE);
        this.player1Point.setText(String.valueOf(player1Point));
        this.player1Point.setFill(Color.WHITE);
        this.player2Point.setText(String.valueOf(player2Point));
        this.player2Point.setFill(Color.WHITE);
        player1.setFont(new Font(20));
        player2.setFont(new Font(20));
        this.player1Point.setFont(new Font(20));
        this.player2Point.setFont(new Font(20));

    }

    @Override
    public void handlePiece(GridGame gameState) {
        int boardStartX = 0;
        int boardStartY = 0;
        switch (gameState.getBoardSize()) {
            case 3:
                boardStartX = 180;
                boardStartY = 170;
                break;
            case 5:
                boardStartX = 140;
                boardStartY = 135;
                break;

            case 6:
                boardStartX = 130;
                boardStartY = 130;
                break;
        }
                for (int i = 0; i < gameState.getBoardSize(); i++) {
                    for (int j = 0; j < gameState.getBoardSize(); j++) {
                        if (!gameState.getBoard().getCase(i, j).getPiece().isEmpty()) {
                                if (gameState.getBoard().getCase(i, j).getPiece().isPlayerOne()) {
                                    String url = (gameState.getBoard().getSize() != 5) ? "/Icons/cross.png" : "/Icons/circleBis.png";
                                    InputStream input = this.getClass().getResourceAsStream(url);
                                    Image image = new Image(input,40,40,false,false);
                                    ImageView shape = new ImageView(image);
                                    shape.setX(boardStartX + i * 40);
                                    shape.setY(boardStartY + j * 40);
                                    scale.add(new ScaleTransition(Duration.seconds(0), shape));
                                    gameState.getScaleTransition(scale.getLast());
                                    if (Point.comparePoints(gameState.getLastCaseAdd(), new Point(i,j))) {
                                        if (gameState.getBoardSize() == 6) {
                                            shape.setY(gameState.getLastCaseAdd().getY() * 40);
                                            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0), shape);
                                            gameState.getTranslateTransition(translateTransition);

                                            translateTransition.play();

                                        }else {
                                            scale.getLast().play();
                                            shape.setFitHeight(80);
                                            shape.setFitWidth(80);
                                            shape.setX(boardStartX - 20 + i * 40);
                                            shape.setY(boardStartY - 20 + j * 40);
                                        }
                                    }
                                    piecePlayer2.add(shape);
                                } else {
                                    String url = "/Icons/circle.png";
                                    InputStream input = this.getClass().getResourceAsStream(url);
                                    Image image = new Image(input,40,40,false,false);
                                    ImageView circle = new ImageView(image);
                                    circle.setX(boardStartX + i * 40);
                                    circle.setY(boardStartY + j * 40);
                                    scale.add(new ScaleTransition(Duration.seconds(0), circle));
                                    if (Point.comparePoints(gameState.getLastCaseAdd(), new Point(i,j))) {
                                        if (gameState.getBoardSize() == 6) {
                                            circle.setY(gameState.getLastCaseAdd().getY() * 40);
                                            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0), circle);
                                            gameState.getTranslateTransition(translateTransition);

                                            translateTransition.play();

                                        }else {
                                            gameState.getScaleTransition(scale.getLast());
                                            scale.getLast().play();
                                            circle.setFitHeight(80);
                                            circle.setFitWidth(80);
                                            circle.setX(boardStartX - 20 + i * 40);
                                            circle.setY(boardStartY - 20 + j * 40);
                                        }

                                    }
                                    piecePlayer1.add(circle);
                                }
                        }
                    }
                }
    }

    @Override
    public void handleBack() {
        backgroundBack.setFill(Color.rgb(80,0,115, 0.75));
        backgroundBack.setStroke(Color.PINK);
        backgroundBack.setArcHeight(10);
        backgroundBack.setArcWidth(10);
        backText.setText("Retour");
        backText.setFont(new Font(17));
        backText.setFill(Color.PINK);
    }
}
