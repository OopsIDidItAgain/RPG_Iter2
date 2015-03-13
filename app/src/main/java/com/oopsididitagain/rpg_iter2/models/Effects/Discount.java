package com.oopsididitagain.rpg_iter2.models.Effects;
/*
 * This class is used to discount items when using the bargain effect
 * I'm imagining that the entities can set the discount they want to apply to all
 * their items.
 */
public class Discount implements Effect{
	double discount;
	public Discount(double discount){
		this.discount = discount;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double applyDiscount(double price){
		return (price - this.discount);
	}
	@Override
	public void applyMultiplier(int m){
		this.discount *= m;
	}
	

}
