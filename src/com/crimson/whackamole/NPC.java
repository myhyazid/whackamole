package com.crimson.whackamole;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class NPC  {
    private int ID;
    private String name;
    private int addTime;
    private int score;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void genRandMole(GridPane gpGame, GroupofHoles goh, GroupofNPC gon, int randHole, int randNPC){

        goh.getListofHoles(randHole).setOccupant(gon.getListofNPC(randNPC));

        //CountdownTimer.callNPCTimer NPCTime = new CountdownTimer.callNPCTimer();
        Label lb = new Label();
        if (randNPC==0){
            Image image = new Image(getClass().getResourceAsStream("sprites/128x128.png"));
           // Label lb = new Label();
            lb.setGraphic(new ImageView(image));
        }
        else if (randNPC==1){
            Image image = new Image(getClass().getResourceAsStream("sprites/NPC1-128x128.png"));
            //Label lb = new Label();
            lb.setGraphic(new ImageView(image));
        }
        else if (randNPC==2){

            Image image = new Image(getClass().getResourceAsStream("sprites/NPC2-128x128.png"),128,128,false,false);
            //Label lb = new Label();
            lb.setGraphic(new ImageView(image));
        }
        else {
            Image image = new Image(getClass().getResourceAsStream("sprites/Hole-128x128.png"));

            lb.setGraphic(new ImageView(image));
        }

        gpGame.add(lb,goh.getListofHoles(randHole).getPosition().getX(),goh.getListofHoles(randHole).getPosition().getY(),1,1);
        Game g = new Game();
        g.initEvents(goh,lb,randNPC,gpGame);

    }

    public void clearBoard(Label lb, GridPane gpGame,int colIndex, int rowIndex){
        Image image = new Image(getClass().getResourceAsStream("sprites/Hole-128x128.png"));
        lb.setGraphic(new ImageView(image));
        gpGame.add(lb,colIndex,rowIndex,1,1);
    }
}
