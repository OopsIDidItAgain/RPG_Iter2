package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

public class ObstacleItem extends PositionedGameObject implements TiledProbeVisitable, MovementInhibitor {

	public ObstacleItem(String id, Position position) {
		super(id, position);
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.visit(this);
	}

	@Override
	public void attemptInhibition(MovementProbe movementProbe) {
		movementProbe.denyMovement();
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
		return false;
	}
	
}
