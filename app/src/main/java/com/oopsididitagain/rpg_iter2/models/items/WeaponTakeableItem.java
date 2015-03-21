package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class WeaponTakeableItem extends EquipableTakeableItem {
	private WeaponItemType type;

	public WeaponTakeableItem(String id, Position position,
			double price, StatBlob statBlob, WeaponItemType type) {
		super(id, position, price, statBlob);
		this.type = type;
	}
	
	public WeaponItemType getWeaponItemType() {
		return type;
	}

	@Override
	public InventoryEquipableItem toInventoryItem() {
		InventoryWeaponItem item = new InventoryWeaponItem(this);
		return item;
	}

}
