package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.models.Entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.MovementPermitter;

public class Probe {
	private Entity entity;
	private boolean movementSuccess = true;
	
	public Probe(Entity entity) {
		this.entity = entity;
	}

	public void attemptMove(Tile fromTile, Tile targetTile) {
		targetTile.checkMovable(this);
		if (movementSuccess)
			moveEntity(fromTile, targetTile);
	}
	
	private void moveEntity(Tile previous, Tile targetTile) {
		/* TODO: This is bad OOP, I need to fix this */
		previous.remove(entity);
		entity.setPosition(targetTile.getPosition());
		targetTile.add(entity);
	}
	
	public void visit(MovementPermitter movementPermitter) {
		return;
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
	
	public void deny() {
		movementSuccess = false;
	}
}
