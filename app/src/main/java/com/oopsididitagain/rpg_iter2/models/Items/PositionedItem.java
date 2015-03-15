package com.oopsididitagain.rpg_iter2.models.Items;

import com.oopsididitagain.rpg_iter2.models.GameObject;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.utils.Positionable;

public abstract class PositionedItem extends GameObject implements Positionable {
	private Position position;

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

}
