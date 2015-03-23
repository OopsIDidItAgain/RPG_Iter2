package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class Teleporter extends PositionedGameObject implements Tileable, TiledEntityVisitable {

	public Teleporter(String id, Position position) {
		super(id, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

	@Override
	public boolean removeable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.HIGH;
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toSaveableFormat() {
		// TODO Auto-generated method stub
		return "";
	}

}
