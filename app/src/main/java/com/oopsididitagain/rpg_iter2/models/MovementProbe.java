package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.MovementProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.TileContentsProbeStatus;

public class MovementProbe {
	private Entity entity;
	private MovementProbeStatus movementProbeStatus = MovementProbeStatus.MOVEMENT_OK;
	private Set<TileContentsProbeStatus> tileFlags = new HashSet<TileContentsProbeStatus>();
	private List<PositionedGameObject> positionedGameObjects = new ArrayList<PositionedGameObject>();
	
	public MovementProbe(Entity entity) {
		this.entity = entity;
	}
	
	public MovementProbe(){}

	public void inspect(Tile targetTile) {
		targetTile.checkMovable(this);
	}
	
	/*private void moveEntity(Tile previous, Tile targetTile) {
		TODO: This is bad OOP, I need to fix this 
		previous.remove(entity);
		entity.setPosition(targetTile.getPosition());
		targetTile.add(entity);
	}*/
	
	public void denyMovement() {
		this.movementProbeStatus = MovementProbeStatus.MOVEMENT_DENIED;
	}
	
	public void visit(MovementInhibitor movementInhibitor) {
		movementInhibitor.attemptInhibition(this);
	}
	
	public boolean inAir() {
		return entity.isCurrentlyFlying();
	}
	
	public boolean onGround() {
		return !entity.isCurrentlyFlying();
	}
	
	public MovementProbeStatus getStatus() {
		return movementProbeStatus;
	}
	
	public void addPositionedGameObject(PositionedGameObject positionedGameObject) {
		positionedGameObjects.add(positionedGameObject);
		tileFlags.add(TileContentsProbeStatus.POSITIONED_GAMEOBJECT_DETECTED);
	}

	public void addEntity(Entity entity) {
		this.entity = entity;
		tileFlags.add(TileContentsProbeStatus.ENTITY_DETECTED);
	}
	public boolean checkStatus(TileContentsProbeStatus status){
		return tileFlags.contains(status);
	}
	public Entity getEntity(){
		return this.entity;
	}
	
}
