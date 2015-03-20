package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.models.items.Item;
import com.oopsididitagain.rpg_iter2.models.items.ObstacleItem;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;

/*
 * It holds a 2d array of tiles.
 */
public class GameMap {
	Tile[][] tiles;
	// i don't think we need height or width attributes
	
	GameMap(Tile[][] tiles) {
		
		this.tiles = tiles;
	}
	
	public int getHeight() {
		return (tiles == null) ? 0 : tiles.length;
	}
	
	public int getWidth() {
		return (tiles == null) ? 0 : tiles[0].length;
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public Tile getTileAt(int y, int x){
		return tiles[y][x];
	}
	
	public Tile getTileAt(Position pos) {
		return tiles[pos.getY()][pos.getX()];
	}
	

	public boolean checkIfValid(Position pos) {
		if (	(pos.getY() >= getHeight() ||
				(pos.getX() >= getWidth()) ||
				(pos.getY() < 0) ||
				(pos.getX() < 0)))
					return false;
		
		return true;
	}
	
	
}
