package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.AreaEffectType;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class AreaEffect extends PositionedGameObject implements TiledEntityVisitable, InstantStatModifier {

	private AreaEffectType type;
	
	public AreaEffect(String id, Position position, AreaEffectType type) {
		super(id, position);
		this.type = type;
	}

	@Override
	public void accept(Entity entity) throws InvalidMovementException {
		entity.visit(this);
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public StatBlob statBlob() {
		return type.statBlob();
	}

	@Override
	public void affect(StatBlob target) {
		target.merge(statBlob());
	}

	@Override
	public boolean removeable() {
		return false;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.MIDDLE;
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.addPositionedGameObject(this);
	}

}
