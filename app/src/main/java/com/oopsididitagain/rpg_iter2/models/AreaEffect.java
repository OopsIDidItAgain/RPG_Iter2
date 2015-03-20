package com.oopsididitagain.rpg_iter2.models;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.items.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.AreaEffectType;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.MovementPermitter;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;

public class AreaEffect extends PositionedGameObject implements Tileable, InstantStatModifier, MovementPermitter {

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
	public void accept(Probe probe) {
		probe.visit(this);
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
	public void attemptRemoveFrom(Collection<Tileable> tileables) {
		// Not removable, should I rethink this? -- Joe
		System.out.println("Won't Remove an AreaEffect!");
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.MIDDLE;
	}

}
