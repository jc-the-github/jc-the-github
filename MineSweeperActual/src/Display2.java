import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@SuppressWarnings("serial")
public class Display2 extends JPanel {
    private static JLabel[][] myLabels;
    private static JLabel[][] myLabels2;

    private static int[][] points;
    private static int mines;
    private static int cols;
    private static int rows;

    public Display2() {
        MyMouseListener2 myListener = new MyMouseListener2(this);
        ComponentEventDemo componentEvent = new ComponentEventDemo(this);

        Dimension labelPrefSize = new Dimension(200, 200);
        setLayout(new GridLayout());
        Border border = BorderFactory.createLineBorder(Color.CYAN, 1);
        myLabels2 = new JLabel[3][1];
        for (int i = 0; i < 3; i++) {
            JLabel myLabel = new JLabel();
            myLabel = new JLabel();
            myLabel.setOpaque(true);
            myLabel.setBackground(Color.BLUE);
            myLabel.addMouseListener(myListener);
            myLabel.setPreferredSize(labelPrefSize);
            myLabel.setBorder(border);
            myLabel = getjLabel(myLabel);
            if (i == 0) {
                myLabel.setText("Easy");
                myLabel = getjLabel(myLabel);

            } else if (i == 1) {
                myLabel.setText("Medium");
                myLabel = getjLabel(myLabel);

            } else {
                myLabel.setText("Hard");
                myLabel = getjLabel(myLabel);

            }
            add(myLabel);
            myLabels2[i][0] = myLabel;
        }

    }

    public static void displayMoved() {
        System.out.println("asdfasd");
        for (int rows = 0; rows < myLabels.length; rows++) {
            for (int cols = 0; cols < myLabels[rows].length; cols++) {
                if (myLabels[rows][cols].getBackground().equals(Color.CYAN)) {
                    if (points[rows][cols] % 10 == 0) {
                        myLabels[rows][cols].setBackground(Color.CYAN);
                        myLabels[rows][cols].setText(" ");
                        myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                    } else {
                        myLabels[rows][cols].setBackground(Color.CYAN);
                        myLabels[rows][cols].setText("" + points[rows][cols] % 10);
                        myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                    }
                }
            }
        }
    }

    public static int getMines() {
        return mines;
    }

    public static int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
        System.out.println("col 2 " + cols);
    }

    public static int getRows() {
        System.out.println("ropws 2 " + rows);

        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        System.out.println("rows222 " + rows);
    }

    public static JLabel getjLabel(JLabel jLabel) {
        JLabel label;
        jLabel.setVerticalTextPosition(JLabel.CENTER);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        label = jLabel;
        Font labelFont = label.getFont();
        String labelText = label.getText();
        label.setForeground(Color.CYAN);
        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();
        double widthRatio = (double) componentWidth / (double) stringWidth;
        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
//        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        return label;
    }

    public void labelPressed(JLabel label) {
        if (label.getText().equals("Easy")) {
            cols = 10;
            rows = 8;
            mines = 10;
            setCols(cols);
            setRows(rows);
            System.out.println("eas");

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Main.createAndShowGui2();
                }
            });
        }
        if (label.getText().equals("Medium")) {
            cols = 18;
            rows = 14;
            mines = 40;
            System.out.println("med");

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Main.createAndShowGui2();
                }
            });
        }
        if (label.getText().equals("Hard")) {
            cols = 24;
            rows = 18;
            mines = 100;
            System.out.println("har");

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Main.createAndShowGui2();
                }
            });
        }
    }
}