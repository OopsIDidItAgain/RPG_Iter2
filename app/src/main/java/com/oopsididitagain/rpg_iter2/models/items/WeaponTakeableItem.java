package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.EquippableItemType;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class WeaponTakeableItem extends EquippableTakeableItem {
	private WeaponItemType type;

	public WeaponTakeableItem(String id, Position position,
			double price, StatBlob statBlob, WeaponItemType type) {
		super(id, position, price, statBlob);
		this.type = type;
	}

	@Override
	public EquippableItemType getEquippableItemType() {
		return EquippableItemType.WEAPON;
	}
	
	public WeaponItemType getWeaponItemType() {
		return type;
	}

}
