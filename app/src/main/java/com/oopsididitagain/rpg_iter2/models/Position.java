package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

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

	public void setDirection(Direction facing) {
		this.facing = facing;
	}

	public Direction getDirection() {
		return facing;
	}

	public void checkBounds(int lowerWidth, int lowerHeight, int upperWidth, int upperHeight) throws PositionOutOfBoundsException {
		if (y >= upperHeight || x >= upperWidth || x < lowerWidth || y < lowerHeight)
			throw new PositionOutOfBoundsException("Position (" + x + ", " +y + ") is out of bounds!");
	}
	
	public Position createPositionAtDirection(Direction direction) {
		Position targetPosition;
		switch(direction) {
		case EAST: targetPosition = new Position(this.getY(), this.getX() + 1, Direction.EAST);
			break;
		case NORTH: targetPosition = new Position(this.getY() - 1, this.getX(), Direction.NORTH);
			break;
		case NORTHEAST:targetPosition = new Position(this.getY() - 1, this.getX() + 1, Direction.NORTHEAST);
			break;
		case NORTHWEST:targetPosition = new Position(this.getY() - 1, this.getX() - 1, Direction.NORTHWEST);
			break;
		case SOUTH:targetPosition = new Position(this.getY() + 1, this.getX(), Direction.SOUTH);
			break;
		case SOUTHEAST:targetPosition = new Position(this.getY() + 1, this.getX() + 1, Direction.SOUTHEAST);
			break;
		case SOUTHWEST:  targetPosition = new Position(this.getY() + 1, this.getX() - 1, Direction.SOUTHWEST); 
			break;
		case WEST:targetPosition = new Position(this.getY(), this.getX() - 1, Direction.WEST);
			break;
		default:
			targetPosition = new Position(x, y, direction);
		}
		return targetPosition;
	}
}
