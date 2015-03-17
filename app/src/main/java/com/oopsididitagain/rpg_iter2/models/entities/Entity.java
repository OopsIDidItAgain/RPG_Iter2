package com.oopsididitagain.rpg_iter2.models.entities;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.EntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

/**
 * Created by parango on 3/11/15.
 */
public abstract class Entity implements Positionable, EntityVisitable, Tileable {
	protected EntityStatus entityStatus;
	protected Position position;
	protected Inventory inventory;
	protected boolean isCurrentlyFlying;

	public Entity(Position position){
		this.position = position;
		this.entityStatus = new EntityStatus(EntityStatus.PLAYING);
	}
	
	@Override
	public Position getPosition() {
		return position;
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
	
	@Override
	public void accept(Entity other) {
		other.visit(this);
	}

	public abstract void visit(Entity other);
	public abstract void visit(TakeableItem item);
	public abstract void visit(InstantStatModifier modifier);

	public boolean isCurrentlyFlying() {
		return isCurrentlyFlying;
	}
	
	public void toggleFlight() {
		isCurrentlyFlying = !isCurrentlyFlying;
	}
	
}
