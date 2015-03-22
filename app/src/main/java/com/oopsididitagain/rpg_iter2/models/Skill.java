package com.oopsididitagain.rpg_iter2.models;

/*
 * This is the class that holds a particular skill, I specified that all
 * skills multipliers would initially be set to one, but we can change that if 
 * we want.
 * It has a name so that, we can look up the skill in the hashmap of the avatar,
 * we might want to predefine them though, to protect from mispelling.
 */
import com.oopsididitagain.rpg_iter2.models.effects.Effect;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class Skill {

	public static String BARGAIN = "bargin";
	public static String ONEHAND = "oneHand";
	public static String TWOHAND = "oneHand";
	public static String BRAWL = "brawl";
	public static String RANGED = "ranged";
	public static String BINDWOUNDS = "bindwounds";
	public static String OBSERVATION = "observation";
	public static String ENCHANTMENT = "enchantment";
	public static String BOON = "boon";
	public static String BANE = "bane";
	public static String STAFF = "staff";
	public static String PICKPOCKET = "pickpocket";
	public static String REMOVETRAP = "removetrap";
	public static String CREEP = "creep";
	

	
	int multiplier;
	Effect effect;
	String name;
	boolean active;
	
	public Skill(String name){
		this.multiplier = 1;
		this.name = name; 
	}
	
	public void setEffect(Effect effect){
		this.effect = effect;	
		applyMultiplier();
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

	public int getRadius() {
		return effect.getRadius();
	}

		
	public void applySkill(Avatar avatar, MiniMap tiles,SkillProbe skillProbe) {
		applyMultiplier();
		effect.applySkill(avatar,tiles,skillProbe);
	}
	
	public String getName(){
		return name;
	}
}
