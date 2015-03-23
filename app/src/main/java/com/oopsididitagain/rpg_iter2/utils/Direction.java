package com.oopsididitagain.rpg_iter2.utils;

/**
 * Direction Enum
 */
public enum Direction {
    SOUTHWEST(1, "SOUTWEST"),
    SOUTH(2, "SOUTH"),
    SOUTHEAST(3, "SOUTHEAST"),
    WEST(4, "WEST"),
    EAST(6, "EAST"),
    NORTHWEST(7, "NORTHWEST"),
    NORTH(8, "NORTH"),
    NORTHEAST(9, "NORTHEAST");

    private final int keyCode;
    private final String desc;

    Direction(int keyCode, String s) {
        this.keyCode= keyCode;
        this.desc = s;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    @Override
    public String toString() {
    	return desc;
    }
}
