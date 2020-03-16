import java.awt.*;
import java.awt.event.ComponentEvent;
import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Display extends JPanel {
    private static JLabel[][] myLabels;
    private static JLabel[][] myLabels2;

    private static int[][] points;
    public int flagT;
    private int flagCorrect;
    private int mines;
    private int cols;
    private int rows;


    public Display() {

    }

    public Display(int cellWidth) {

        MyMouseListener myListener = new MyMouseListener(this);
        ComponentEventDemo componentEvent = new ComponentEventDemo(this);
        Display2 display2 = new Display2();
        display2.setBackground(Color.BLUE);
        cols = display2.getCols();
        rows = display2.getRows();
        points = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                points[i][j] = 0;
            }
        }
        System.out.println("rows " + rows + " pts2 " + points.length);
        System.out.println("col " + cols + " pts2 " + points.length);

        mines = display2.getMines();
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setLength(cols);
        mineSweeper.setWidth(rows);
        MineSweeper.fillMines(points, mines);
        int count = 0;
        MineSweeper.findNum(points, rows, cols, count);
        Dimension labelPrefSize = new Dimension(cellWidth, cellWidth);
        setLayout(new GridLayout(rows, cols));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        myLabels = new JLabel[rows][cols];
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                JLabel myLabel = new JLabel();
                myLabel = new JLabel();
                myLabel.setOpaque(true);
                myLabel.setBackground(Color.BLUE);
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                myLabel.setBorder(border);
                myLabel.setText(" ");
                add(myLabel);
                myLabels[row][col] = myLabel;
            }
        }
    }

    public static int[][] getPoints() {
        return points;
    }

    public static void displayMoved() {
        System.out.println("asdfasd");
        for (int rows = 0; rows < myLabels.length; rows++) {
            for (int cols = 0; cols < myLabels[rows].length; cols++) {
                if (myLabels[rows][cols].getBackground().equals(Color.CYAN) || myLabels[rows][cols].getText().equals(":(")) {
                    myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                }
            }
        }

    }

    public static JLabel getjLabel(JLabel jLabel) {
        JLabel label;
        jLabel.setVerticalTextPosition(JLabel.CENTER);
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        label = jLabel;
        Font labelFont = label.getFont();
        String labelText = label.getText();
        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = label.getWidth();
        double widthRatio = (double) componentWidth / (double) stringWidth;
        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = label.getHeight();
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
        return label;
    }

    public void getCols() {
        this.cols = cols;
    }

    public void labelPressedActual(int x, int y) {

        if (points[x][y] == 0 || points[x][y] % 10 == 0) {
            System.out.println("zeros?");
            int count = 0;
            MineSweeper.markAllAround(points, x, y);
            System.out.println(x);
            int min_x = 0;
            MineSweeper.traverse(points, x, y, min_x, count);
            for (int rows = 0; rows < myLabels.length; rows++) {
                for (int cols = 0; cols < myLabels[rows].length; cols++) {
                    if (points[rows][cols] > 9 && points[rows][cols] < 31) {
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
        } else if (points[x][y] > 0 && points[x][y] < 9 || points[x][y] % 10 > 0 && points[x][y] % 10 < 0) {

            if (points[x][y] > 9) {
                myLabels[x][y].setBackground(Color.CYAN);
                myLabels[x][y].setText("" + points[x][y] % 10);
                myLabels[x][y] = getjLabel(myLabels[x][y]);
            } else {
                myLabels[x][y].setBackground(Color.CYAN);
                myLabels[x][y].setText("" + points[x][y]);
                myLabels[x][y] = getjLabel(myLabels[x][y]);

            }
            myLabels[x][y] = getjLabel(myLabels[x][y]);

        } else if (points[x][y] == 9 || points[x][y] % 10 == 9) {           //this works
            System.out.println("Hit Mine! pftt");
            for (int rows = 0; rows < myLabels.length; rows++) {
                for (int cols = 0; cols < myLabels[rows].length; cols++) {
                    if (points[rows][cols] > 9) {
                        if (points[rows][cols] % 10 == 9) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText("*" /*+ points[rows][cols] % 10*/);
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        } else if (points[rows][cols] % 10 == 0) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText(" ");
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        } else {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText("" + points[rows][cols] % 10);
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        }
                    } else {
                        if (points[rows][cols] == 9) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText("*" /*+ points[rows][cols] % 10*/);
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        } else if (points[rows][cols] == 0) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText(" ");
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        } else {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setForeground(Color.BLACK);
                            myLabels[rows][cols].setText("" + points[rows][cols]);
                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                        }
                    }
                }
            }
            //                    if (myLabels[rows][cols].getText().equals("[1-3]|[0-9])")) {
//                        System.out.println("flag not shown");
////                        myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
////                        myLabels[rows][cols].setBackground(Color.CYAN);
////                        myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
//                    }
            for (int f = 0; f < myLabels.length; f++) {

                for (int p = 0; p < myLabels[f].length; p++) {

                    System.out.print("[" + myLabels[f][p].getText() + "]");

                }
                System.out.println();
            }
        }

    }

    public void labelPressedActual(JLabel label) {
        int x = label.getY() / label.getHeight();
        int y = label.getX() / label.getWidth();
        System.out.println("tile clicked" + points[x][y]);

        if (points[x][y] < 10) {
            if (points[x][y] == 0 || points[x][y] % 10 == 0) {
                int count = 0;
                MineSweeper.markAllAround(points, x, y);
                System.out.println(x);
                int min_x = 0;
                MineSweeper.traverse(points, x, y, min_x, count);
                for (int rows = 0; rows < myLabels.length; rows++) {
                    for (int cols = 0; cols < myLabels[rows].length; cols++) {
                        if (points[rows][cols] > 9 && points[rows][cols] < 31) {
                            if (points[rows][cols] % 10 == 0) {
                                myLabels[rows][cols].setText(" ");
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            } else {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setText("" + points[rows][cols] % 10);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            }
                        }
                    }
                }
            } else if (points[x][y] > 0 && points[x][y] < 9 || points[x][y] % 10 > 0 && points[x][y] % 10 < 0) {
                if (points[x][y] > 9) {
                    myLabels[x][y].setBackground(Color.CYAN);
                    myLabels[x][y].setText("" + points[x][y] % 10);
                    myLabels[x][y] = getjLabel(myLabels[x][y]);

                } else {
                    myLabels[x][y].setBackground(Color.CYAN);
                    myLabels[x][y].setText("" + points[x][y]);
                    myLabels[x][y] = getjLabel(myLabels[x][y]);
                }

                myLabels[x][y] = getjLabel(myLabels[x][y]);
            } else if (points[x][y] == 9 || points[x][y] % 10 == 9) {
                System.out.println("Hit Mine! pftt2222");
                for (int rows = 0; rows < myLabels.length; rows++) {
                    for (int cols = 0; cols < myLabels[rows].length; cols++) {
                        if (points[rows][cols] > 9) {
                            if (points[rows][cols] % 10 == 9) {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText("*" /*+ points[rows][cols] % 10*/);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            } else if (points[rows][cols] % 10 == 0) {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText(" ");
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            } else {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText("" + points[rows][cols] % 10);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            }
                        } else {
                            if (points[rows][cols] == 9) {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText("*" /*+ points[rows][cols] % 10*/);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            } else if (points[rows][cols] == 0) {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText(" ");
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            } else {
                                myLabels[rows][cols].setBackground(Color.CYAN);
                                myLabels[rows][cols].setForeground(Color.BLACK);
                                myLabels[rows][cols].setText("" + points[rows][cols]);
                                myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                            }
                        }
                    }
                }
            }
        }
    }

    public void flagMark(JLabel label) {
        int x = label.getY() / label.getHeight();
        int y = label.getX() / label.getWidth();
        if (points[x][y] > 30 && myLabels[x][y].getBackground() != Color.CYAN) {
            if (points[x][y] == 39) {
                flagCorrect--;
            }
            points[x][y] -= 30;
            myLabels[x][y].setText(" ");
            System.out.println("flag unmarked " + points[x][y]);
            flagT--;

            myLabels[x][y] = getjLabel(myLabels[x][y]);
        } else if (points[x][y] < 10 && myLabels[x][y].getBackground() != Color.CYAN) {
            points[x][y] += 30;
            System.out.println("flag marked " + points[x][y]);
            flagT++;
            if (points[x][y] == 39) {
                flagCorrect += 1;
            }
            myLabels[x][y].setText(":(");
            myLabels[x][y].setForeground(Color.CYAN);
            myLabels[x][y] = getjLabel(myLabels[x][y]);
        }
        System.out.println("flag correc " + flagCorrect + " flagt " + flagT + "mines " + mines);
        if (flagCorrect == flagT && flagT == mines) {
            for (int rows = 0; rows < myLabels.length; rows++) {
                for (int cols = 0; cols < myLabels[rows].length; cols++) {
                    myLabels[rows][cols].setBackground(Color.CYAN);
                    myLabels[rows][cols].setForeground(Color.BLUE);
                    myLabels[rows][cols].setText(":)");
                    myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
                }
            }
        }
    }

    public void flagSearch(JLabel label) { //middle click
        System.out.println("middle clicked");
        int x = label.getY() / label.getHeight();
        int y = label.getX() / label.getWidth();
        MineSweeper.lookFlag(points, x, y);
        System.out.println("flag: " + MineSweeper.flagFound);
        if (MineSweeper.flagFound) {
            System.out.println("Flag found method");
            MineSweeper.flagFound = false;
            int rows = points.length;
            int columns = points[0].length;
            int count = 0;
            if (x > -1 && x < rows && y - 1 > -1 && y < columns && points[x][y - 1] < 10) {                       //left
                if (points[x][y - 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                System.out.println(count);
                count++;
                labelPressedActual(x, y - 1);
            }
            if (x - 1 > -1 && x < rows && y - 1 > -1 && y < columns && points[x - 1][y - 1] < 10) {                       //top left
                if (points[x - 1][y - 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x - 1, y - 1);
                System.out.println(count);
                count++;
            }
            if (x - 1 > -1 && x < rows && y > -1 && y < columns && points[x - 1][y] < 10) {                      //above
                if (points[x - 1][y] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x - 1, y);
                System.out.println(count);
                count++;
            }
            if (x - 1 > -1 && x < rows && y > -1 && y + 1 < columns && points[x - 1][y + 1] < 10) {                      //top right
                if (points[x - 1][y + 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x - 1, y + 1);
                System.out.println(count);
                count++;
            }
            if (x > -1 && x < rows && y > -1 && y + 1 < columns && points[x][y + 1] < 10) {                       //right
                if (points[x][y + 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x, y + 1);
                System.out.println(count);
                count++;
            }

            if (x > -1 && x + 1 < rows && y > -1 && y + 1 < columns && points[x + 1][y + 1] < 10) {                       //bottom right
                if (points[x + 1][y + 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x + 1, y + 1);
                System.out.println(count);
                count++;
            }
            if (x > -1 && x + 1 < rows && y > -1 && y < columns && points[x + 1][y] < 10) {                      //under
                if (points[x + 1][y] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x + 1, y);
                System.out.println(count);
                count++;
            }
            if (x > -1 && x + 1 < rows && y - 1 > -1 && y < columns && points[x + 1][y - 1] < 10) {                      //bottom left
                if (points[x + 1][y - 1] == 9) {
                    for (rows = 0; rows < myLabels.length; rows++) {
                        for (int cols = 0; cols < myLabels[rows].length; cols++) {
                            myLabels[rows][cols].setBackground(Color.CYAN);
                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
                            label = getjLabel(myLabels[x][y]);
                        }
                    }
                }
                labelPressedActual(x + 1, y - 1);
                System.out.println(count);
                count++;
            }


        }
//        if (points[x][y] > 30) {
//            points[x][y] = points[x][y] % 10;
//        }
    }

//    public void labelPressed(JLabel label) {
//        int x = label.getY() / label.getHeight();
//        int y = label.getX() / label.getWidth();
//        if (points[x][y] > 0 && points[x][y] < 9) {         //if number L clicked
//            myLabels[x][y].setBackground(Color.CYAN);
//            myLabels[x][y].setText(String.valueOf(points[x][y]));
//            myLabels[x][y] = getjLabel(myLabels[x][y]);
//        } else if (points[x][y] == 0) {                     //if open field L clicked
//            int count = 0;
//            MineSweeper.markAllAround(points, x, y);
//            System.out.println(x);
//            int min_x = 0;
//            MineSweeper.traverse(points, x, y, min_x, count);
//            for (int rows = 0; rows < myLabels.length; rows++) {
//                for (int cols = 0; cols < myLabels[rows].length; cols++) {
//                    if (points[rows][cols] > 9) {
//                        if (points[rows][cols] % 10 == 0) {
//                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
//                            myLabels[rows][cols].setBackground(Color.CYAN);
//                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
//
//                        } else {
//                            myLabels[rows][cols].setBackground(Color.CYAN);
//                            myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
//                            myLabels[rows][cols] = getjLabel(myLabels[rows][cols]);
//
//                        }
//                    }
//                }
//            }
//        } else if (points[x][y] == 9) {                                                 //if mine L clicked
//            System.out.println("Hit Mine!" + String.valueOf(points[x][y]));
//            for (int rows = 0; rows < myLabels.length; rows++) {
//                for (int cols = 0; cols < myLabels[rows].length; cols++) {
//                    myLabels[rows][cols].setBackground(Color.CYAN);
//                    myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
//                    myLabels[x][y] = getjLabel(myLabels[rows][cols]);
//                }
//            }
//        } else {
//            for (int rows = 0; rows < myLabels.length; rows++) {
//                for (int cols = 0; cols < myLabels[rows].length; cols++) {
//                    if (points[rows][cols] > 9) {
//                        if (points[rows][cols] % 10 == 0) {
//                            myLabels[rows][cols].setText(" ");
//                        } else {
//                            if (points[rows][cols] % 10 == 9) {
//
//                            } else {
//                                myLabels[rows][cols].setBackground(Color.CYAN);
//                                myLabels[rows][cols].setText(String.valueOf(points[rows][cols]));
//                                label = getjLabel(myLabels[rows][cols]);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

}