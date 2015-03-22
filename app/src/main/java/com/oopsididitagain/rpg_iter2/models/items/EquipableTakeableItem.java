package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;

public abstract class EquipableTakeableItem extends TakeableItem
		implements StatBlobHolder {
	private StatBlob statBlob;
	private int rank;

	public EquipableTakeableItem(String id, Position position,
			double price, StatBlob statBlob, int rank) {
		super(id, position, price);
		this.statBlob = statBlob;
		this.rank = rank;
	}

	@Override
	public StatBlob statBlob() {
		return statBlob;
	}
	
	public int getRank() {
		return rank;
	}
	
	public abstract InventoryEquipableItem toInventoryItem();

}
