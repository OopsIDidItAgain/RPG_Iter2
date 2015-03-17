package com.oopsididitagain.rpg_iter2.models.interaction_classes;

import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.SkilledEntity;
import com.oopsididitagain.rpg_iter2.utils.Direction;

/*
 * This should hold the interaction between entities and the map,
 * it will check if an entity can move, return the closest entities
 *  to another entity
 * 
 */
public class EntityMapInteraction {
	GameMap gameMap;
	SkillInteraction skillInteraction;
	
	public EntityMapInteraction(GameMap gameMap){
		this.gameMap = gameMap;
		this.skillInteraction = new SkillInteraction();
	}

	public void setUpSkill( SkilledEntity entity,String skill) {
		switch(skill){
		case"bargain":
			Direction d = entity.getDirection();
			Position p = entity.getPostion();
			Entity e = getEntityAt(p,d);
			Skill bargain = entity.getSkill("bargain");
			skillInteraction.bargain(e,bargain);
		}
		
		
	}

	private Entity getEntityAt(Position p, Direction d) {
		// TODO Auto-generated method stub
		return null;
	}


}
