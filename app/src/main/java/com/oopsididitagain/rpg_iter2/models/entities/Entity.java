package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

/**
 * Created by parango on 3/11/15.
 */
public abstract class Entity extends PositionedGameObject implements Positionable, TiledProbeVisitable, StatBlobHolder,MovementInhibitor {
	protected EntityStatus entityStatus;
	protected Inventory inventory;
	protected boolean isCurrentlyFlying;
	protected StatBlob statblob;

	public Entity(String id, Position position,StatBlob statblob){
		super(id, position);
		this.statblob = statblob;
		this.entityStatus = new EntityStatus(EntityStatus.PLAYING);
		this.inventory = new Inventory();
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
	public abstract void visit(InventoryUnusableItem item);
	public abstract void visit(InventoryArmorItem item);
	public abstract void visit(InventoryWeaponItem item);

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

	public void move(Tile fromTile, Tile targetTile, Position updatedPosition) {
		fromTile.remove(this);
		setPosition(updatedPosition);
		targetTile.add(this);
		targetTile.interact(this);
	}
	
	@Override
	public StatBlob statBlob() {
		return this.statblob;
	}

	
}
