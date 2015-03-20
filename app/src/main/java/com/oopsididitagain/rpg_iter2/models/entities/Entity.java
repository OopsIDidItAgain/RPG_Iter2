package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.items.InventoryEquipableItem;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

/**
 * Created by parango on 3/11/15.
 */
public abstract class Entity extends PositionedGameObject implements Positionable, TiledProbeVisitable {
	protected EntityStatus entityStatus;
	protected Inventory inventory;
	protected boolean isCurrentlyFlying;

	public Entity(String id, Position position){
		super(id, position);
		this.entityStatus = new EntityStatus(EntityStatus.PLAYING);
	}
	
	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override 
	public Direction getDirection() {
		return position.getDirection();
	}

	public void changeStatus(EntityStatusModifier entityStatusModifier){
		entityStatusModifier.changeStatus(this);
	}

	public EntityStatus getEntityStatus(){
		return entityStatus;
	}

	public void setEntityStatus(EntityStatus entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public abstract void visit(TakeableItem item);
	public abstract void visit(InstantStatModifier modifier);
	public abstract void visit(InventoryEquipableItem item);
	public abstract void visit(InventoryUnusableItem item);

	public boolean isCurrentlyFlying() {
		return isCurrentlyFlying;
	}
	
	public void toggleFlight() {
		isCurrentlyFlying = !isCurrentlyFlying;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.HIGH;
	}

	public void move(Tile fromTile, Tile targetTile) {
		fromTile.remove(this);
		this.setPosition(targetTile.getPosition());
		targetTile.add(this);
		targetTile.interact(this);
	}
}
