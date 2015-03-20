package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Positionable;

public abstract class PositionedGameObject extends GameObject
		implements Positionable {
	protected Position position;

	public PositionedGameObject(String id, Position position) {
		super(id);
		this.position = position;
	}

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public Direction getDirection() {
		return position.getDirection();
	}

}
