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

public enum SlidingDirection {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Returns the sliding direction associated with the given char.
     *
     * @param c a char
     * @return the sliding direction associated with the given char.
     * @throws IllegalArgumentException when 'c' is not a valid char
     * ('u', 'U', 'd', 'D', 'l', 'L', 'r', 'R').
     */
    public static SlidingDirection fromString(char c) {
        c = Character.toUpperCase(c);
        return switch (c) {
            case 'U' -> UP;
            case 'D' -> DOWN;
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            default -> throw new IllegalArgumentException("Invalid character!");
        };
    }

    public static SlidingDirection[] validMovesAtNorthWest() {
        return new SlidingDirection[]{UP, LEFT};
    }

    public static SlidingDirection[] validMovesAtNorth() {
        return new SlidingDirection[]{UP, LEFT, RIGHT};
    }

    public static SlidingDirection[] validMovesAtNorthEast() {
        return new SlidingDirection[]{UP, RIGHT};
    }

    public static SlidingDirection[] validMovesAtWest() {
        return new SlidingDirection[]{UP, LEFT, DOWN};
    }

    public static SlidingDirection[] validMovesAtEast() {
        return new SlidingDirection[]{UP, RIGHT, DOWN};
    }

    public static SlidingDirection[] validMovesAtSouthWest() {
        return new SlidingDirection[]{DOWN, LEFT};
    }

    public static SlidingDirection[] validMovesAtSouth() {
        return new SlidingDirection[]{DOWN, LEFT, RIGHT};
    }

    public static SlidingDirection[] validMovesAtSouthEast() {
        return new SlidingDirection[]{DOWN, RIGHT};
    }

    public static SlidingDirection[] getValidMoves(LocationArea area) {
        return switch (area) {
            case NORTH_EAST -> validMovesAtNorthEast();
            case NORTH -> validMovesAtNorth();
            case NORTH_WEST -> validMovesAtNorthWest();
            case EAST -> validMovesAtEast();
            case CENTER -> SlidingDirection.values();
            case WEST -> validMovesAtWest();
            case SOUTH_EAST -> validMovesAtSouthEast();
            case SOUTH -> validMovesAtSouth();
            case SOUTH_WEST -> validMovesAtSouthWest();
        };
    }

}
