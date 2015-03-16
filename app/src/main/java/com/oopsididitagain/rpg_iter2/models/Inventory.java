package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import com.oopsididitagain.rpg_iter2.models.Items.Item;
/**
 * Created by parango on 3/11/15.
 * 
 */

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.items.Item;

public class Inventory {
<<<<<<< HEAD
	
	private ArrayList<Item> itemList;
	private int selected;
	
	public Inventory() {
		itemList = new ArrayList<Item>();
		selected = -1;
	}
	
	public void add(Item item) {
		itemList.add(item);
	}
	
	public boolean remove(Item item) {
		return itemList.remove(item);
	}
	
	public Item getItem(int index) throws NullPointerException {
		Item item = null;
		try {
			item = itemList.get(index);
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
			item = itemList.get(selected);
		}
		return item;
	}
	
	public void select(int selected) {
		this.selected = selected;
	}
	
	public int getNumberOfItems() {
		return itemList.size();
	}
	
}
=======

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
>>>>>>> f6e0f8d26bd89dbb00281c4bdd0726b55307d17f
