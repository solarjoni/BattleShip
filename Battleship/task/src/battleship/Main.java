/*
    In this stage, you should arrange your ships on the game field.
    Before you start, let"s discuss the conventions of the game:

    On a 10x10 field, the first row should contain numbers from 1 to 10 indicating the column,
    and the first column should contain letters from A to J indicating the row.
    The symbol ~ denotes the fog of war: the unknown area on the
    opponent"s field and the yet untouched area on your field.
    The symbol O denotes a cell with your ship, X denotes that
    the ship was hit, and M signifies a miss.
    You have 5 ships: Aircraft Carrier is 5 cells, Battleship is 4 cells,
    Submarine is 3 cells, Cruiser is also 3 cells, and Destroyer is 2 cells.
    Start placing your ships with the largest one.
    To place a ship, enter two coordinates: the beginning and the end of the ship.
    If an error occurs in the input coordinates, your program should report it.
    The message should contain the word Error.
 */
package battleship;

import java.io.*;

import java.util.Scanner;

public class Main {
    static String fogOfWar = "~";
    static String cell = "O";
    static String hit = "X";
    static String miss = "M";
    static int hitCount = 0;
    static String newLine = "\n";
    static private int shipLength;
    static private int firstRowNumber;
    static private int firstColNumber;
    static private int secondRowNumber;
    static private int secondColNumber;
    static private int minRowNumber;
    static private int minColNumber;
    static private int maxRowNumber;
    static private int maxColNumber;

    public static int getHitCount() {
        return hitCount;
    }

    public static void setHitCount(int hitCount) {
        Main.hitCount = hitCount;
    }

    public static int getMaxRowNumber() {
        return maxRowNumber;
    }

    public static int getMaxColNumber() {
        return maxColNumber;
    }

    public static void setMaxRowNumber(int maxRowNumber) {
        Main.maxRowNumber = maxRowNumber;
    }

    public static void setMaxColNumber(int maxColNumber) {
        Main.maxColNumber = maxColNumber;
    }

    public static void setMinRowNumber(int minRowNumber) {
        Main.minRowNumber = minRowNumber;
    }

    public static void setMinColNumber(int minColNumber) {
        Main.minColNumber = minColNumber;
    }

    public static int getMinRowNumber() {
        return minRowNumber;
    }

    public static int getMinColNumber() {
        return minColNumber;
    }

    public static int getFirstRowNumber() {
        return firstRowNumber;
    }

    public static int getFirstColNumber() {
        return firstColNumber;
    }

    public static int getSecondRowNumber() {
        return secondRowNumber;
    }

    public static int getSecondColNumber() {
        return secondColNumber;
    }

    public static void setFirstRowNumber(int firstRowNumber) {
        Main.firstRowNumber = firstRowNumber;
    }

    public static void setFirstColNumber(int firstColNumber) {
        Main.firstColNumber = firstColNumber;
    }

    public static void setSecondRowNumber(int secondRowNumber) {
        Main.secondRowNumber = secondRowNumber;
    }

    public static void setSecondColNumber(int secondColNumber) {
        Main.secondColNumber = secondColNumber;
    }

    public static int getShipLength() {
        return shipLength;
    }

    public static void setShipLength(int shipLength) {
        Main.shipLength = shipLength;
    }

