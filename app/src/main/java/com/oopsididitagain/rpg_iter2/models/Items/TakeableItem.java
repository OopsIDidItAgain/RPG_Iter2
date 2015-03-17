package com.oopsididitagain.rpg_iter2.models.items;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Probe;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.MovementPermitter;
import com.oopsididitagain.rpg_iter2.utils.Priceable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class TakeableItem extends PositionedGameObject implements Tileable, Priceable, MovementPermitter {

	private boolean taken = false;
	private double price;

	public TakeableItem(String id, Position position, double price) {
		super(id, position);
		this.price = price;
	}

	@Override
	public void attemptRemoveFrom(Collection<Tileable> tileables) {
		if (taken) tileables.remove(this);
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}
	
	public void take() throws ItemAlreadyTakenException {
		if (taken) throw new ItemAlreadyTakenException("Item: " + getId());
		taken = true;
	}

	@Override
	public double price() {
		return price;
	}
	
	public InventoryItem toInventoryItem() {
		InventoryItem inventoryItem = new InventoryItem(this);
		return inventoryItem;
	}

	@Override
	public void accept(Probe probe) {
		probe.visit(this);
	}

}
