package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.ArrayList;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.items.Item;

public class PickPocket implements Effect{
	int numberOfItemsStolen = 1;
	
	
	@Override
	public void applyMultiplier(int m) {
		numberOfItemsStolen *= m;
	}


	public void pickPocket(Inventory stealingEntity, Inventory robbedEntity) {
		ArrayList<Item> stolenItems = robbedEntity.getItems(numberOfItemsStolen);
		stealingEntity.add(stolenItems);
	}

}
