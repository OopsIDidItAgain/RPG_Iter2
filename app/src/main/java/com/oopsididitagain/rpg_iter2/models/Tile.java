package com.oopsididitagain.rpg_iter2.models;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.TileContentsProbeStatus;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

public class Tile implements Assetable, Positionable {
	private LinkedList<TiledEntityVisitable> entityVisitables;
	private LinkedList<TiledProbeVisitable> probeVisitables;
	private Position position;
	private Terrain terrain;

	public Tile(Position position, Terrain terrain) {
		this.entityVisitables = new LinkedList<TiledEntityVisitable>();
		this.probeVisitables = new LinkedList<TiledProbeVisitable>();
		this.position = position;
		this.terrain = terrain;
	}
	public Entity getEntity(){
		Entity e = null;
		MovementProbe p = new MovementProbe();
		this.checkTileContents(p);
		if(p.checkStatus(TileContentsProbeStatus.ENTITY_DETECTED)){
			e = p.getReturnedEntity();
			
		}
		return e;
		
		
	}
	public void interact(Entity entity) {
		List<TiledEntityVisitable> toRemove = new LinkedList<TiledEntityVisitable>();
		for (TiledEntityVisitable entityVisitable: entityVisitables) {
			try {
				entityVisitable.accept(entity);
				if (entityVisitable.removeable())
					toRemove.add(entityVisitable);
			} catch(InvalidMovementException ex) {
				ex.printStackTrace();
			}
		}
		entityVisitables.removeAll(toRemove);
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
		if (tileable instanceof Entity) System.out.println(((Entity)tileable).toSaveableFormat());
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
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public LinkedList<Tileable> getTilebles()	 {
		LinkedList<Tileable> tileables = new LinkedList<Tileable>();
		tileables.add(terrain);
		tileables.addAll(entityVisitables);
		tileables.addAll(probeVisitables);
		Collections.sort(tileables);
		//for (Tileable t: tileables)
			//if (t instanceof Entity)
				//System.out.println("Entity found: " + ((Entity)t).toSaveableFormat());
		return tileables;
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
}