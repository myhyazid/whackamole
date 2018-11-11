package com.crimson.whackamole;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    String HighScore = "0";
    int score;



    public String getHighScore() {
        return HighScore;
    }

    public void setHighScore(String highScore) {
        HighScore = highScore;
    }


    public Stage stage = new Stage();
    public Game(){
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\myhyazid\\Desktop\\Bachelor Of Technology\\Sem 2\\OOP Submission\\whackamole\\src\\com\\crimson\\whackamole\\HighScore.txt"));
            while (scanner.hasNextLine()) {
                this.setHighScore(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initGUI(){


        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane gpHeader = new GridPane();
        GridPane gpGame = new GridPane();
        stage.setTitle("My New Stage Title");
        gpGame.setHgap(10);
        gpGame.setVgap(10);
        gpGame.setTranslateY(100.0);
        gpGame.setAlignment(Pos.CENTER);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);

        gpHeader.getColumnConstraints().addAll(column1, column2,column3,column4);

        stage.setScene(new Scene(root, 1366, 768, Color.WHITE));

        root.getChildren().add(gpHeader);
        root.getChildren().add(gpGame);

        stage.resizableProperty().setValue(Boolean.FALSE);

        //DEBUG
        gpHeader.setStyle("-fx-background-color: green;");
        gpGame.setStyle("-fx-background-color: red;");


        GroupofHoles goh = new GroupofHoles();
        for (int i=0;i<4;i++){
            Hole rowY = new Hole();

            for (int j=0;j<4;j++) {
                Hole columnX = new Hole();
                POS poslocate = new POS();

                poslocate.setX(j); //set columns
                poslocate.setY(i); //set rows

                if (j == 0) {
                    rowY.setPosition(poslocate);
                    rowY.setID(i);
                    goh.addHole(rowY);
                }
                else {
                    columnX.setPosition(poslocate);
                    columnX.setID(j);
                    goh.addHole(columnX);
                    //System.out.println(goh.getNumofHoles());
                }

            }

        }
        //System.out.println(goh.getListofHoles(15).getPosition()
        //        //System.out.println(goh.getNumofHoles());.getY()+","+goh.getListofHoles(15).getPosition().getX());
        for (int i=0;i<4;i++){
            Image image = new Image(getClass().getResourceAsStream("sprites/Hole-128x128.png"));
            Label lb = new Label();
            lb.setGraphic(new ImageView(image));
            gpGame.add(lb,0,i,1,1);
            for (int j=0;j<4;j++){
                Image imageCol = new Image(getClass().getResourceAsStream("sprites/Hole-128x128.png"));
                Label lbCol = new Label();
                lbCol.setGraphic(new ImageView(imageCol));
                gpGame.add(lbCol,j,i,1,1);
            }

        }

        Random random = new Random();
        GroupofNPC gon = new GroupofNPC();

        for (int i=0;i<3;i++){
            NPC bot = new NPC();
            bot.setID(i);
            if (i==0){
                bot.setName("Normal");
                bot.setAddTime(0);
                bot.setScore(50);
            }
            else if (i==1){
                bot.setName("Special");
                bot.setAddTime(50);
                bot.setScore(150);
            }
            else if (i==2){
                bot.setName("TimeBomb");
                bot.setAddTime(-50);
                bot.setScore(-10);
            }
            gon.addNPC(bot);

        }



        //Header

        CountdownTimer FTM = new CountdownTimer();
        Label lblScore = new Label("Score : " + score);
        Label lblTimer = new Label("Time Left : ");
        Button btnPause = new Button("Pause ☰");
        lblScore.setTextFill(Color.RED);
        lblScore.setStyle("-fx-font-size: 4em;");

        lblTimer.setTextFill(Color.RED);
        lblTimer.setStyle("-fx-font-size: 4em;");
        lblTimer.setAlignment(Pos.CENTER);

        btnPause.setAlignment(Pos.CENTER_RIGHT);
        btnPause.setStyle("-fx-font-size: 3em;");

       // btnPause.setStyle("-fx-font-size: 4em;");
        btnPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hi");
            }
        });
        FTM.call();
        gpHeader.add(lblScore,0,0,1,1);
        gpHeader.add(lblTimer,1,0,1,1);
        gpHeader.add(FTM.timerLabel,2,0,1,1);
        gpHeader.add(btnPause,3,0,1,1);

        goh.getListofHoles(random.nextInt(16)).setOccupant(gon.getListofNPC(random.nextInt(3)));

    }


}
