package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;

public class InventoryArmorItem extends InventoryEquippableItem {
	private ArmorItemType type;

	public InventoryArmorItem(String id, double price,
			StatBlob statBlob, ArmorItemType type) {
		super(id, price, statBlob);
		this.type = type;
	}

	public InventoryArmorItem(ArmorTakeableItem item) {
		super(item);
		this.type = item.getArmorItemType();
	}
	
	public ArmorItemType getArmorItemType() {
		return type;
	}
}
