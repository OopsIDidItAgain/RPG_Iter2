package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;

public class InventoryUnusableItem extends InventoryItem {

	public InventoryUnusableItem(String id, double price) {
		super(id, price);
	}

	public InventoryUnusableItem(TakeableItem takeableItem) {
		super(takeableItem);
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}
	
}
