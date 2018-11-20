package com.crimson.whackamole;

import com.sun.istack.internal.Nullable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import javax.swing.Timer;
//import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Game{
    private String HighScore = "0";
    public static int score =0;
    String timeleft = null;
    public Label lblScore = new Label();


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
        //String timeleft = null;

        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        GridPane gpHeader = new GridPane();
        GridPane gpGame = new GridPane();
        stage.setTitle("Crimson Whackamole! Game");
        gpGame.setHgap(10);
        stage.centerOnScreen();
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

        //Header
        lblScore.setText("Score : " + score);
        CountdownTimer FTM = new CountdownTimer();
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
        FTM.callCountDown();
        gpHeader.add(lblScore,0,0,1,1);
        gpHeader.add(lblTimer,1,0,1,1);
        gpHeader.add(FTM.timerLabel,2,0,1,1);
        gpHeader.add(btnPause,3,0,1,1);

      // for (int i=0;i<16;i++) {
           // boolean repeat;

        initGameGUI(gpGame,FTM);
    }

    public void initGameGUI(GridPane gpGame, CountdownTimer FTM){
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

        final Timer t = new Timer(2000,null);
        t.addActionListener(new ActionListener(){
            int i=0;

            public void actionPerformed(java.awt.event.ActionEvent e){


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int randHole = random.nextInt(16);
                        int randNPC = random.nextInt(3);

                        NPC mole = new NPC();
                        mole.genRandMole(gpGame,goh,gon,randHole,randNPC);
                        lblScore.setText("Score : " + score);

                       // initEvents(goh);
                        //javaFX operations should go here
                    }
                });


                if(i>27){t.stop();}
                i++;
            }

        });
        t.setRepeats(true);
        t.start();

        //}

        Thread check = new Thread(new Runnable() {
            //String timeleft = null;
            @Override
            public void run() {

                while (!FTM.timerLabel.getText().equals("0")) {
                    int minusoneCInt = Integer.parseInt(FTM.timerLabel.getText())-1;
                    timeleft = Integer.toString(minusoneCInt);
                    //System.out.println(timeleft);


                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //System.out.println("Check : " + timeleft);

                        if (timeleft.equals("0")) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Time's Out");
                            alert.setHeaderText("Congrats! Your Mark is : ");
                            alert.setContentText("" + score);

                            alert.showAndWait();
                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.get() == ButtonType.OK) {
                                timeleft = null;

                                if (score >= Integer.parseInt(HighScore)) {
                                    try (BufferedWriter outWrite = new BufferedWriter(new FileWriter("C:\\Users\\myhyazid\\Desktop\\Bachelor Of Technology\\Sem 2\\OOP Submission\\whackamole\\src\\com\\crimson\\whackamole\\HighScore.txt"))) {
                                        outWrite.write(Integer.toString(score));
                                        outWrite.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                //Platform.setImplicitExit(false);
                                /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent event) {

                                    }
                                });*/
                                Platform.setImplicitExit(false);
                                stage.close();
                            // Hide this current window (if this is what you want)
                                //(Node)(event.getSource())).getScene().getWindow().hide();
                                // ... user chose OK
                            }

                        }
                    }
                });
                //new Main().callMain();
            } // thread
        }); // thread





        check.start();

    }




    int id;
    public void initEvents(GroupofHoles goh, Label lb,int randNPC, GridPane gpGame){

        //for(ic = 0; ic < 15; ic++){

            //Label lb = new Label();
            lb.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            Node source = (Node)e.getSource() ;
                            Integer colIndex = GridPane.getColumnIndex(source);
                            Integer rowIndex = GridPane.getRowIndex(source);

                            for (int i=0;i<16;i++) {
                             if (goh.getListofHoles(i).getPosition().getX() == colIndex && goh.getListofHoles(i).getPosition().getY() == rowIndex) {
                                 id = i;
                             }
                            }
                            pressedButton(id,goh);
                            System.out.println(goh.getListofHoles(id).getOccupant().getID());

                            NPC reset = new NPC();
                            reset.setID(3);
                            reset.setName("Empty");
                            goh.getListofHoles(id).setOccupant(reset);

                            try {
                                reset.clearBoard(lb, gpGame, colIndex, rowIndex);
                            } catch (IllegalArgumentException k){
                               k.printStackTrace();
                            }

                        }
                    });

    }

    private void pressedButton(int id,GroupofHoles goh) {
        int val = goh.getListofHoles(id).getOccupant().getID();


                //if val is 0 = Normal Mole
                //if val is 1 = Special Mole
                //if val is 2 = Timebomb
                //if val is 3 = empty hole

                if (val == 0) {
                    score++;
                } else if (val == 1) { //val==0
                    score = score + 10;
                } else {
                    score--;
                }

                lblScore.setText("Score : " + score);


    }
}

