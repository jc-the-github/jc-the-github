import java.util.Scanner;

public class MineSweeper {
    private int length;
    private int width;
    private static int[][] points;

    public static boolean flagFound = false;

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPoints(int[][] points) {
        this.points = points;
    }

    public static void main(String args[]) {
//        RealGetInput realGetInput = new RealGetInput().invoke();
//        Scanner in = realGetInput.getIn();
//        int mines = realGetInput.getMines();
//        int[][] points = realGetInput.getPoints();
//        fillMines(mines);
//        int rows = points.length;
//        int columns = points[0].length;
//        int count = 0;
//        findNum(points, rows, columns, count);
//        printField(points);
//        getInput(points, in, width, length);
    }

    public static void fillMines(int[][] points,int mines) {
        int index_1;
        int index_2;
        int rows = points.length;
        int columns = points[0].length;
        for (int i = 0; i < mines; i++) {
            index_1 = (int) (Math.random() * rows);
            index_2 = (int) (Math.random() * columns);
            if (points[index_1][index_2] == 9) {
                while (true) {
                    index_1 = (int) (Math.random() * rows);
                    index_2 = (int) (Math.random() * columns);
                    if (points[index_1][index_2] != 9) {
                        break;
                    }
                }
            }
            points[index_1][index_2] = 9;
        }
    }

    public static void findNum(int[][] points, int rows, int columns, int count) {
        count = 0;
        for (int x = 0; x < rows; x++) {                  //INSIDE
            for (int y = 0; y < columns; y++) {
                if (points[x][y] != 9) {
                    if (x > -1 && x < rows && y - 1 > -1 && y < columns && points[x][y - 1] == 9) {  //left
                        count++;
                    }
                    if (x > -1 && x + 1 < rows && y > -1 && y < columns && points[x + 1][y] == 9) {  //under
                        count++;
                    }
                    if (x > -1 && x < rows && y > -1 && y + 1 < columns && points[x][y + 1] == 9) {  //right
                        count++;
                    }
                    if (x - 1 > -1 && x < rows && y > -1 && y < columns && points[x - 1][y] == 9) {  //above
                        count++;
                    }
                    if (x - 1 > -1 && x < rows && y - 1 > -1 && y < columns && points[x - 1][y - 1] == 9) {    //top left
                        count++;
                    }
                    if (x > -1 && x + 1 < rows && y - 1 > -1 && y < columns && points[x + 1][y - 1] == 9) {    //bottom left
                        count++;
                    }
                    if (x > -1 && x + 1 < rows && y > -1 && y + 1 < columns && points[x + 1][y + 1] == 9) {    //bottom right
                        count++;
                    }
                    if (x - 1 > -1 && x < rows && y > -1 && y + 1 < columns && points[x - 1][y + 1] == 9) {     //top right
                        count++;
                    }
                    points[x][y] = count;
                    count = 0;
                }
            }
        }

    }


