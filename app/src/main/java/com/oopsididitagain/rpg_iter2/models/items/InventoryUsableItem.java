package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.EntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;

public class InventoryUsableItem extends InventoryItem implements EntityVisitable, InstantStatModifier {
	private StatBlob statBlob;
	
	public InventoryUsableItem(EffectTakeableItem item) {
		super(item);
		this.statBlob = item.statBlob();
	}
	
	@Override
	public StatBlob statBlob() {
		return statBlob;
	}

	@Override
	public void affect(StatBlob target) {
		target.merge(statBlob());
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

}
