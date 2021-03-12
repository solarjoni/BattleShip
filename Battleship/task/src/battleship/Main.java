/*
    In this stage, you should arrange your ships on the game field.
    Before you start, let's discuss the conventions of the game:

    On a 10x10 field, the first row should contain numbers from 1 to 10 indicating the column,
    and the first column should contain letters from A to J indicating the row.
    The symbol ~ denotes the fog of war: the unknown area on the
    opponent's field and the yet untouched area on your field.
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
    static char fogOfWar = '~';
    static char cell = 'o';
    static char hit = 'x';
    static char miss = 'M';
    static String newLine = "\n";
    static String notationUpper = "  1 2 3 4 5 6 7 8 9 10";
    static private int shipLength;
    static private int firstRowNumber;
    static private int firstColNumber;
    static private int secondRowNumber;
    static private int secondColNumber;
    static private int minRowNumber;
    static private int minColNumber;
    static private int maxRowNumber;
    static private int maxColNumber;

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
        BattleField newField = new BattleField("newone");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):");
        processAndFill(newField, "Aircraft Carrier");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Battleship (4 cells):");
        processAndFill(newField, "Battleship");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Submarine (3 cells):");
        processAndFill(newField, "Submarine");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Cruiser (3 cells):");
        processAndFill(newField, "Cruiser");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Destroyer (2 cells):");
        processAndFill(newField, "Destroyer");
        composeBoard(newField);

    }

    public static void processAndFill(BattleField newField, String ship) throws IOException {
        CharArrayWriter boardWriter = new CharArrayWriter();
        FileWriter bf = new FileWriter("battlefield", true);
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in); // For text use Scanner
        while (true) {
            try {
                String[] stringsArray;
                String line = input.nextLine();
                stringsArray = line.split("\\s+"); // Exception if no whitespaces

                char firstLetter = stringsArray[0].charAt(0);
                char secondLetter = stringsArray[1].charAt(0);
                setFirstRowNumber((int) firstLetter - 65);
                setSecondRowNumber((int) secondLetter - 65);
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
                // Checks if input is correct
                if (stringsArray.length == 2
                        && stringsArray[0].length() <= 3
                        && stringsArray[1].length() <= 3
                        &&
                        (getFirstRowNumber() == getSecondRowNumber()
                                || getFirstColNumber() == getSecondColNumber())
                        && (getSecondRowNumber() - getFirstRowNumber() == getShipLength() - 1
                        || getSecondColNumber() - getFirstColNumber() == getShipLength() - 1)
                        // Its possible for a second number be less than first!
                        && firstRowNumber >= 0 // is true anyway ?
                        && getFirstRowNumber() <= 9
                        && getFirstColNumber() >= 1
                        && getFirstColNumber() <= 10
                        && secondRowNumber >= 0 // is true anyway ?
                        && getSecondRowNumber() <= 9
                        && getSecondColNumber() >= 1
                        && getSecondColNumber() <= 10
                ) {
                    // Check if cells are available
                    if (isSpaceAvailable(newField, getShipLength())) {
                        // Need to break form while loop if everything ok: while(false)
                        break;
                    } else {
                        System.out.println("Error. Space not available");
                        // continue;
                    }
                } else {
                    System.out.println("Error! Numbers not Ok");
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        // Inserts manual numbers into array
        // newField.battleField[3][4] = cell;
        // boardWriter.write(newField.battleField[3][4]);
        // boardWriter.writeTo(bf);

        for (int row = getFirstRowNumber(); row <= getSecondRowNumber(); row++) {
            for (int col = getFirstColNumber(); col <= getSecondColNumber(); col++) {
                newField.battleField[row][col] = cell;
                boardWriter.write(newField.battleField[row][col]);
            }
        }
        boardWriter.writeTo(bf);
        bf.close();
        boardWriter.close();
    }

    public static boolean isSpaceAvailable(BattleField newField, int shipLength) {
/*        System.out.println(shipLength);
        System.out.print(getFirstRowNumber());
        System.out.print(getFirstColNumber() + " ");
        System.out.print(getSecondRowNumber());
        System.out.println(getSecondColNumber());
        System.out.print(newField.battleField[getFirstRowNumber()][getFirstColNumber()] + " ");
        System.out.println(newField.battleField[getSecondRowNumber()][getSecondColNumber()]);
*/

        if (getFirstColNumber() == 1) {
            setMinColNumber(1);
        } else {
            setMinColNumber(getFirstColNumber() - 1);
        }
        if (getFirstRowNumber() == 0) {
            setMinRowNumber(0);
        } else {
            setMinRowNumber(getFirstRowNumber() - 1);
        }
        if (getSecondRowNumber() == 9) {
            setMaxRowNumber(9);
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
                // System.out.println(newField.battleField[row][col] + "***" + row + col);
                if (newField.battleField[row][col] == cell) {
                    return false;
                }
            }
        }
        return true;

    }

    // Creates new board full of 'Fog of War'
    public static void composeBoard(BattleField newField) throws IOException {
        CharArrayWriter boardWriter = new CharArrayWriter();
        FileWriter bf = new FileWriter("battlefield"); // Implement name ask
        System.out.println(notationUpper);
        boardWriter.write(notationUpper);
        boardWriter.write(newLine);
        for (int rows = 0; rows < 10; rows++) {
            for (int cols = 0; cols < 11; cols++) {
                System.out.print(newField.battleField[rows][cols]);
                boardWriter.write(newField.battleField[rows][cols]);
                if (cols == 10) {
                    System.out.println();
                    boardWriter.write(newLine);
                } else {
                    System.out.print(' ');
                    boardWriter.write(' ');
                }
            }
        }
        // Write to file and close Writer
        boardWriter.writeTo(bf);
        bf.close();
        boardWriter.close();
    }
}


class BattleField {
    String name;
    char[][] battleField;

    public BattleField(String name) {
        this.name = name;
        battleField = new char[][]{
                {'A', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'B', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'C', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'D', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'E', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'F', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'G', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'H', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'I', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
                {'J', '~', '~', '~', '~', '~', '~', '~', '~', '~', '~'},
        };
    }
}