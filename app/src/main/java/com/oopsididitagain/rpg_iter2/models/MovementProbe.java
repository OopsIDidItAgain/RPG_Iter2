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
	private Entity returnedEntity;
	private Entity entity;
	private MovementProbeStatus movementProbeStatus = MovementProbeStatus.MOVEMENT_OK;
	private Set<TileContentsProbeStatus> tileFlags = new HashSet<TileContentsProbeStatus>();
	private List<PositionedGameObject> positionedGameObjects = new ArrayList<PositionedGameObject>();
	private Trap trap;
	
	public MovementProbe(Entity entity) {
		this.entity = entity;
	}
	
	public MovementProbe(){}

	public void inspect(Tile targetTile) {
		targetTile.checkMovable(this);
	}
	
	public void denyMovement() {
		this.movementProbeStatus = MovementProbeStatus.MOVEMENT_DENIED;
	}
	
	public void visit(MovementInhibitor movementInhibitor) {
		movementInhibitor.attemptInhibition(this);
	}
	
	public boolean inAir() {
		//if (entity == null) return true;
		return entity.isCurrentlyFlying();
	}
	
	public boolean onGround() {
		//if (entity == null) return true;
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
		this.returnedEntity = entity;
		tileFlags.add(TileContentsProbeStatus.ENTITY_DETECTED);
	}
	public boolean checkStatus(TileContentsProbeStatus status){
		return tileFlags.contains(status);
	}
	public Entity getReturnedEntity(){
		return this.returnedEntity;
	}

	public void setEntityFlag() {
		// TODO Auto-generated method stub
		
	}

	public void add(Trap trap) {
		this.trap = trap;
		tileFlags.add(TileContentsProbeStatus.TRAP_DETECTED);
	}

	public Trap getTrap() {
		return trap;
	}
	
}
