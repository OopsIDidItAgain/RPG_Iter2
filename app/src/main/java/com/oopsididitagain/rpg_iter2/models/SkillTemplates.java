package com.oopsididitagain.rpg_iter2.models;

 /* This class is used for the skill templates, the first argument of every funtion
 * is the thing that is being affected by the skill and the second thing is
 * the "Effect" thats affecting it. This class is used so that multiple skills that 
 * apply similar effects can use the same method.
 */

import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.PickPocket;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;


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
	
	public static void applySkill(Entity entity, InstantStatModifier statModifier){
		
	}
}
