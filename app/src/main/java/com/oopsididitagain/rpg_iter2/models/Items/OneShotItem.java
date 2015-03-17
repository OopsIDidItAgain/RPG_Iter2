package com.oopsididitagain.rpg_iter2.models.items;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Probe;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.MovementPermitter;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class OneShotItem extends PositionedGameObject implements InstantStatModifier, Tileable, MovementPermitter {
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
	public void attemptRemoveFrom(Collection<Tileable> tileables) {
		tileables.remove(this);
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

	@Override
	public void affect(StatBlob target) {
		target.merge(statBlob());
	}

	@Override
	public void accept(Probe probe) {
		probe.visit(this);
	}


}
