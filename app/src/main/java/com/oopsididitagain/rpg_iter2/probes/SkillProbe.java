package com.oopsididitagain.rpg_iter2.probes;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.TileContentsProbeStatus;

public class SkillProbe {
	public SkillProbe(){
	}

	public void performSkill(EntityStatusModifier entityStatusModifier,Avatar avatar, MiniMap minimap) {
		Position center = minimap.getCenter();
		Direction direction = avatar.getDirection();
		Position position = center.createPositionAtDirection(direction);
		Tile currentTile;
		MovementProbe probe = new MovementProbe(avatar);
		while(minimap.inBounds(position)){
			currentTile = minimap.getTile(position);
			currentTile.checkMovable(probe);
			if(probe.getStatus() == MovementProbeStatus.MOVEMENT_DENIED){
				break;
			}
			currentTile.checkTileContents(probe);
			if(probe.checkStatus(TileContentsProbeStatus.ENTITY_DETECTED)){
				break;
			}
			position = position.createPositionAtDirection(direction);
		}
			
		Entity entity = probe.getEntity();
		entityStatusModifier.changeStatus(entity);
	
	}
}



