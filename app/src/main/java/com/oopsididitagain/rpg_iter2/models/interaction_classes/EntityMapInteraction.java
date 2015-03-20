package com.oopsididitagain.rpg_iter2.models.interaction_classes;


import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.SkilledEntity;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

/*
 * This should hold the interaction between entities and the map,
 * it will check if an avatar can move, return the closest entities
 *  to another avatar
 * 
 */
public class EntityMapInteraction {
	private GameMap gameMap;
	private SkillInteraction skillInteraction;
	
	public EntityMapInteraction(GameMap gameMap){
		this.gameMap = gameMap;
		this.skillInteraction = new SkillInteraction();
	}
	
	public void move(Entity entity, Position toPosition) {
		// Step 1: Make Sure Position is on the Map.
		Tile targetTile;
		try {
			targetTile = gameMap.getTileAt(toPosition);
		} catch (PositionOutOfBoundsException ex) {
			ex.printStackTrace();
			return;
		}
		
		// Step 2: Can we Move to the Tile there?
		MovementProbe movementProbe = new MovementProbe(entity);
		movementProbe.inspect(targetTile);
		
		if (movementProbe.getStatus() == MovementProbeStatus.MOVEMENT_OK) {
			Tile fromTile;
			try {
				fromTile = gameMap.getTileAt(entity.getPosition());
			} catch (PositionOutOfBoundsException e) {
				e.printStackTrace();
				return;
			}
			entity.move(fromTile, targetTile);
		}
	}

	public void setUpSkill( SkilledEntity entity,String skill) {
		switch(skill){
		case"bargain":
			/*
			Direction d = avatar.getDirection();
			Position p = avatar.getPostion();
			Entity e = getEntityAt(p,d);
			Skill bargain = avatar.getSkill("bargain");
			skillInteraction.bargain(e,bargain);
			*/
		}
	}

	private Entity getEntityAt(Position p, Direction d) {
		// TODO Auto-generated method stub
		return null;
	}


}
