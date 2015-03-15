package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Direction;

/*
 * It holds the position of an Entity
 */
public class Position {
	private int x;
	private int y;
	private Direction facing;

	public Position( int y, int x, Direction facing) {
		super();
		this.x = x;
		this.y = y;
		this.facing = facing;
	}

	public Position(int y, int x ) {
		super();
		this.x = x;
		this.y = y;
		this.facing = Direction.NORTH;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
	}

	public Direction getDirection() {
		return facing;
	}
}
