package com.oopsididitagain.rpg_iter2.utils;

/**
 * Direction Enum
 */
public enum Direction {
    SOUTHWEST(1),
    SOUTH(2),
    SOUTHEAST(3),
    WEST(4),
    EAST(6),
    NORTHWEST(7),
    NORTH(8),
    NORTHEAST(9);

    private final int keyCode;

    Direction(int keyCode) {
        this.keyCode= keyCode;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    @Override
    public String toString() {
        return Integer.toString(keyCode);
    }

    public Direction fromInteger(int keyCode) {
        switch (keyCode) {
            case 1: return SOUTHWEST;
            case 2: return SOUTH;
            case 3: return SOUTHEAST;
            case 4: return WEST;
            case 5: return EAST;
            case 6: return NORTHWEST;
            case 7: return NORTH;
            case 8: return NORTHEAST;
            default: return NORTH;
        }
    }
}
