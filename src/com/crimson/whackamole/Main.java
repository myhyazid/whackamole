package com.crimson.whackamole;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//import java.awt.*;
import java.util.Optional;

public class Main extends Application {
    public String mainPos;
  //  public String HS;


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // Layout declaration
        GridPane gp = new GridPane();
        Scene sc = new Scene(gp, 1366, 768, Color.WHITE);

        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);

        // Layout element
        Image image = new Image(getClass().getResourceAsStream("sprites/64x64.png"));
        Label lb = new Label(" Whack-a-mole");
        Label lbHS = new Label();
        lb.setGraphic(new ImageView(image));
        lb.setFont(new Font("Comic-Sans", 36));

        Button btn = new Button("Play Now!");

        Game g = new Game();
        lbHS.setText("Current High Score :  " + g.getHighScore());
        lbHS.setFont(new Font(22));

        // Layout behaviour
        //btn.setText("Submit");
        btn.setAlignment(Pos.CENTER);
        btn.setFont(new Font(36));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                g.stage.show();
                g.initGUI();
                // Hide this current window (if this is what you want)
                //mainPos = ((Node)(event.getSource())).getScene().getWindow().toString();
                //Platform.setImplicitExit(false);
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        });



        g.stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {  // Jangan Kacau dlm ni

                primaryStage.show();
                //lbHS.setText(HS);
                restart(primaryStage);
                // Platform.setImplicitExit(true);
                // Platform.exit();
                //g.stage.close();

            }
        });



        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });


        // Application
        gp.add(lb, 0, 0, 1, 1);
        //gp.add(tf,1,0,1,1);
        gp.add(btn, 0, 1, 1, 1);
        // gp.add(tf2,1,1,1,1);
        gp.add(lbHS, 0, 2, 1, 1);
        // gp.add(btn2,1,2,1,1);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    void restart(Stage stage) {
        try {
            start(stage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
