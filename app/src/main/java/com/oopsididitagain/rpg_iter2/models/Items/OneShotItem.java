package com.oopsididitagain.rpg_iter2.models.Items;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Entities.Entity;
import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.StatModifiable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class OneShotItem extends PositionedItem implements InstantStatModifier {
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
	public void affect(StatModifiable target) {
		// Law of Demeter...
		target.statBlob().merge(blob);
	}

}
