package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Position;
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

	@Override
	public TakeableItem toTakeableItem(Position position) {
		TakeableItem item = new TakeableItem(getId(), position, price());
		return item;
	}

	@Override
	public void accept(InventoryViewInteraction inventoryViewInteraction) {
		inventoryViewInteraction.visit(this);
	}
}
