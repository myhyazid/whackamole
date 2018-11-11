package com.crimson.whackamole;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    String HighScore = "0";

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


        Group root = new Group();
        GridPane gpGame = new GridPane();
        stage.setTitle("My New Stage Title");
        gpGame.setHgap(10);
        gpGame.setVgap(10);
        gpGame.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 1366, 768, Color.WHITE));
        root.getChildren().add(gpGame);
        root.setTranslateX(400);
        root.setTranslateY(100);
        stage.resizableProperty().setValue(Boolean.FALSE);



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
        //System.out.println(goh.getNumofHoles());
        //System.out.println(goh.getListofHoles(15).getPosition().getY()+","+goh.getListofHoles(15).getPosition().getX());
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

        CountdownTimer FTM = new CountdownTimer();
        Label lbtimer = new Label();
        FTM.call();
        gpGame.add(FTM.timerLabel,1,5,1,1);
        System.out.println(lbtimer.getText());

        goh.getListofHoles(random.nextInt(16)).setOccupant(gon.getListofNPC(random.nextInt(3)));

    }


}
