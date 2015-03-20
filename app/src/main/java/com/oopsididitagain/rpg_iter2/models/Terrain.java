package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;

public class Terrain implements Assetable, Tileable, MovementInhibitor {
	public static final Terrain GRASS = new Terrain("G", false, false);
	public static final Terrain WATER = new Terrain("W", true, false);
	public static final Terrain MOUNTAIN = new Terrain("M", true, true);

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

	@Override
	public boolean removeable() {
		return false;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.LOW;
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

}
