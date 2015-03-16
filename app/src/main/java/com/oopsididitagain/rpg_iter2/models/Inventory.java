package com.oopsididitagain.rpg_iter2.models;

/**
 * Created by parango on 3/11/15.
 * 
 */

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.Effects.Discount;
import com.oopsididitagain.rpg_iter2.models.Items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.Items.TakeableItem;

public class Inventory {

	private LinkedList<InventoryItem> contents = new LinkedList<InventoryItem>();

	public void add(TakeableItem item){
		contents.add(new InventoryItem(item));
	}
	
	public void add(InventoryItem item){
		contents.add(item);
	}
	
	public void remove(InventoryItem item) {
		contents.remove(item);
	}

	public void applyDiscount(Discount discount) {
		for(InventoryItem item: contents){
			item.applyDiscount(discount);
		}
	}
}
