package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;


/*
 * It holds a 2d array of tiles.
 */
public class GameMap {
	private Tile[][] tiles;
	private final int HEIGHT; 
	private final int WIDTH;
	
	public GameMap(Tile[][] tiles) {
		this.tiles = tiles;
		this.HEIGHT = tiles.length;
		this.WIDTH = tiles[HEIGHT - 1].length;
	}
	
	public Tile getTileAt(Position position) throws PositionOutOfBoundsException {
		position.checkBounds(WIDTH, HEIGHT);
		return tiles[position.getY()][position.getX()];
	}
	
}
