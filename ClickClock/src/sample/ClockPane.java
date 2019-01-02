package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ClockPane extends Pane{
    private int hour;
    private int minute;
    private int second;
    private double w=250,h=250;
    private String[] country = {"Asia/Shanghai", "America/New_York", "JST" ,"Europe/London"};

    public ClockPane() {
        setCurrentTime(0);
    }

    public ClockPane(int hour, int minute, int second) {
        this.hour=hour;
        this.minute=minute;
        this.second=second;
        paintClock();
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour=hour;
        paintClock();
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute=minute;
        paintClock();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second=second;
        paintClock();
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w=w;
        paintClock();
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h=h;
        paintClock();
    }

    public void setCurrentTime(int num) {
        Calendar calendar=new GregorianCalendar(TimeZone.getTimeZone(country[num]));
        this.hour=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute=calendar.get(Calendar.MINUTE);
        this.second=calendar.get(Calendar.SECOND);

        paintClock();
    }

    protected void paintClock() {
        double clockRadius=Math.min(w,h)*0.8*0.5;
        double centerX=w/2;
        double centerY=h/2;

        Circle circle=new Circle(centerX,centerY,clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        Text t12=new Text(centerX-5,centerY-clockRadius+15,"12");
        Text t1=new Text(centerX+clockRadius-55,centerY-clockRadius+30,"1");
        Text t2=new Text(centerX+clockRadius-25,centerY-clockRadius+63,"2");
        Text t3=new Text(centerX+clockRadius-15,centerY+5,"3");
        Text t4=new Text(centerX+clockRadius-25,centerY+47,"4");
        Text t5=new Text(centerX+clockRadius-55,centerY+80,"5");
        Text t6=new Text(centerX-3,centerY+clockRadius-7,"6");
        Text t7=new Text(centerX-50,centerY+clockRadius-20,"7");
        Text t8=new Text(centerX-clockRadius+20,centerY+47,"8");
        Text t9=new Text(centerX-clockRadius+5,centerY+5,"9");
        Text t10=new Text(centerX-clockRadius+20,centerY-clockRadius+63,"10");
        Text t11=new Text(centerX-50,centerY-clockRadius+30,"11");

        double sLength=clockRadius*0.8;
        double scondX=centerX+sLength*Math.sin(second*(2*Math.PI/60));
        double scondY=centerY-sLength*Math.cos(second*(2*Math.PI/60));
        Line sline=new Line(centerX,centerY,scondX,scondY);
        sline.setStroke(Color.RED);

        double mLength=clockRadius*0.65;
        double minuteX=centerX+mLength*Math.sin(minute*(2*Math.PI/60));
        double minuteY=centerY-mLength*Math.cos(minute*(2*Math.PI)/60);
        Line mline=new Line(centerX,centerY,minuteX,minuteY);
        mline.setStroke(Color.BLUE);

        double hLength=clockRadius*0.5;
        double hourX=centerX+hLength*Math.sin((hour%12+minute/60.0)*(2*Math.PI/12));
        double hourY=centerY-hLength*Math.cos((hour%12+minute/60)*(2*Math.PI/12));
        Line hline=new Line(centerX,centerY,hourX,hourY);
        hline.setStroke(Color.GREEN);

        getChildren().clear();
        getChildren().addAll(circle,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,sline,mline,hline);

    }
}