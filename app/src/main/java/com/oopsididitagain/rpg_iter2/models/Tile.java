package com.oopsididitagain.rpg_iter2.models;

import java.util.LinkedList;
import java.util.List;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Positionable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class Tile implements Positionable {
	private List<Tileable> tileables;
	private Position position;
	private Terrain terrain;

	public Tile(Position position, Terrain terrain) {
		this.tileables = new LinkedList<Tileable>();
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
}
