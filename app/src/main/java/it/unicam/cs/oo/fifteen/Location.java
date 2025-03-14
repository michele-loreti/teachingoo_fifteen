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
 * Instances of this class are used to represent the position
 * of a tail in the board.
 */
public class Location {

    private final int row;

    private final int column;

    private final LocationArea area;
    private final int size;

    /**
     * Creates a new location for the given row and column.
     *
     * @param row index of a row.
     * @param column index of a column.
     */
    public Location(int row, int column, int size) {
        this.row = row;
        this.column = column;
        this.size = size;
        this.area = LocationArea.getLocationArea(row, column, size);    
    }

    /**
     * Returns the row of this location.
     *
     * @return the row of this location.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of this location.
     *
     * @return the column of this location.
     */
    public int getColumn() {
        return column;
    }

    public SlidingDirection[] getValidMoves() {
        return SlidingDirection.getValidMoves(this.area);
    }

    Location whoIsMoving(SlidingDirection direction) {
        return switch (direction) {
            case UP -> new Location(getRow()+1, getColumn(), size);
            case DOWN -> new Location(getRow()-1,getColumn(), size);
            case LEFT -> new Location(getRow(),getColumn()+1, size);
            case RIGHT -> new Location(getRow(),getColumn()-1, size);
        };
    }

    public int distanceTo(Location location) {
        return Math.abs(getRow() - location.getRow()) + Math.abs(getColumn() - location.getColumn());
    }



}
