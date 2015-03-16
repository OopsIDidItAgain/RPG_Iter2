package com.oopsididitagain.rpg_iter2.models.interaction_classes;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.SkillTemplates;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.Effect;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;

/*
 * This should hold the interaction between the entities and skills
 * I did it this way to avoid breaking law of demeter 
 * "avatar.getOccupation.bargain(Item,Discount);"
 * and to avoid encumberance.So that the entity
 * doesn't have methods that it won't need. 
 * 
 * This can have all the skills because its handling the skills for 
 * all entities.
 * 
 * We could subclass entity out into smasher sneak and summoner, as well.
 * 
 * but essentially I imagined the view displaying the active 
 * skills that the entity can use based on their occupation,
 * 
 * GameController:
 * takeSneakInput();
 * takeSmasherInput();
 * takeSummonerInput();
 * 
 * all these methods would respond to specfic numbers pressed by the played and 
 * send the entity calling the skill and the object thats being affected into here.
 * 
 */

public class SkillInteraction {

	public void bargain(Entity e, Skill bargain) {
		Inventory inventory = e.getInventory();
		Effect d = bargain.getEffect();
		SkillTemplates.applySkill(inventory, (Discount)d);
		
	}


}
