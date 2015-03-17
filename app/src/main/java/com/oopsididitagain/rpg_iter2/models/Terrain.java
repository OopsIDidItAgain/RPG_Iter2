package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;

public enum Terrain implements MovementInhibitor {
	GRASS(false, false),
	WATER(true, false),
	MOUNTAIN(true, true);

	private boolean inhibitGround;
	private boolean inhibitAir;

	private Terrain(boolean inhibitGround, boolean inhibitAir) {
		this.inhibitGround = inhibitGround;
		this.inhibitAir = inhibitAir;
	}
	
	@Override
	public void attemptInhibition(Probe probe) {
		if (probe.onGround() && inhibitGround) probe.deny();
		if (probe.inAir() && inhibitAir) probe.deny();
	}

	@Override
	public void accept(Probe probe) {
		probe.visit(this);
	}

}
