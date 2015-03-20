package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;

public enum Terrain implements Assetable, MovementInhibitor {
	GRASS("G", false, false),
	WATER("W", true, false),
	MOUNTAIN("M", true, true);

	private boolean inhibitGround;
	private boolean inhibitAir;
	private String id;

	private Terrain(String id, boolean inhibitGround, boolean inhibitAir) {
		this.id = id;
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

	@Override
	public String getId() {
		return id;
	}

}
