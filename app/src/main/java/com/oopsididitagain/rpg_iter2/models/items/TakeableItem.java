package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.Priceable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class TakeableItem extends PositionedGameObject implements TiledEntityVisitable, Priceable {

	private boolean taken = false;
	private double price;

	public TakeableItem(String id, Position position, double price) {
		super(id, position);
		this.price = price;
	}

	@Override
	public boolean removeable() {
		return taken;
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
		InventoryItem inventoryItem = new InventoryUnusableItem(this);
		return inventoryItem;
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.MIDDLE;
	}

	@Override
	public void accept(Entity entity) throws InvalidMovementException {
		entity.visit(this);
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.addPositionedGameObject(this);
	}

}
