package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.items.Item;

public class Inventory {
	
	private ArrayList<Item> inventory;
	private int selected;
	
	public Inventory() {
        inventory = new ArrayList<Item>();
		selected = -1;
	}
	
	public void add(Item item) {
        inventory.add(item);
	}
	
	public boolean remove(Item item) {
		return inventory.remove(item);
	}
	
	public Item getItem(int index) throws NullPointerException {
		Item item = null;
		try {
			item = inventory.get(index);
		} catch(IndexOutOfBoundsException e) {
			/*
			 * Should never happen.
			 * Will decide how to handle later.
			 * Option 1: Have getter class check if item is not null, react
			 * 			 appropriately.
			 * 		-Violates LOD
			 * Option 2: Throw an Exception
			 * 		-May not be necessary.
			 * 		-Will keep as is for now.
			 */
			throw new NullPointerException();
		}
		return item;
	}
	
	public Item getSelectedItem() {
		Item item = null;
		if(selected > -1) {
			item = inventory.get(selected);
		}
		return item;
	}
	
	public void select(int selected) {
		this.selected = selected;
	}
	
	public int getNumberOfItems() {
		return inventory.size();
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
