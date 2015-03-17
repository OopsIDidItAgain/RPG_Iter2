package com.oopsididitagain.rpg_iter2.models.effects;
/*
 * This class is used to discount items when using the bargain effect
 * I'm imagining that the entities can set the discount they want to apply to all
 * their items.
 */
public class Discount implements Effect{
	double baseDiscount;
	double discount;
	public Discount(double baseDiscount){
		this.baseDiscount = baseDiscount;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double baseDiscount) {
		this.baseDiscount = baseDiscount;
	}

	public double applyDiscount(double price){
		return (price - this.discount);
	}
	@Override
	public void applyMultiplier(int m){
		this.discount = baseDiscount * m;
	}
	

}
