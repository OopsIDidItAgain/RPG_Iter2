package com.oopsididitagain.rpg_iter2.models.Items;

import com.oopsididitagain.rpg_iter2.models.GameObject;

public abstract class InventoryItem extends GameObject {

	public InventoryItem(String id) {
		super(id);
	}
	
	// Should this be Tile destination?
	//public abstract PositionedItem drop(Position destination);

}
