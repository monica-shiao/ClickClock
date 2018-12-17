package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Controller {
    @FXML
    private ImageView btn_circle_clock, btn_digit_clock, btn_time_zone, btn_switch_hour;

    @FXML
    private AnchorPane h_circle_clock, h_digit_clock, h_time_zone,h_switch_hour;

    @FXML
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget() == btn_circle_clock){

            ClockPane clock=new ClockPane();
            //BorderPane borderPane=new BorderPane();

            EventHandler<ActionEvent> eventHandler= e -> {
                h_circle_clock.getChildren().clear();
                clock.setCurrentTime();
                String timeString=clock.getHour()+":"+clock.getMinute()+":"+clock.getSecond();
                Label lblCurrentTime=new Label(timeString);
                lblCurrentTime.setTextFill(Color.web("#0076a3"));
                lblCurrentTime.setFont(new Font("Arial", 30));

                h_circle_clock.setTopAnchor(clock,50.0);
                h_circle_clock.setRightAnchor(clock, 100.0);
                h_circle_clock.setBottomAnchor(lblCurrentTime,50.0);
                h_circle_clock.setRightAnchor(lblCurrentTime,150.0);

                h_circle_clock.getChildren().add(clock);
                h_circle_clock.getChildren().add(lblCurrentTime);
            };

            Timeline animation=new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();

            h_circle_clock.widthProperty().addListener(ov ->
                    clock.setW(h_circle_clock.getWidth())
            );

            h_circle_clock.heightProperty().addListener(ov ->
                    clock.setH(h_circle_clock.getHeight())
            );
            h_circle_clock.setVisible(true);
            h_digit_clock.setVisible(false);
            h_time_zone.setVisible(false);
            h_switch_hour.setVisible(false);

        }
        else if(event.getTarget() == btn_digit_clock){
            h_circle_clock.setVisible(false);
            h_digit_clock.setVisible(true);
            h_time_zone.setVisible(false);
            h_switch_hour.setVisible(false);

        }
        else if(event.getTarget() == btn_time_zone){
            h_circle_clock.setVisible(false);
            h_digit_clock.setVisible(false);
            h_time_zone.setVisible(true);
            h_switch_hour.setVisible(false);
        }
        else{
            h_circle_clock.setVisible(false);
            h_digit_clock.setVisible(false);
            h_time_zone.setVisible(false);
            h_switch_hour.setVisible(true);
        }
    }
}