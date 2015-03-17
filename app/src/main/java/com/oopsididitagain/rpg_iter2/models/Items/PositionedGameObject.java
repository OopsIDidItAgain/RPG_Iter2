package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.GameObject;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Positionable;

public abstract class PositionedGameObject extends GameObject
		implements Positionable {
	private Position position;

	public PositionedGameObject(String id, Position position) {
		super(id);
		this.position = position;
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
