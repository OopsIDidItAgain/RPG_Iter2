package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;

public abstract class InventoryEquipableItem extends InventoryItem implements StatBlobHolder {
	private StatBlob statBlob;

	public InventoryEquipableItem(String id, double price, StatBlob statBlob) {
		super(id, price);
		this.statBlob = statBlob;
	}

	public InventoryEquipableItem(EquipableTakeableItem item) {
		super(item);
		this.statBlob = item.statBlob();
	}
	
	@Override
	public StatBlob statBlob() {
		return statBlob;
	}
}
