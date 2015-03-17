package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.Probe;

public interface MovementInhibitor extends ProbeVisitable {
	public void attemptInhibition(Probe probe);
}
