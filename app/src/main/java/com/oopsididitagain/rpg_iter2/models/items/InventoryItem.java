package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.GameObject;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.utils.Priceable;

public class InventoryItem extends GameObject implements Priceable {
	protected double price;
	
	public InventoryItem(String id, double price) {
		super(id);
		this.price = price;
	}
	
	public InventoryItem(TakeableItem item) {
		super(item.getId());
		this.price = item.price();
	}
	
	public void removeFrom(Inventory inventory) {
		inventory.remove(this);
	}
	
	@Override
	public double price() {
		return price;
	}
	
	public void applyDiscount(Discount discount) {
		discount.applyDiscount(price);
	}

	public void displayItem() {
		System.out.println(price);
		
	}
	
	// Should this be Tile destination?
	//public abstract PositionedItem drop(Position destination);

}