    public static void main(String[] args) throws IOException {
        // Write your code here
        BattleField myField = new BattleField("myfield");
        BattleField fogField = new BattleField("fogfield");
        printBoard(myField, "myfield");
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells): ");
        placeShips(myField, "Aircraft Carrier");
        System.out.println("\nEnter the coordinates of the Battleship (4 cells): ");
        placeShips(myField, "Battleship");
        System.out.println("\nEnter the coordinates of the Submarine (3 cells): ");
        placeShips(myField, "Submarine");
        System.out.println("\nEnter the coordinates of the Cruiser (3 cells): ");
        placeShips(myField, "Cruiser");
        System.out.println("\nEnter the coordinates of the Destroyer (2 cells): ");
        placeShips(myField, "Destroyer");
        System.out.println("\nThe game starts!\n");
        printBoard(fogField, "fogfield");
        System.out.println("\nTake a shot!\n");
        shootAndAnalyze(myField, fogField);
    }

    /* Checks input and puts ships on board */
    public static void placeShips(BattleField myField, String ship) throws IOException {
        // CharArrayWriter myFieldCharWriter = new CharArrayWriter();
        // FileWriter myFieldFileWriter = new FileWriter("myfield", true);
        /* BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); */
        Scanner input = new Scanner(System.in); // For text use Scanner
        while (true) {
            try {
                String[] stringsArray;
                String line = input.nextLine();
                stringsArray = line.split("\\s+"); // Exception if no whitespaces

                char firstLetter = stringsArray[0].charAt(0);
                char secondLetter = stringsArray[1].charAt(0);
                setFirstRowNumber((int) firstLetter - 64);
                setSecondRowNumber((int) secondLetter - 64);
                setFirstColNumber(Integer.parseInt(stringsArray[0].substring(1)));
                setSecondColNumber(Integer.parseInt(stringsArray[1].substring(1)));

                switch (ship) {
                    case "Aircraft Carrier":
                        setShipLength(5);
                        break;
                    case "Battleship":
                        setShipLength(4);
                        break;
                    case "Submarine":
                        setShipLength(3);
                        break;
                    case "Cruiser":
                        setShipLength(3);
                        break;
                    case "Destroyer":
                        setShipLength(2);
                        break;
                }
                /* Checks if input is correct */
                if (stringsArray.length == 2
                        && stringsArray[0].length() <= 3 // i.e A1 or A10
                        && stringsArray[1].length() <= 3
                        &&
                        (getFirstRowNumber() == getSecondRowNumber()
                                || getFirstColNumber() == getSecondColNumber())
                        && (getSecondRowNumber() - getFirstRowNumber() == getShipLength() - 1
                        || getSecondColNumber() - getFirstColNumber() == getShipLength() - 1
                        || getFirstRowNumber() - getSecondRowNumber() == getShipLength() - 1
                        || getFirstColNumber() - getSecondColNumber() == getShipLength() - 1)
                        // Its possible for a second number be less than first!
                        && firstRowNumber >= 1 // is true anyway ?
                        && getFirstRowNumber() <= 10
                        && getFirstColNumber() >= 1
                        && getFirstColNumber() <= 10
                        && secondRowNumber >= 1 // is true anyway ?
                        && getSecondRowNumber() <= 10
                        && getSecondColNumber() >= 1
                        && getSecondColNumber() <= 10
                ) {
                    // Check if cells are available
                    if (isSpaceAvailable(myField)) {
                        // Need to break form while loop if everything ok: while(false)
                        break;
                    } else {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n");
                        // continue;
                    }
                } else {
                    System.out.println("\nError! Wrong length of the " + ship + "! " + "Try again:\n");
                }
            } catch (Exception e) {
                System.out.println("\nError! " + e.getMessage() + "\n");
            }
        }
        /* Insert manual numbers into array
            myField.battleField[3][4] = cell;
            myFieldCharWriter.write(myField.battleField[3][4]);
            myFieldCharWriter.writeTo(myFieldFileWriter);
        */

        for (int row = getFirstRowNumber(); row <= getSecondRowNumber(); row++) {
            for (int col = getFirstColNumber(); col <= getSecondColNumber(); col++) {
                myField.battleField[row][col] = cell;
                // myFieldCharWriter.write(myField.battleField[row][col]);
            }
        }
        // myFieldCharWriter.writeTo(myFieldFileWriter);
        // myFieldFileWriter.close();
        // myFieldCharWriter.close();
        System.out.println();
        printBoard(myField, "myfield");
    }

    /* Checking space for ships placement */
    public static boolean isSpaceAvailable(BattleField myField) {
/*      System.out.println(shipLength);
        System.out.print(getFirstRowNumber());
        System.out.print(getFirstColNumber() + " ");
        System.out.print(getSecondRowNumber());
        System.out.println(getSecondColNumber());
        System.out.print(myField.battleField[getFirstRowNumber()][getFirstColNumber()] + " ");
        System.out.println(myField.battleField[getSecondRowNumber()][getSecondColNumber()]);
*/
        /* Reverse numbers if condition is true (its checked if numbers are ok in the placeShips */
        if (getFirstRowNumber() > getSecondRowNumber()) {
            int temp = getSecondRowNumber();
            setSecondRowNumber(getFirstRowNumber());
            setFirstRowNumber(temp);
        }
        if (getFirstColNumber() > getSecondColNumber()) {
            int temp = getSecondColNumber();
            setSecondColNumber(getFirstColNumber());
            setFirstColNumber(temp);
        }
        if (getFirstColNumber() == 1) {
            setMinColNumber(1);
        } else {
            setMinColNumber(getFirstColNumber() - 1);
        }
        if (getFirstRowNumber() == 1) {
            setMinRowNumber(1);
        } else {
            setMinRowNumber(getFirstRowNumber() - 1);
        }
        if (getSecondRowNumber() == 10) {
            setMaxRowNumber(10);
        } else {
            setMaxRowNumber(getSecondRowNumber() + 1);
        }
        if (getSecondColNumber() == 10) {
            setMaxColNumber(10);
        } else {
            setMaxColNumber(getSecondColNumber() + 1);
        }

        for (int row = getMinRowNumber(); row <= getMaxRowNumber(); row++) {
            for (int col = getMinColNumber(); col <= getMaxColNumber(); col++) {
                // System.out.println(myField.battleField[row][col] + "***" + row + col);
                if (myField.battleField[row][col].equals(cell)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* Shoot method */
    public static void shootAndAnalyze(BattleField myField, BattleField fogField) throws IOException {
        // CharArrayWriter charWriter = new CharArrayWriter();
        // CharArrayWriter fogFieldCharWriter = new CharArrayWriter();
        //FileWriter myFieldFileWriter = new FileWriter("myfield", true);
        // FileWriter fogFieldFileWriter = new FileWriter("fogfield", true);
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in); // For text use Scanner
        while (getHitCount() <= 16) {
            while (true) {
                try {
                    String[] string = new String[2];
                    String line = input.nextLine();
                    string[0] = line.substring(0, 1);
                    string[1] = line.substring(1);

                    if (string[1].length() <= 2
                            && string[0].charAt(0) > 64
                            && string[0].charAt(0) < 75
                            && Integer.parseInt(string[1]) >= 1
                            && Integer.parseInt(string[1]) <= 10) {

                        char firstLetter = string[0].charAt(0);
                        setFirstRowNumber((int) firstLetter - 64);
                        setFirstColNumber(Integer.parseInt(string[1]));
                        break;
                    } else {
                        System.out.println("Error! You entered the wrong coordinates!. Try again: ");
                    }
                } catch (Exception e) {
                    System.out.println("Error! " + e.getMessage());
                }
            }

            /* Only count hit once
             */
            if (cell.equals(myField.battleField[getFirstRowNumber()][getFirstColNumber()])) {
                hitCount++;
                // System.out.println(hitCount);
            }

            if (cell.equals(myField.battleField[getFirstRowNumber()][getFirstColNumber()])
                    || hit.equals((myField.battleField[getFirstRowNumber()][getFirstColNumber()]))) {
                myField.battleField[getFirstRowNumber()][getFirstColNumber()] = hit;
                fogField.battleField[getFirstRowNumber()][getFirstColNumber()] = hit;


                // charWriter.write(myField.battleField[getFirstRowNumber()][getFirstColNumber()]);
                // charWriter.write(fogField.battleField[getFirstRowNumber()][getFirstColNumber()]);
                System.out.println();
                printBoard(fogField, "fogfield");

                if (getFirstColNumber() == 1) {
                    setMinColNumber(1);
                } else {
                    setMinColNumber(getFirstColNumber() - 1);
                }
                if (getFirstRowNumber() == 1) {
                    setMinRowNumber(1);
                } else {
                    setMinRowNumber(getFirstRowNumber() - 1);
                }
                if (getFirstRowNumber() == 10) {
                    setMaxRowNumber(10);
                } else {
                    setMaxRowNumber(getFirstRowNumber() + 1);
                }
                if (getFirstColNumber() == 10) {
                    setMaxColNumber(10);
                } else {
                    setMaxColNumber(getFirstColNumber() + 1);
                }

                /*
                    Check if there are cells aorund
                 */
                if (cell.equals(myField.battleField[getMaxRowNumber()][getFirstColNumber()])
                        || cell.equals(myField.battleField[getFirstRowNumber()][getMaxColNumber()])
                        || cell.equals(myField.battleField[getMinRowNumber()][getFirstColNumber()])
                        || cell.equals(myField.battleField[getFirstRowNumber()][getMinColNumber()])) {
                    System.out.println("\nYou hit a ship! Try again:\n");

                } else {
                    System.out.println("\nYou sank a ship! Specify a new target\n");
                }
                // printBoard(fogField, "fogfield");
                // printBoard(myField, "myfield");
            } else {
                myField.battleField[getFirstRowNumber()][getFirstColNumber()] = miss;
                fogField.battleField[getFirstRowNumber()][getFirstColNumber()] = miss;
                // charWriter.write(myField.battleField[getFirstRowNumber()][getFirstColNumber()]);
                // charWriter.write(fogField.battleField[getFirstRowNumber()][getFirstColNumber()]);
                System.out.println();
                // printBoard(fogField, "fogfield");
                System.out.println("\nYou missed. Try again:\n");
                // printBoard(myField, "myfield");
            }
            // charWriter.writeTo(myFieldFileWriter);
            // charWriter.writeTo(fogFieldFileWriter);
            // myFieldFileWriter.close();
            // fogFieldFileWriter.close();
            // charWriter.close();
        }
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    /* Prints board */
    public static void printBoard(BattleField field, String fileName) throws IOException {
        CharArrayWriter fieldCharWriter = new CharArrayWriter();
        FileWriter fieldFileWriter = new FileWriter(fileName); // Implement name ask
        // System.out.println(notationUpper);
        // myFieldCharWriter.write(notationUpper);
        // myFieldCharWriter.write(newLine);
        for (int rows = 0; rows < 11; rows++) {
            for (int cols = 0; cols < 11; cols++) {
                System.out.print(field.battleField[rows][cols]);
                fieldCharWriter.write(field.battleField[rows][cols]);
                if (cols == 10) {
                    System.out.println();
                    fieldCharWriter.write(newLine);
                } else {
                    System.out.print(" ");
                    fieldCharWriter.write(" ");
                }
            }
        }
        // Write to file and close Writer
        fieldCharWriter.writeTo(fieldFileWriter);
        fieldFileWriter.close();
        fieldCharWriter.close();
    }
}

class BattleField {
    String name;
    String[][] battleField;

    public BattleField(String name) {
        this.name = name;
        battleField = new String[][]{
                {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
                {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
                {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
        };
    }
}

class Ship {
}

class AircraftCarrier extends Ship {
    private final int deck = 5;
    private String[] coordinates;

    public AircraftCarrier(String a, String b) {

    }
}

class Battleship extends Ship {
    private final int deck = 4;
    private String coordinates;

}

class Submarine extends Ship {
    private final int deck = 3;
    private String coordinates;

}

class Cruiser extends Ship {
    private final int deck = 3;
    private String coordinates;

}

class Destroyer extends Ship {
    private final int deck = 2;
}
