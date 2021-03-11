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
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char fogOfWar = '~';
    static char cell = 'o';
    static char hit = 'x';
    static char miss = 'M';
    static String newLine = "\n";
    static char firstLetter;
    static char secondLetter;
    static char[][] bfArray = new char[10][10];
    static char[] testArray = {'1', '2', '3', '4', '5'};
    static String notationUpper = "  1 2 3 4 5 6 7 8 9 10";

    public static void main(String[] args) throws IOException {
        // Write your code here
        BattleField newField = new BattleField("newone");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):");
        processPosition(newField, "Aircraft Carrier");
        composeBoard(newField);
        System.out.println("\nEnter the coordinates of the Battleship (4 cells):");
        processPosition(newField, "Battleship");
        composeBoard(newField);


    }

    public static void processPosition(BattleField newField, String ship) throws IOException {
        CharArrayWriter boardWriter = new CharArrayWriter();
        FileWriter bf = new FileWriter("battlefield", true);


        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String[] stringsArray;
                String line = input.nextLine();
                stringsArray = line.split("\\s+"); // Exception if no whitespaces

                char firstLetter = stringsArray[0].charAt(0);
                char secondLetter = stringsArray[1].charAt(0);
                int firstRowNumber = (int) firstLetter - 65;
                int secondRowNumber = (int) secondLetter - 65;
                int firstColNumber = Integer.parseInt(stringsArray[0].substring(1));
                int secondColNumber = Integer.parseInt(stringsArray[1].substring(1));

                System.out.print(firstRowNumber);
                System.out.println(firstColNumber);
                System.out.print(secondRowNumber);
                System.out.println(secondColNumber);
                System.out.println(newField.battleField[firstRowNumber][firstColNumber]);
                System.out.println(newField.battleField[secondRowNumber][secondColNumber]);


                if (stringsArray.length == 2
                        && stringsArray[0].length() <= 3
                        && stringsArray[1].length() <= 3
                        && '~' == newField.battleField[firstRowNumber][firstColNumber]
                        && firstRowNumber >= 0
                        && firstRowNumber <= 9
                        && firstColNumber >= 1
                        && firstColNumber <= 10
                        && secondRowNumber >= 0
                        && secondRowNumber <= 9
                        && secondColNumber >= 1
                        && secondColNumber <= 10

                ) {
                    switch (ship) {
                        case "Aircraft Carrier":

                            break;
                        case "Battleship":
                            break;
                        case "Submarine":
                            break;
                        case "Cruiser":
                            break;
                        case "Destroyer":
                            break;
                    }
                    break;
                } else {
                    System.out.println("Error");
                }
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }

        }

        // System.out.println(position);

        newField.battleField[3][4] = cell;
        boardWriter.write(newField.battleField[3][4]);
        boardWriter.writeTo(bf);
        bf.close();
        boardWriter.close();
    }

    // Creates new board full of 'Fog of War'
    public static void composeBoard(BattleField newField) throws IOException {
        CharArrayWriter boardWriter = new CharArrayWriter();
        FileWriter bf = new FileWriter("battlefield");
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