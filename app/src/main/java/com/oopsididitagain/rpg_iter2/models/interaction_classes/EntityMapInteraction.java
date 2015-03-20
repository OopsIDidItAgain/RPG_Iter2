package com.oopsididitagain.rpg_iter2.models.interaction_classes;



import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.SkilledEntity;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

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


	private Entity getEntityAt(Position p, Direction d) {
		// TODO Auto-generated method stub
		return null;
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
