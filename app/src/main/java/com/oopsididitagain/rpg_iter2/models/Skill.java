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

	int multiplier;
	Effect effect;
	boolean active;
	
	public Skill(boolean active){
		this.active = active;
		this.multiplier = 1;
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

}
