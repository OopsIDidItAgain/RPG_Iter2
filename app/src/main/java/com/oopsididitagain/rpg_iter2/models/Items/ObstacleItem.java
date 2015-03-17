package com.oopsididitagain.rpg_iter2.models.items;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Probe;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class ObstacleItem extends PositionedGameObject implements Tileable, MovementInhibitor {

	public ObstacleItem(String id, Position position) {
		super(id, position);
	}

	@Override
	public void accept(Entity entity) throws InvalidMovementException {
		throw new InvalidMovementException("ObstacleItem has been moved onto by an Entity: " + getId());
	}

	@Override
	public void accept(Probe probe) {
		probe.visit(this);
	}

	@Override
	public void attemptInhibition(Probe probe) {
		probe.deny();
	}

	@Override
	public void attemptRemoveFrom(Collection<Tileable> tileables) {
		System.out.println("Can't remove a Obstacle Item !");
	}
	
	

}
