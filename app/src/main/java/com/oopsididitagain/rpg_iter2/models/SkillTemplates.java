package com.oopsididitagain.rpg_iter2.models;

 /* This class is used for the skill templates, the first argument of every funtion
 * is the thing that is being affected by the skill and the second thing is
 * the "Effect" thats affecting it. This class is used so that multiple skills that 
 * apply similar effects can use the same method.
 */

import com.oopsididitagain.rpg_iter2.models.Effects.Discount;
import com.oopsididitagain.rpg_iter2.models.Effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.Entities.Entity;
import com.oopsididitagain.rpg_iter2.models.Items.Item;


public class SkillTemplates {
	public static void applySkill(Item item, Discount discount){
		item.applyDiscount(discount);
	}

	public static void applySkill(Entity entity, EntityStatusModifier modifier){
		entity.changeStatus(modifier);
	}
	
}
