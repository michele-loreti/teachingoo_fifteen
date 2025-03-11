/*
 * The MIT License (MIT)
 * Copyright © 2025 Michele Loreti
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the “Software”),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package it.unicam.cs.oo.fifteen;

import java.util.Scanner;

/**
 * A simple console application for playing 15 Puzzle.
 */
public class ConsoleApp {

    private final PuzzleBoard board;

    private final Scanner input = new Scanner(System.in);

    /**
     * Creates a new console app with a puzzle of the default size.
     */
    public ConsoleApp() {
        this(PuzzleBoard.DEFAULT_SIZE);
    }

    /**
     * Creates a new console app with a puzzle of the given size.
     *
     * @param size the size of the puzzle.
     */
    public ConsoleApp(int size) {
        this.board = new PuzzleBoard(size);
    }

    /**
     * Prints to the standard output the current state of the puzzle.
     */
    private void printBoard() {
        for (int i = 0; i < board.getSize(); i++) {
            printRowSeparator();
            printRow(i);
        }
        printRowSeparator();
    }

    /**
     * Prints the content of the i-th row of the puzzle.
     *
     * @param i the index of the row to print.
     */
    private void printRow(int i) {
        for (int j = 0; j < board.getSize(); j++) {
            if (board.isEmpty(i, j)) {
                System.out.print("+    ");
            } else {
                System.out.printf("+ %2d ", board.get(i, j));
            }
        }
        System.out.println("+");
    }

    /**
     * Prints a line separator.
     */
    private void printRowSeparator() {
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

    /**
     * Starts the game.
     */
    public void start() {
        while (true) {
            printBoard();
            doAction();
        }
    }

    /**
     * Handles the user input and performs an action.
     *
     * @return true if an action has been successfully executed.
     */
    private boolean doAction() {
        System.out.println("Enter your move (u, d, l, r):  ");
        System.out.flush();
        boolean flag = true;
        boolean rightCommand = true;
        String command = input.nextLine();
        try {
            flag = board.move(SlidingDirection.fromString(command.charAt(0)));
        } catch (IllegalArgumentException e) {
            rightCommand = false;
        }
        if (!flag) {
            System.out.println("\n\nERROR: Illegal move!\n\n");
            return false;
        }
        if (!rightCommand) {
            System.out.println("\n\nERROR: Illegal command!\n\n");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ConsoleApp cp = new ConsoleApp();
        cp.start();
    }
}
