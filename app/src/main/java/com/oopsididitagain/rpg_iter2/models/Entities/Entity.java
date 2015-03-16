package com.oopsididitagain.rpg_iter2.models.Entities;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Positionable;

/**
 * Created by parango on 3/11/15.
 */
public abstract class Entity implements Positionable {
	protected EntityStatus entityStatus;
	protected Position position;
	protected Inventory inventory;

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
}
