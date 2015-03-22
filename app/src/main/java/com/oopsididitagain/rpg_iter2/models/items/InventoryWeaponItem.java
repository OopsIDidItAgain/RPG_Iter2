package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class InventoryWeaponItem extends InventoryEquipableItem {
	private WeaponItemType type;

	public InventoryWeaponItem(String id, double price,
			StatBlob statBlob, WeaponItemType type, int rank) {
		super(id, price, statBlob, rank);
		this.type = type;
	}

	public InventoryWeaponItem(WeaponTakeableItem item) {
		super(item);
		this.type = item.getWeaponItemType();
	}
	
	public WeaponItemType getWeaponItemType() {
		return type;
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

	@Override
	public WeaponTakeableItem toTakeableItem(Position position) {
		WeaponTakeableItem item = new WeaponTakeableItem(getId(), position, price(), statBlob(), getRank(), type);
		return item;
	}

}
