package com.oopsididitagain.rpg_iter2.models;

import java.util.SortedSet;
import java.util.TreeSet;


import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.ProjectileVisitable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

public class Tile implements Assetable, Positionable {
	private SortedSet<TiledEntityVisitable> entityVisitables;
	private SortedSet<TiledProbeVisitable> probeVisitables;
	private Position position;
	private Terrain terrain;

	public Tile(Position position, Terrain terrain) {
		this.entityVisitables = new TreeSet<TiledEntityVisitable>();
		this.probeVisitables = new TreeSet<TiledProbeVisitable>();
		this.position = position;
		this.terrain = terrain;
	}
	
	public void interact(Entity entity) {
		for (TiledEntityVisitable entityVisitable: entityVisitables) {
			try {
				entityVisitable.accept(entity);
				if (entityVisitable.removeable())
					entityVisitables.remove(entityVisitable);
			} catch(InvalidMovementException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void checkMovable(MovementProbe movementProbe) {
		terrain.accept(movementProbe);
		for (TiledProbeVisitable probeVisitable: probeVisitables) 
			probeVisitable.accept(movementProbe);
	}
	
	public void checkTileContents(MovementProbe movementProbe) {
		for (TiledEntityVisitable tev: entityVisitables) 
			tev.accept(movementProbe);
		for (TiledProbeVisitable tpv: probeVisitables) 
			tpv.accept(movementProbe);
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public Direction getDirection() {
		return position.getDirection();
	}

	public void add(TiledProbeVisitable tileable) {
		probeVisitables.add(tileable);
	}
	
	public void add(TiledEntityVisitable tileable) {
		entityVisitables.add(tileable);
	}

	public void remove(TiledEntityVisitable tileable) {
		entityVisitables.remove(tileable);
	}

	public void remove(TiledProbeVisitable tileable) {
		probeVisitables.remove(tileable);
	}

	@Override
	public String getId() {
		return terrain.getId();
	}
	
	public SortedSet<Tileable> getTilebles()	 {
		SortedSet<Tileable> tileables = new TreeSet<Tileable>();
		tileables.add(terrain);
		tileables.addAll(entityVisitables);
		tileables.addAll(probeVisitables);
		return tileables;
	}
}