    public static void traverse(int[][] points, int x, int y, int min_x, int count) {
        int rows = points.length;
        int columns = points[0].length;
        if (x > -1 && x < rows && y - 1 > -1 && y < columns && points[x][y - 1] == 10) {  //correct                     //left
            y = y - 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x - 1 > -1 && x < rows && y - 1 > -1 && y < columns && points[x - 1][y - 1] == 10) {                       //top left
            x = x - 1;
            y = y - 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x - 1 > -1 && x < rows && y > -1 && y < columns && points[x - 1][y] == 10) {                      //above
            x = x - 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x - 1 > -1 && x < rows && y > -1 && y + 1 < columns && points[x - 1][y + 1] == 10) {                      //top right
            x = x - 1;
            y = y + 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x > -1 && x < rows && y > -1 && y + 1 < columns && points[x][y + 1] == 10) {                       //right
            y = y + 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x > -1 && x + 1 < rows && y > -1 && y + 1 < columns && points[x + 1][y + 1] == 10) {                       //bottom right
            x = x + 1;
            y = y + 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x > -1 && x + 1 < rows && y > -1 && y < columns && points[x + 1][y] == 10) {                      //under
            x = x + 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        if (x > -1 && x + 1 < rows && y - 1 > -1 && y < columns && points[x + 1][y - 1] == 10) {                      //bottom left
            x = x + 1;
            y = y - 1;
            markAllAround(points, x, y);
            traverse(points, x, y, min_x, count);
        }
        count++;
        if (count > 100) {
            System.out.println("Stuck!!?");

        } else {
            stuck(points, min_x, count);                                               //STUCK HEREEEEEEEEEEEEEEEEEEEEEEEE
        }
    }


    public static void markAllAround(int[][] points, int x, int y) {
        int rows = points.length;
        int columns = points[0].length;
        if (x > -1 && x < rows && y - 1 > -1 && y < columns && points[x][y - 1] < 9) {                       //left
            points[x][y - 1] = points[x][y - 1] + 10;
        }
        if (x - 1 > -1 && x < rows && y - 1 > -1 && y < columns && points[x - 1][y - 1] < 9) {                       //top left
            points[x - 1][y - 1] = points[x - 1][y - 1] + 10;
        }
        if (x - 1 > -1 && x < rows && y > -1 && y < columns && points[x - 1][y] < 9) {                      //above
            points[x - 1][y] = points[x - 1][y] + 10;
        }
        if (x - 1 > -1 && x < rows && y > -1 && y + 1 < columns && points[x - 1][y + 1] < 9) {                      //top right
            points[x - 1][y + 1] = points[x - 1][y + 1] + 10;
        }
        if (x > -1 && x < rows && y > -1 && y + 1 < columns && points[x][y + 1] < 9) {                       //right
            points[x][y + 1] = points[x][y + 1] + 10;
        }
        if (x > -1 && x + 1 < rows && y > -1 && y + 1 < columns && points[x + 1][y + 1] < 9) {                       //bottom right
            points[x + 1][y + 1] = points[x + 1][y + 1] + 10;
        }
        if (x > -1 && x + 1 < rows && y > -1 && y < columns && points[x + 1][y] < 9) {                      //under
            points[x + 1][y] = points[x + 1][y] + 10;
        }
        if (x > -1 && x + 1 < rows && y - 1 > -1 && y < columns && points[x + 1][y - 1] < 9) {                      //bottom left
            points[x + 1][y - 1] = points[x + 1][y - 1] + 10;
        }
        points[x][y] += 20;
    }

    public static void stuck(int[][] points, int min_x, int count) {
        int y;
        int rows = points.length;
        int columns = points[0].length;
        for (; min_x < rows; min_x++) {
            for (int min_y = 0; min_y < columns; min_y++) {
                if (points[min_x][min_y] == 10) {
                    int x_2 = min_x;
                    y = min_y;
                    markAllAround(points, x_2, y); //culprit
                    traverse(points, x_2, y, min_x, count);
                    ////STUCK HEREEEEEEEEEEEEEEEEEEEEEEEE
                    break;
                }
            }
        }
    }

    public static void lookFlag(int[][] points, int x, int y) {         //look for flag
        int rows = points.length;
        int columns = points[0].length;
        int count = points[x][y] % 10;
        int countFlags = 0;
        if (x > -1 && x < rows && y - 1 > -1 && y < columns) {                       //left
//            if(points[x][y - 1] > 4800){
//                points[x][y - 1] = points[x][y - 1] - 4770;
//            }
            if (points[x][y - 1] > 30) {
                countFlags++;
                System.out.println("f0 " + points[x][y - 1]);
            }
        }
        if (x - 1 > -1 && x < rows && y - 1 > -1 && y < columns) {                       //top left
//            if(points[x - 1][y-1] > 4800){
//                points[x - 1][y-1] = points[x - 1][y-1] - 4770;
//            }
            if (points[x - 1][y - 1] > 30) {
                countFlags++;
                System.out.println("f1 " + points[x - 1][y - 1]);

            }
        }
        if (x - 1 > -1 && x < rows && y > -1 && y < columns) {                      //above
//            if(points[x - 1][y] > 4800){
//                points[x - 1][y] = points[x - 1][y] - 4770;
//            }
            if (points[x - 1][y] > 30) {
                countFlags++;
                System.out.println("f2 " + points[x - 1][y]);
            }
        }
        if (x - 1 > -1 && x < rows && y > -1 && y + 1 < columns) {                      //top right
//            if(points[x - 1][y + 1] > 4800){
//                points[x - 1][y + 1] = points[x - 1][y + 1] - 4770;
//            }
            if (points[x - 1][y + 1] > 30) {
                countFlags++;
                System.out.println("f3 " + points[x - 1][y + 1]);

            }
        }
        if (x > -1 && x < rows && y > -1 && y + 1 < columns) {                       //right
//            if(points[x][y + 1] > 4800){
//                points[x][y + 1] = points[x][y + 1] - 4770;
//            }
            if (points[x][y + 1] > 30) {
                countFlags++;
                System.out.println("f4 " + points[x][y + 1]);

            }
        }
        if (x > -1 && x + 1 < rows && y > -1 && y + 1 < columns) {                       //bottom right
//            if(points[x + 1][y + 1] > 4800){
//                points[x + 1][y + 1] = points[x + 1][y + 1] - 4770;
//            }
            if (points[x + 1][y + 1] > 30) {
                countFlags++;
                System.out.println("f5 " + points[x + 1][y + 1]);

            }
        }
        if (x > -1 && x + 1 < rows && y > -1 && y < columns) {                      //under
//            if(points[x + 1][y] > 4800){
//                points[x + 1][y] = points[x + 1][y] - 4770;
//            }
            if (points[x + 1][y] > 30) {
                countFlags++;
                System.out.println("f6 " + points[x + 1][y]);

            }
        }
        if (x > -1 && x + 1 < rows && y - 1 > -1 && y < columns) {                      //bottom left
//            if(points[x + 1][y - 1] > 4800){
//                points[x + 1][y - 1] = points[x + 1][y - 1] - 4770;
//            }
            if (points[x + 1][y - 1] > 30) {
                countFlags++;
                System.out.println("f7 " + points[x + 1][y - 1]);

            }
        }
        System.out.println("flags: " + countFlags);
        System.out.println("num: " + count);
        if (count == countFlags) {
            flagFound = true;
        }

    }


    public static class RealGetInput {
        private Scanner in;
        private int mines;
        private int length;
        private int width;
        private int[][] points;

        public Scanner getIn() {
            return in;
        }

        public int getMines() {
            return mines;
        }

        public int getCols() {
            return length;
        }

        public int getRows() {
            return width;
        }

        public int[][] getPoints() {
            return points;
        }

        public RealGetInput invoke() {
            in = new Scanner(System.in);
            System.out.println("Enter Number : (1. Easy) (2. Medium) (3.Hard) (4. Custom)");
            int choice_1 = in.nextInt();
            mines = 0;
            length = 0;
            width = 0;
            if (choice_1 == 1) {                 //width is up and down
                length = 10;
                width = 8;
                mines = 10;                                // length is left and right
            } else if (choice_1 == 2) {
                length = 18;
                width = 14;
                mines = 40;
            } else if (choice_1 == 3) {
                length = 24;
                width = 18;
                mines = 100;
            } else if (choice_1 == 4) {
                System.out.println("Enter width: ");
                width = in.nextInt();
                System.out.println("Enter length: ");
                length = in.nextInt();
                System.out.println("Enter mine #: ");
                mines = in.nextInt();
            } else {

            }

            return this;
        }
    }
//    public static void printField(int[][] points) {
//        int rows = points.length;
//        int columns = points[0].length;
//        String rowCoord = "    " + 0;
//        for (int i = 1; i < columns; i++) {
//            if (i > 9) {
//                rowCoord += " " + i;
//            } else {
//                rowCoord += "  " + i;
//            }
//        }
//        System.out.print(rowCoord);
//        System.out.println();
//        int coord = 0;
//        for (int x = 0; x < rows; x++) {
//            coord = rows - 1 - x;
//            System.out.printf("%2d ", coord);
//            for (int p = 0; p < columns; p++) {
//                if (points[x][p] == 9) {
//                    System.out.print("[*]");
//                } else if (points[x][p] % 10 == 0) {
//                    System.out.print("[ ]");
//                } else {
//                    System.out.print("[" + points[x][p] % 10 + "]");
//                }
//            }
//            System.out.printf(" %2d ", coord);
//            System.out.println();
//        }
//        System.out.print(rowCoord);
//        System.out.println();
//
//    }

//    public static void getInput(int[][] points, Scanner in, int width, int length) {
//        int point_1;
//        int point_2;
//        int rows = points.length;
//        int columns = points[0].length;
//        int count = 0;
//        int[][] points_2 = new int[width][length];
//        for (int i = 0; i < width; i++) {                   //fill second array
//            for (int j = 0; j < length; j++) {
//                points_2[i][j] = 0;
//            }
//        }
//        while (1 == 1) {
//            System.out.println("Enter Number : (1. Dig) (2. Tag) (3.Exit)");
//            int choice = in.nextInt();
//            if (choice == 1) {
//                System.out.println("Enter 1st and 2nd coordinates for dig: (Ex. \"4 1\")");
//                point_1 = in.nextInt();
//                point_2 = in.nextInt();
//                point_2 = (rows - 1) - point_2;
//                if (points[point_2][point_1] == 9) {               //hit mine
//                    System.out.println("Hit Mine!");
//                    printField(points);
//                    break;
//                } else if (points[point_2][point_1] == 0) {             //hits nothing
//                    System.out.println("No Mine!");
//                    int x = point_2;
//                    int y = point_1;
//                    markAllAround(points, x, y);
//                    int min_x = 0;
//                    traverse(points, x, y, min_x,count);
//                    printHiddenField(points);
//                } else {
//                    points[point_2][point_1] += 10;
//                    System.out.println("Number!: ");
//                    printHiddenField(points);
//                }
//            } else if (choice == 2) {
//                System.out.println("Enter 1st and 2nd coordinates for tag: (Ex. \"4 1\")");
//                point_1 = in.nextInt();
//                point_2 = in.nextInt();
//                point_2 = (rows - 1) - point_2;
//                if (points_2[point_2][point_1] == 1) {
//                    points_2[point_2][point_1] = 0;
//                } else {
//                    points_2[point_2][point_1] = 1;
//                    System.out.println("tagged" + points_2[point_2][point_1]);
//
//                }
//                printHiddenField(points);
//            } else if (choice == 3) {
//                System.out.println("BYE THEN ;(");
//                System.exit(0);
//            }
//
//        }
//    }

//    public static void printHiddenField(int[][] points) {
//        int rows = points.length;
//        int columns = points[0].length;
//        String rowCoord = "    " + 0;
//        for (int i = 1; i < columns; i++) {
//            if (i > 9) {
//                rowCoord += " " + i;
//            } else {
//                rowCoord += "  " + i;
//            }
//        }
//        int count = 0;
//        System.out.print(rowCoord);
//        System.out.println();
//        int coord = 0;
//        for (int x = 0; x < rows; x++) {
//            coord = rows - 1 - x;
//            System.out.printf("%2d ", coord);
//            for (int p = 0; p < columns; p++) {
//                if (points[x][p] > 9) {
//                    if (points[x][p] % 10 == 0) {
//                        System.out.print("[ ]");
//                    } else {
//                        System.out.print("[" + points[x][p] % 10 + "]");
//                    }
//                } else if(points[x][p] == 9){
//                    System.out.print("[-]");
//                }
//                else{
//                    System.out.print("[-]");
//                    count++;
//                }
//            }
//            System.out.printf(" %2d ", coord);
//            System.out.println();
//        }
//        System.out.print(rowCoord);
//        System.out.println();
//        if(count == 0){
//            System.out.println("WINNNNN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            printField(points);
//            System.exit(0);
//        }
//    }
}