package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class OneShotItem extends PositionedGameObject implements InstantStatModifier, TiledEntityVisitable {
	private StatBlob blob;

	public OneShotItem(String id, Position position, StatBlob blob) {
		super(id, position);
		this.blob = blob;
	}

	@Override
	public StatBlob statBlob() {
		return blob;
	}

	@Override
	public void affect(StatBlob target) {
		target.merge(statBlob());
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.MIDDLE;
	}

	@Override
	public boolean removeable() {
		return true;
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.addPositionedGameObject(this);
	}


}
