import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseListener2 extends MouseAdapter {
    private Display2 display;


    public MyMouseListener2(Display2 display) {
        this.display = display;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {   //Left click
            display.labelPressed((JLabel) e.getSource());

        } else if (e.getButton() == MouseEvent.BUTTON3) {     // Right Click
//            display.flagMark((JLabel) e.getSource());

        } else if (e.getButton() == MouseEvent.BUTTON2) {       //Middle Click
//            display.flagSearch((JLabel) e.getSource());

        }
    }
}