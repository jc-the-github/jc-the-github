import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

class ComponentEventDemo implements ComponentListener {
    private JFrame frame;
    private Display display;
    private Display2 display2;

    public ComponentEventDemo(JFrame frame){
        this.frame = frame;
        frame.addComponentListener(this);
    }
    public ComponentEventDemo(Display display){
        this.display = display;
        
    }
    public ComponentEventDemo(Display2 display){
        this.display2 = display;
    }



    public void componentHidden(ComponentEvent e) {

        Display.displayMoved();
        System.out.println("Component h");
    }

    public void componentMoved(ComponentEvent e) {
        System.out.println("Component m");

    }

    public void componentResized(ComponentEvent e) {
        System.out.println("Component r");
        Display.displayMoved();

    }

    public void componentShown(ComponentEvent e) {
        System.out.println("Component s");


    }
}