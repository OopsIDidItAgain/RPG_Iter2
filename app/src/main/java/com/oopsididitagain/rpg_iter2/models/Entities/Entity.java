package com.oopsididitagain.rpg_iter2.models.Entities;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Effects.EntityStatusModifier;


/**
 * Created by parango on 3/11/15.
 */
public abstract class Entity {
	private EntityStatus entityStatus;
	private Position position;
	public Entity(Position position){
		this.position = position;
		this.entityStatus = new EntityStatus(EntityStatus.PLAYING);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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
}
