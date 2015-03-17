package com.oopsididitagain.rpg_iter2.models;

 /* This class is used for the skill templates, the first argument of every funtion
 * is the thing that is being affected by the skill and the second thing is
 * the "Effect" thats affecting it. This class is used so that multiple skills that 
 * apply similar effects can use the same method.
 */

import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.PickPocket;
import com.oopsididitagain.rpg_iter2.models.effects.StatModifier;
import com.oopsididitagain.rpg_iter2.models.effects.TimedStatModifier;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
/*
 * skills:
 * bind Wounds X
 * bargain X
 * observation ?
 * 1 -
 * 2 -
 * brawl -
 * long range -
 * enchant X
 * boon X
 * bane X
 * staff X
 * pick pocket X
 * detect and remove trap ?
 * creep X
 * 
 * 
 * 
 */


public class SkillTemplates {
	public static void applySkill(Inventory inventory, Discount discount){
		inventory.applyDiscount(discount);
	}

	public static void applySkill(Entity entity, EntityStatusModifier modifier){
		entity.changeStatus(modifier);
	}
	
	public static void applySkill(Inventory stealingEntity,Inventory robbedEntity, PickPocket pickpocket){
		pickpocket.pickPocket(stealingEntity,robbedEntity);
	}
	
	public static void applySkill(StatBlob targetStatblob, StatModifier statModifier){
		statModifier.affect(targetStatblob);
	}
	
	public static void applySkill(StatBlob targetStatblob, TimedStatModifier statModifier){
		
	}
	
	
	
	/* The only difference between these, this has to check if a weapon is in use
	public static void applySkill(WeaponHolder avatar, StatModifier statModifier){
		//statModifier.affect(targetStatblob);
		statModifier.affect(avatar);
	}
	*/
}
