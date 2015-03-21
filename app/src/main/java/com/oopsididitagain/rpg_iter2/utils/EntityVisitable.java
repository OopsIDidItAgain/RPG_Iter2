package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;

public interface EntityVisitable {
	public void accept(Entity entity) throws InvalidMovementException;
}