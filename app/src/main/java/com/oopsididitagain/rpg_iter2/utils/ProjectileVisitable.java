package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;

public interface ProjectileVisitable {
	public void accept(MovementProbe probe);
}