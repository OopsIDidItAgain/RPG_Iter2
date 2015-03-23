package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.MovementInhibitor;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

public class Terrain implements Assetable, TiledProbeVisitable, MovementInhibitor {
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
	public void attemptInhibition(MovementProbe movementProbe) {
		if (movementProbe.onGround() && inhibitGround) movementProbe.denyMovement();
		if (movementProbe.inAir() && inhibitAir) movementProbe.denyMovement();
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.visit(this);
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

	@Override
	public String toString() {
		switch(id) {
		case "G": return "GRASS";
		case "W": return "WATER";
		case "M": return "MOUNTAIN";
		}
		System.out.println("ERROR TO STRING ON TERRAIN!");
		return "GRASS";
	}
}
