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

/**
 * A simple class that models the 15 puzzle game.
 */
public class PuzzleBoard {

    public static final int DEFAULT_SIZE = 4;

    private int[][] puzzleBoard;
    private int freeRow;
    private int freeColumn;

    /**
     * Creates a new instance with the default dimension 4.
     */
    public PuzzleBoard() {
        this(DEFAULT_SIZE);
    }

    /**
     * Creates a new instance with the given dimension.
     *
     * @param n the dimension of the puzzle.
     */
    public PuzzleBoard(int n) {
        puzzleBoard = new int[n][n];
        init();
    }

    /**
     * Initialise the puzzle to the starting configuration.
     */
    private void init() {
        int size = puzzleBoard.length;
        int counter = 1;
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                puzzleBoard[r][c] = (counter++)%(size*size);
            }
        }
        freeRow = size-1;
        freeColumn = size-1;
    }

    public boolean move(SlidingDirection direction) {
        return switch (direction) {
            case UP -> moveUp();
            case DOWN -> moveDown();
            case LEFT -> moveLeft();
            case RIGHT -> moveRight();
        };
    }

    /**
     * Slides up the tile below the unoccupied position. Returns true
     * if the move is valid, false otherwise.
     *
     * @return true if the move is valid, false otherwise.
     */
    public boolean moveUp() {
        if (freeRow < puzzleBoard.length-1) {
            puzzleBoard[freeRow][freeColumn] = puzzleBoard[freeRow+1][freeColumn];
            freeRow++;
            puzzleBoard[freeRow][freeColumn] = 0;
            return true;
        }
        return false;
    }

    /**
     * Slides down the tile above the unoccupied position. Returns true
     * if the move is valid, false otherwise.
     *
     * @return true if the move is valid, false otherwise.
     */
    public boolean moveDown() {
        if (freeRow > 0) {
            puzzleBoard[freeRow][freeColumn] = puzzleBoard[freeRow-1][freeColumn];
            freeRow--;
            puzzleBoard[freeRow][freeColumn] = 0;
            return true;
        }
        return false;
    }

    /**
     * Slides left the tile at the right of the unoccupied position. Returns true
     * if the move is valid, false otherwise.
     *
     * @return true if the move is valid, false otherwise.
     */
    public boolean moveLeft() {
        if (freeColumn < puzzleBoard.length-1) {
            puzzleBoard[freeRow][freeColumn] = puzzleBoard[freeRow][freeColumn+1];
            freeColumn++;
            puzzleBoard[freeRow][freeColumn] = 0;
            return true;
        }
        return false;
    }

    /**
     * Slides right the tile at the left of the unoccupied position. Returns true
     * if the move is valid, false otherwise.
     *
     * @return true if the move is valid, false otherwise.
     */
    public boolean moveRight() {
        if (freeColumn > 0) {
            puzzleBoard[freeRow][freeColumn] = puzzleBoard[freeRow][freeColumn-1];
            freeColumn--;
            puzzleBoard[freeRow][freeColumn] = 0;
            return true;
        }
        return false;
    }

    /**
     * Returns true if the puzzle is solved.
     *
     * @return true if the puzzle is solved.
     */
    public boolean isSolved() {
        int counter = 1;
        int max = puzzleBoard.length*puzzleBoard.length;
        for(int i = 0; i < puzzleBoard.length; i++) {
            for(int j = 0; j < puzzleBoard.length; j++) {
                if (puzzleBoard[i][j] != (counter++)%(max*max)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the size of this puzzle.
     *
     * @return the size of this puzzle.
     */
    public int getSize() {
        return puzzleBoard.length;
    }

    /**
     * Returns true if the position (i, j) is empty.
     *
     * @param i a value greater or equal to 0 and less than <code>this.getSize()</code>.
     * @param j a value greater or equal to 0 and less than <code>this.getSize()</code>.
     * @return true if the position (i, j) is empty.
     * @throws IndexOutOfBoundsException when either i or j are
     * not greater or equal to zero and less than <code>this.getSize()</code>.
     */
    public boolean isEmpty(int i, int j) {
        return puzzleBoard[i][j] == 0;
    }

    /**
     * Returns the element in the position (i, j).
     *
     * @param i a value greater or equal to 0 and less than <code>this.getSize()</code>.
     * @param j a value greater or equal to 0 and less than <code>this.getSize()</code>.
     * @return the element in the position (i, j) is empty.
     * @throws IndexOutOfBoundsException when either i or j are
     * not greater or equal to zero and less than <code>this.getSize()</code>.
     */
    public int get(int i, int j) {
        return puzzleBoard[i][j];
    }
}
