package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.utils.Priceable;


public class Inventory {

	private List<InventoryItem> contents = new ArrayList<InventoryItem>();
	
	public InventoryItem getItemAtIndex(int index) throws IndexOutOfBoundsException {
        if(index<contents.size() && index>=0){
            return contents.get(index);
        }
        return null;

	}
	
	public void add(TakeableItem item){
		contents.add(item.toInventoryItem());
	}
	
	public void remove(InventoryItem item) {
		if(item == null)
            return;
        contents.remove(item);
	}
	
	public void applyDiscount(Discount discount) {
		for(InventoryItem item: contents){
			item.applyDiscount(discount); // Fix this tess TODO
		}
	}

	public List<InventoryItem> getItems(int numberOfItems) {//Tess returns a random item
		List<InventoryItem> returnlist =  new ArrayList<InventoryItem>();
		for(int i = 0; i != numberOfItems; ++i){
			if(contents.isEmpty()){
				break;
			}
			Random random = new Random();
			int max = contents.size() - 1;
			int randomNumber = random.nextInt(max + 1);
			InventoryItem item = contents.remove(randomNumber);
			returnlist.add(item);
		}
		return returnlist;
	}

	public void add(List<InventoryItem> stolenItems) {//Tess add a list of items to inventory
		for(int i = 0; i!= stolenItems.size(); ++i){
			contents.add(stolenItems.get(i));
		}
		
	}

	/* Wouldn't this be in the view? --Joe */
	public void displayInventory() {
		for(InventoryItem i: contents){
			i.displayItem();
		}
	}

	public int size() {
		return contents.size();
	}

	public boolean contains(InventoryItem requirement) {
		return contents.contains(requirement);
	}

	public void remove(Priceable pricedItem) {
		contents.remove(pricedItem);
		
	}

	public void add(Priceable pricedItem) {
		contents.add((InventoryItem) pricedItem);//TODO FIX
		
	}

	public String toSaveableFormat() {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < contents.size(); ++i) {
			sb.append(contents.get(i).toSaveableFormat() + "\n");
		}
		return sb.toString();
	}
}
