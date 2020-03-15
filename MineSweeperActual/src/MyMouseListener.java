import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class MyMouseListener extends MouseAdapter {
    private Display display;


    public MyMouseListener(Display display) {
        this.display = display;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {   //Left click
            display.labelPressedActual((JLabel) e.getSource());

        } else if (e.getButton() == MouseEvent.BUTTON3) {     // Right Click
            display.flagMark((JLabel) e.getSource());

        } else if (e.getButton() == MouseEvent.BUTTON2) {       //Middle Click
            display.flagSearch((JLabel) e.getSource());

        }
    }
}