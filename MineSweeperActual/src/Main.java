import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static JFrame frame1;
//    private static JFrame frame1;
    private static Display2 mainPanel2;
    public static void createAndShowGui() {

        Display2 mainPanel2 = new Display2();
        frame1 = new JFrame("Blued-Away");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.getContentPane().add(mainPanel2);
        frame1.pack();
        frame1.setLocationByPlatform(true);
        frame1.setVisible(true);
        ComponentEventDemo componentEvent = new ComponentEventDemo(frame1);

    }

    public static void createAndShowGui2() {


        int cellWidth = 20;
        Display mainPanel = new Display(cellWidth);
        frame1.getContentPane().removeAll();
        frame1.getContentPane().add(mainPanel);
        frame1.pack();
        frame1.setLocationByPlatform(true);
        frame1.setVisible(true);
//        frame1.setVisible(false);
        ComponentEventDemo componentEvent = new ComponentEventDemo(frame1);

    }

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }


}


