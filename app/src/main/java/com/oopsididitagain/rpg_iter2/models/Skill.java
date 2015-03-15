package com.oopsididitagain.rpg_iter2.models;
import com.oopsididitagain.rpg_iter2.models.Effects.Discount;
/*
 * This is the class that holds a particular skill, I specified that all
 * skills multipliers would initially be set to one, but we can change that if 
 * we want.
 * It has a name so that, we can look up the skill in the hashmap of the avatar,
 * we might want to predefine them though, to protect from mispelling.
 */
import com.oopsididitagain.rpg_iter2.models.Effects.Effect;

public class Skill {
	String name;
	int multiplier;
	Effect effect;
	boolean passive;
	
	public Skill(String name ){//add the passive thingy
		this.name = name;
		this.multiplier = 1;
		applyMultiplier();
	}
	public void setEffect(Effect effect){
		this.effect = effect;	
	}
	
	public String getName(){
		return name;
	}
	public void applyMultiplier(){
		effect.applyMultiplier(multiplier);
	}
	public void increaseMultiplier(){
		this.multiplier++;
		applyMultiplier();
	}
	public Effect getEffect() {
		return effect;
	}

}
