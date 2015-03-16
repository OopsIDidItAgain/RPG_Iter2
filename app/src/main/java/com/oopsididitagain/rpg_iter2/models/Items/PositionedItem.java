package com.oopsididitagain.rpg_iter2.models.Items;

import com.oopsididitagain.rpg_iter2.models.GameObject;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public abstract class PositionedItem extends GameObject implements Positionable, Tileable {
	protected Position position;

	public PositionedItem(String id, Position position) {
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
