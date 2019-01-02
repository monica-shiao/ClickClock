package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Controller {
    @FXML
    private ImageView btn_circle_clock, btn_digit_clock, btn_time_zone, btn_switch_hour;

    @FXML
    private AnchorPane h_circle_clock, h_digit_clock, h_time_zone,h_switch_hour;

    @FXML
    private CheckBox cb_taiwan, cb_america, cb_japan, cb_uk;

    @FXML
    Label dateLabel,timeLabel,dayLabel, lb_area;

    private int country=0;
    private String[] country_str = {"Asia/Shanghai", "America/New_York", "JST" ,"Europe/London"};
    Date date;

    @FXML
    private void handleButtonAction(MouseEvent event){
        if(event.getTarget() == btn_circle_clock){

            ClockPane clock=new ClockPane();

            EventHandler<ActionEvent> eventHandler= e -> {
                h_circle_clock.getChildren().clear();
                clock.setCurrentTime(country);
                String timeString = clock.getHour()+":"+clock.getMinute()+":"+clock.getSecond();

                SimpleDateFormat time_format = new SimpleDateFormat("hh:mm:ss");
                Calendar calendar = Calendar.getInstance();
                time_format.setTimeZone(TimeZone.getTimeZone(country_str[country]));
                date = calendar.getTime();
                String time = time_format.format(date);


                Label lblCurrentTime=new Label();
                lblCurrentTime.setText(time);
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
            EventHandler<ActionEvent> eventHandler = (e) -> {
                digitclock clock = new digitclock();
                clock.calendar = Calendar.getInstance();

                // Time
                clock.format = new SimpleDateFormat("hh:mm:ss a");
                clock.format.setTimeZone(TimeZone.getTimeZone(country_str[country]));
                clock.date = clock.calendar.getTime();
                clock.time = clock.format.format(clock.date);

                // Year / Month / Day
                clock.format2 = new SimpleDateFormat("西元yyyy年 MMMM dd ");
                clock.format2.setTimeZone(TimeZone.getTimeZone(country_str[country]));
                clock.date = clock.calendar.getTime();
                clock.month = clock.format2.format(clock.date);

                // Day of week
                clock.format3 = new SimpleDateFormat("EEEE");
                clock.format3.setTimeZone(TimeZone.getTimeZone(country_str[country]));
                clock.date = clock.calendar.getTime();
                clock.day = clock.format3.format(clock.date);

                dateLabel.setText(String.valueOf(clock.month));
                timeLabel.setText(clock.time);
                dayLabel.setText(clock.day);
            };
            Timeline animation = new Timeline(new KeyFrame[]{new KeyFrame(Duration.millis(1000.0D), eventHandler, new KeyValue[0])});
            animation.setCycleCount(-1);
            animation.play();
            h_circle_clock.setVisible(false);
            h_digit_clock.setVisible(true);
            h_time_zone.setVisible(false);
            h_switch_hour.setVisible(false);

        }
        else if(event.getTarget() == btn_time_zone){
            cb_taiwan.setOnAction(eh);
            cb_america.setOnAction(eh);
            cb_japan.setOnAction(eh);
            cb_uk.setOnAction(eh);

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

    EventHandler eh = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() instanceof CheckBox) {
                CheckBox chk = (CheckBox) event.getSource();
                if ("Taiwan".equals(chk.getText())) {
                    country=0;
                    lb_area.setText("Taiwan");
                    cb_taiwan.setSelected(true);
                    cb_america.setSelected(false);
                    cb_japan.setSelected(false);
                    cb_uk.setSelected(false);
                }
                else if ("New York".equals(chk.getText())) {
                    country=1;
                    lb_area.setText("New York");
                    cb_taiwan.setSelected(false);
                    cb_america.setSelected(true);
                    cb_japan.setSelected(false);
                    cb_uk.setSelected(false);
                }
                else if ("Japan".equals(chk.getText())) {
                    country=2;
                    lb_area.setText("Japan");
                    cb_taiwan.setSelected(false);
                    cb_america.setSelected(false);
                    cb_japan.setSelected(true);
                    cb_uk.setSelected(false);
                }
                else if ("United Kingdom".equals(chk.getText())) {
                    country=3;
                    lb_area.setText("United Kingdom");
                    cb_taiwan.setSelected(false);
                    cb_america.setSelected(false);
                    cb_japan.setSelected(false);
                    cb_uk.setSelected(true);
                }
            }
        }
    };
}


