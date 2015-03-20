package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.EquippableItemType;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;

public abstract class EquippableTakeableItem extends TakeableItem
		implements StatBlobHolder {
	private StatBlob statBlob;

	public EquippableTakeableItem(String id, Position position,
			double price, StatBlob statBlob) {
		super(id, position, price);
		this.statBlob = statBlob;
	}

	@Override
	public StatBlob statBlob() {
		return statBlob;
	}
	
	public abstract EquippableItemType getEquippableItemType();

}
