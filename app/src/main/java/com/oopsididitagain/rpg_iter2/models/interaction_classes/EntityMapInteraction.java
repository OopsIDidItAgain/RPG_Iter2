package com.oopsididitagain.rpg_iter2.models.interaction_classes;

import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;
import com.oopsididitagain.rpg_iter2.utils.TileContentsProbeStatus;

/*
 * This should hold the interaction between entities and the map,
 * it will check if an entity can move, return the closest entities
 *  to another entity
 * 
 */
public class EntityMapInteraction {

	private GameMap gameMap;
	private SkillProbe skillProbe;

	
	public EntityMapInteraction(GameMap gameMap){
		this.gameMap = gameMap;
		this.skillProbe = new SkillProbe();
		
	}
	public Entity checkForEntity(Entity entity, Position toPosition) {
		Entity e = null;
		
		Tile targetTile;
		if(gameMap.tileInbounds(toPosition)){
			targetTile = gameMap.getTileAt(toPosition);
	
			MovementProbe movementProbe = new MovementProbe(entity);
			movementProbe.inspect(targetTile);
			
			if(movementProbe.checkStatus(TileContentsProbeStatus.ENTITY_DETECTED)){
				e = movementProbe.getReturnedEntity();
				
			}
		
			return e;
		}else{
			return e;
		}
		
	}
	public boolean move(Entity entity, Position toPosition)   {
		// Step 1: Make Sure Position is on the Map.
		
		if(gameMap.tileInbounds(toPosition)){
			
			
		   
			Tile targetTile, fromTile;
			fromTile = gameMap.getTileAt(entity.getPosition());
			targetTile = gameMap.getTileAt(toPosition);
			
			
			// Step 2: Can we Move to the Tile there?
			MovementProbe movementProbe = new MovementProbe(entity);
			movementProbe.inspect(targetTile);
			
			// Step 3a: If we can, go ahead and Perform movement 
			if (movementProbe.getStatus() == MovementProbeStatus.MOVEMENT_OK) {
				entity.move(fromTile, targetTile, toPosition);
				return true;
			}
	
			// Step 3b: If we cannot, then we need to at least set the Entity's Position in the correct direction.
			else {
				entity.setPosition(new Position(entity.getY(), entity.getX(), toPosition.getDirection()));
				return false;
			} 
			
		}
		return false;
	}

	public void applySkill(Avatar avatar, Skill skill) {
		int radius = skill.getRadius();
		MiniMap tiles = getTilesFor(avatar.getPosition(),radius);
		skill.applySkill(avatar,tiles,skillProbe);
	}
	
	private MiniMap getTilesFor(Position position, int radius){
		return gameMap.getTiles(position.getY(),position.getX(),radius);
	}

}
