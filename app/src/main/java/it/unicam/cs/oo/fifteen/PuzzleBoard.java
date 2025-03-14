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

import java.util.Random;

/**
 * A simple class that models the 15 puzzle game.
 */
public class PuzzleBoard {

    public static final int DEFAULT_SIZE = 4;

    private int[][] puzzleBoard;
    private Location freeCell;

    private int shuffleDegree;

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
        freeCell = new Location(size-1, size-1, size);
        shuffleDegree = 0;
    }

    /**
     * Executes the movement corresponding to the given slinding direction.
     * Returns true if the action is valid.
     *
     * @param direction movement direction
     * @return true if the action is valid.
     */
    public boolean move(SlidingDirection direction) {
        Location movingCell = freeCell.whoIsMoving(direction);
        if (isValid(movingCell)) {
            swap(movingCell, freeCell);
            freeCell = movingCell;
            return true;
        } else {
            return false;
        }
    }

    private void swap(Location l1, Location l2) {
        int delta = getShuffleDegree(l1)+getShuffleDegree(l2);
        int temp = get(l1);
        set(l1, get(l2));
        set(l2, temp);
        shuffleDegree += getShuffleDegree(l1)+getShuffleDegree(l2)-delta;
    }

    public void shuffle(int n) {
        Random r = new Random();
        for(int i = 0; i < n; i++) {
            SlidingDirection[] directions = validMoves();
            move(directions[r.nextInt(directions.length)]);
        }
    }

    public SlidingDirection[] validMoves() {
        return freeCell.getValidMoves();
    }

    private void set(Location freeCell, int v) {
        puzzleBoard[freeCell.getRow()][freeCell.getColumn()] = v;
    }

    private int get(Location loc) {
        return get(loc.getRow(), loc.getColumn());
    }

    private boolean isValid(Location loc) {
        return (loc.getRow()>=0)&&(loc.getColumn()>=0)
                &&(loc.getRow()<puzzleBoard.length)
                &&(loc.getColumn()< puzzleBoard.length);
    }

    /**
     * Returns true if the puzzle is solved.
     *
     * @return true if the puzzle is solved.
     */
    public boolean isSolved() {
        return shuffleDegree == 0;
    }

    private Location getLocationOf(int v) {
        return new Location((v-1)/puzzleBoard.length,
                (v-1)%puzzleBoard.length,
                puzzleBoard.length);
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

    private int getShuffleDegree(Location loc) {
        if (isValid(loc)) {
            if (get(loc)==0) return 0;
            return loc.distanceTo(getLocationOf(get(loc)));
        } else {
            return -1;
        }
    }
}
