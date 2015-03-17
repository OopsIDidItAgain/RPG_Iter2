package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;


public class PickPocket implements Effect{
	int numberOfItemsStolen = 1;
	int baseNumberOfItemsStolen = 1;
	
	@Override
	public void applyMultiplier(int m) {
		numberOfItemsStolen = baseNumberOfItemsStolen * m;
	}


	public void pickPocket(Inventory stealingEntity, Inventory robbedEntity) {
		LinkedList<InventoryItem> stolenItems = robbedEntity.getItems(numberOfItemsStolen);
		stealingEntity.add(stolenItems);
	}

}
