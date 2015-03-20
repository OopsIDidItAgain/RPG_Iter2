package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;

public abstract class InventoryEquippableItem extends InventoryItem implements StatBlobHolder {
	private StatBlob statBlob;

	public InventoryEquippableItem(String id, double price, StatBlob statBlob) {
		super(id, price);
		this.statBlob = statBlob;
	}

	public InventoryEquippableItem(EquipableTakeableItem item) {
		super(item);
		this.statBlob = item.statBlob();
	}
	
	@Override
	public StatBlob statBlob() {
		return statBlob;
	}
}
