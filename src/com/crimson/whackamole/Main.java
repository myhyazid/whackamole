package com.crimson.whackamole;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        
        // Layout declaration
        GridPane gp = new GridPane();
        Scene sc = new Scene(gp,1366,768);

        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);

        // Layout element
        Label lb = new Label("Email : ");
        TextField tf = new TextField();
        Button btn = new Button();

        Label lb2 = new Label("Password : ");
        TextField tf2 = new TextField();
        Button btn2 = new Button();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Layout behaviour
        btn.setText("Submit");
        btn2.setText("Clear");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf.getText().equals("")){
                    System.out.println("Crimson Whackamole");
                }

                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Look, a Confirmation Dialog");
                alert.setContentText("Do you want to proceed with login?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                } else {
                    // ... user chose CANCEL or closed the dialog
                }

            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tf.clear();
                tf2.clear();
            }
        });




        // Application
        gp.add(lb,0,0,1,1);
        gp.add(tf,1,0,1,1);
        gp.add(btn,0,2,1,1);
        gp.add(tf2,1,1,1,1);
        gp.add(lb2,0,1,1,1);
        gp.add(btn2,1,2,1,1);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(sc);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
