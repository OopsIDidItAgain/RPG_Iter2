package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.effects.Discount;

/**
 * Created by parango on 3/11/15.
 * Tess added the price, I think every Item should have a price
 * so that every entity can trade.
 */
public class Item {
	double price;
	public Item(double price){
		this.price = price;
	}
	public void applyDiscount(Discount d){
		this.price = d.applyDiscount(this.price);
	}
	public void displayItem(){
		System.out.println(price);
	}
}
