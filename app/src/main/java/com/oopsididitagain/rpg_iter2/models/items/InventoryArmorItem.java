package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;

public class InventoryArmorItem extends InventoryEquipableItem {
	private ArmorItemType type;

	public InventoryArmorItem(String id, double price,
			StatBlob statBlob, int rank, ArmorItemType type) {
		super(id, price, statBlob, rank);
		this.type = type;
	}

	public InventoryArmorItem(ArmorTakeableItem item) {
		super(item);
		this.type = item.getArmorItemType();
	}
	
	public ArmorItemType getArmorItemType() {
		return type;
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}

	@Override
	public TakeableItem toTakeableItem(Position position) {
		ArmorTakeableItem item = new ArmorTakeableItem(getId(), position, price(), statBlob(), getRank(), type);
		return item;
	}

	@Override
	public String toSaveableFormat() {
		String[] arr = { getId(), Double.toString(price()), type.toString(), Boolean.toString(isEquipped()),
				Integer.toString(getRank()), statBlob().toSaveFormat() };
		return IOUtil.commaSeperate(arr);
	}
	
}