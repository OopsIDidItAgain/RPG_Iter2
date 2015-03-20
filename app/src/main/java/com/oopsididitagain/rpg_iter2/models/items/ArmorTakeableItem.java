package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.EquippableItemType;

public class ArmorTakeableItem extends EquippableTakeableItem {

	private EquippableItemType type;

	public ArmorTakeableItem(String id, Position position,
			double price, StatBlob statBlob, EquippableItemType type) {
		super(id, position, price, statBlob);
		this.type = type;
	}

	@Override
	public EquippableItemType getEquippableItemType() {
		return type;
	}

}
