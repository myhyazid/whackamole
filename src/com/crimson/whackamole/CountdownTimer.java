package com.crimson.whackamole;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CountdownTimer {


    public Label timerLabel = new Label();


    public void callCountDown(){
         final Integer STARTTIME = 60;
         Timeline timeline = null;

         IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

        // Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

                if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                                new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            }

}