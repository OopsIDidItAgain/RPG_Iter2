package com.oopsididitagain.rpg_iter2.probes;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.Trap;
import com.oopsididitagain.rpg_iter2.models.effects.DetectTrap;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.effects.PickPocket;
import com.oopsididitagain.rpg_iter2.models.effects.StatModifier;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.stats.StatCollection;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.TileContentsProbeStatus;

public class SkillProbe {
	public SkillProbe(){
	}

	public void performSkill(EntityStatusModifier entityStatusModifier,Avatar avatar, MiniMap minimap) {
		Entity entity = getEntity(avatar,minimap);
		if(entity != null){
			entityStatusModifier.changeStatus(entity);
		}
	}

	public void setUpSkill(Observe observe, Avatar avatar, MiniMap minimap) {//Still working on observe
		Entity entity = getEntity(avatar,minimap);
		performSkill(observe,entity);
	}

	
	private void performSkill(Observe observe, Entity entity) {
		if(entity != null){
			observe.observe(entity.statBlob());
		}
	}

	public void setUpSkill(Discount discount, Avatar avatar, MiniMap minimap) {
		Entity entity = getEntity(avatar,minimap);
		performSkill(discount,entity);
		
	}
	private void performSkill(Discount discount, Entity entity) {
		if(entity != null){
			discount.applyDiscount(entity.getInventory());
		}
	}

	public void setUpSkill(PickPocket pickPocket, Avatar avatar, MiniMap tiles) {
		Entity entity = getEntity(avatar,tiles);
		performSkill(pickPocket,avatar,entity);
	}

	private void performSkill(PickPocket pickPocket, Avatar stealingEntity,
			Entity robbedEntity) {
		if(robbedEntity != null){
			pickPocket.pickPocket(stealingEntity.getInventory(), robbedEntity.getInventory());
		}
		
	}

	private Entity getEntity(Avatar avatar, MiniMap minimap){
		Position center = minimap.getCenter();
		Direction direction = avatar.getDirection();
		Position position = center.createPositionAtDirection(direction);
		Tile currentTile;
		MovementProbe probe = new MovementProbe(avatar);
		while(minimap.inBounds(position)){
			currentTile = minimap.getTile(position);
			currentTile.checkTileContents(probe);
			if(probe.checkStatus(TileContentsProbeStatus.ENTITY_DETECTED)){
				break;
			}
			currentTile.checkMovable(probe);
			if(probe.getStatus() == MovementProbeStatus.MOVEMENT_DENIED){
				break;
			}
			position = position.createPositionAtDirection(direction);
		}
			
		Entity entity = probe.getReturnedEntity();
		return entity;
		
	}
	
	public void performSkill(StatModifier statModifier, Avatar avatar,MiniMap tiles) {
		Entity entity = getEntity(avatar,tiles);
		statModifier.changeStats(entity,avatar);
	}

	public void setUpSkill(DetectTrap detectTrap, Avatar avatar, MiniMap tiles) {
		Trap t = getTrap(tiles,avatar);
		if(t!=null){
			detectTrap.detectTrap(t);
		}
	}

	private Trap getTrap(MiniMap tiles, Avatar avatar) {
		Position center;
		Tile currentTile;
		MovementProbe mp = new MovementProbe();
		for(int i =  0; i!= tiles.length();++i){
			for(int j = 0; j!=tiles.width(); ++j){
				center = new Position(i,j);
				currentTile = tiles.getTile(center);
				currentTile.checkTileContents(mp);
				if(mp.checkStatus(TileContentsProbeStatus.TRAP_DETECTED)){
					return mp.getTrap();
				}
			}
		}
		return null;
	}

	public void setUpSkill(DetectTrap detectTrap, Avatar avatar, MiniMap tiles,
			int removeRadius) {
		Position center = tiles.getCenter();
		Direction direction = avatar.getDirection();
		Position position = center.createPositionAtDirection(direction);
		Tile currentTile = null;
		MovementProbe probe = new MovementProbe(avatar);
		int i = 0;
		while(i < removeRadius){
			currentTile = tiles.getTile(position);
			currentTile.checkTileContents(probe);
			if(probe.checkStatus(TileContentsProbeStatus.TRAP_DETECTED)){
				break;
			}
			currentTile.checkMovable(probe);
			if(probe.getStatus() == MovementProbeStatus.MOVEMENT_DENIED){
				break;
			}
			position = position.createPositionAtDirection(direction);
			++i;
		}
			
		Trap trap = probe.getTrap();
		if(currentTile != null && trap != null){
			detectTrap.removeTrap(trap,currentTile);
		}
	}



	
}



