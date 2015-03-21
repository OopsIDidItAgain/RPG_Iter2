package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;

public interface MovementInhibitor extends ProbeVisitable {
	public void attemptInhibition(MovementProbe movementProbe);
}
