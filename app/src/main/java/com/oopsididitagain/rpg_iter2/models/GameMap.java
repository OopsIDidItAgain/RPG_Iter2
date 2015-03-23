package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;

/*
 * It holds a 2d array of tiles.
 */
public class GameMap {
	private Tile[][] tiles;
	private ArrayList<Npc> listOfNpcs;

	public GameMap(Tile[][] tiles) {
		this.tiles = tiles;
		this.listOfNpcs = new ArrayList<Npc>();
	}

	public Tile getTileAt(Position position) {

		try {
			position.checkBounds(0, 0, getWidth(), getHeight());
		} catch (PositionOutOfBoundsException e) {
			e.printStackTrace();
		}

		return tiles[position.getY()][position.getX()];
	}

	public GameMap(MapDatabase mDb) {
		this(mDb.getTiles());
		readObjectsFromMapDB(mDb);
	}

	public boolean tileInbounds(Position p) {
		if ((p.getX() > tiles[0].length - 1) || (p.getX() < 0)) {

			return false;
		} else if ((p.getY() > tiles.length - 1) || (p.getY() < 0)) {

			return false;
		} else {

			return true;
		}

	}

	private void readObjectsFromMapDB(MapDatabase mDb) {
		for (TiledEntityVisitable tiled : mDb.getEntityVisitables()) {
			// Type cast this is horrible! -- Joe :'(
			Position position = ((PositionedGameObject) tiled).getPosition();
			Tile tile = getTileAt(position);
			tile.add(tiled);
		}
		for (TiledProbeVisitable tiled : mDb.getProbeVisitables()) {
			// Type cast this is horrible! -- Joe :'(
			Position position = ((PositionedGameObject) tiled).getPosition();
			Tile tile = getTileAt(position);
			tile.add(tiled);
			if(tiled instanceof Npc) {
				listOfNpcs.add((Npc)tiled);
			}
		}
	}

	public int getHeight() {
		return (tiles == null) ? 0 : tiles.length;
	}

	public int getWidth() {
		return (tiles == null) ? 0 : tiles[0].length;
	}

	public MiniMap getTiles(int y, int x, int radius) {

		int upperBoundHeight = y + radius + 1;
		if (upperBoundHeight > tiles.length) {
			upperBoundHeight = tiles.length;
		}
		int lowerBoundHeight = y - radius - 1;
		if (lowerBoundHeight < 0) {
			lowerBoundHeight = 0;
		}
		int newHeight = upperBoundHeight - lowerBoundHeight;

		int upperBoundWidth = x + radius + 1;
		if (upperBoundWidth > tiles[0].length) {
			upperBoundWidth = tiles[0].length;
		}
		int lowerBoundWidth = x - radius - 1;
		if (lowerBoundWidth < 0) {
			lowerBoundWidth = 0;
		}
		int newWidth = upperBoundWidth - lowerBoundWidth;
		Tile[][] returningTiles = new Tile[newHeight][newWidth];
		MiniMap minimap = new MiniMap();
		int startingX = 0;
		int startingY = 0;
		for (int i = lowerBoundHeight; i != upperBoundHeight; ++i) {
			for (int j = lowerBoundWidth; j != upperBoundWidth; ++j) {
				if (i == y && j == x) {
					Position center = new Position(startingY, startingX);
					minimap.setCenter(center);
				}
				returningTiles[startingY][startingX] = tiles[i][j];
				++startingX;
			}
			startingX = 0;
			++startingY;
		}
		minimap.setTiles(returningTiles);
		return minimap;
	}

	public ArrayList<Npc> getListOfNpcs() {
		return listOfNpcs;
	}

}
