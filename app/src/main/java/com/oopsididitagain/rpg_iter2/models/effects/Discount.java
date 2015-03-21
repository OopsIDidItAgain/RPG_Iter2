package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

/*
 * This class is used to discount items when using the bargain effect
 * I'm imagining that the entities can set the discount they want to apply to all
 * their items.
 */
public class Discount implements Effect{
	double baseDiscount;
	double discount;
	int radius = 1;
	
	public Discount(double baseDiscount){
		this.baseDiscount = baseDiscount;
	}
	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double baseDiscount) {
		this.baseDiscount = baseDiscount;
	}

	public void applyDiscount(Inventory inventory){
		inventory.applyDiscount(this);;
	}
	public double applyDiscount(double price){
		return price - discount;
	}
	@Override
	public void applyMultiplier(int m){
		this.discount = baseDiscount * m;
	}

	@Override
	public int getRadius() {
		return this.radius;
	}

	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		skillProbe.setUpSkill(this, avatar, tiles);
		
	}
	

}
