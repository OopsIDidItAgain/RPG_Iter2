package com.oopsididitagain.rpg_iter2.models;

import java.util.SortedSet;
import java.util.TreeSet;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Assetable;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class Tile implements Assetable, Positionable {
	private SortedSet<Tileable> tileables;
	private Position position;
	private Terrain terrain;

	public Tile(Position position, Terrain terrain) {
		this.tileables = new TreeSet<Tileable>();
		this.position = position;
		this.terrain = terrain;
	}
	
	public void interact(Entity entity) {
		for (Tileable t: tileables) {
			try {
				t.accept(entity);
				t.attemptRemoveFrom(tileables);
			} catch(InvalidMovementException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void checkMovable(Probe probe) {
		terrain.accept(probe);
		for (Tileable t: tileables) {
			t.accept(probe);
		}
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

	public void add(Tileable tileable) {
		tileables.add(tileable);
	}

	public void remove(Tileable tileable) {
		tileable.attemptRemoveFrom(tileables);
	}

	@Override
	public String getId() {
		return terrain.getId();
	}
}
