package com.oopsididitagain.rpg_iter2.models;

/**
 * Created by parango on 3/11/15.
 * 
 */

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.items.Item;

public class Inventory {

	private LinkedList<Item> inventory = new LinkedList<Item>();

	public Inventory() {

	}

	public void addItem(Item item){
		inventory.add(item);
	}

	public void applyDiscount(Discount discount) {
		for(Item i: inventory){
			i.applyDiscount(discount);
		}

	}
}
