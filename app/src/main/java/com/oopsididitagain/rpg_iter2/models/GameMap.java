package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
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
	MapDatabase mapReader;
	
	GameMap(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	GameMap(String level) {
		mapReader = new MapDatabase(level);
	}
	
	// I believe it is better to have all the code for creating
	// the game map inside of GameMap. It just seems right from an MVC
	// perspective for the map to know how it is created -- thoughts? (Danny)
	public void initialize() {
		
		// RIGHT, so we're reading from 2 csv's!
		//
		//		1. one that has positions and terrains
		//		2. one that has positions and tileables (minus terrains)
		
		initializeGrid(); // this also initializes the map
		initializeObjects();
	}
	
	public void initializeGrid() {
		// initialize map!!
		String[] dimensions = mapReader.readGridLine().split(",");
		tiles = new Tile[Integer.parseInt(dimensions[1])] 
		                [Integer.parseInt(dimensions[0])];
		
		mapReader.readGridLine(); // blank line
		
		// go through the grid
		String s = mapReader.readGridLine();
		
		for (int i = 0; i < Integer.parseInt(dimensions[1]); i++) {
			s = mapReader.readGridLine();
			
			if (s.equals("")) {
				// default behavior if something's wrong with the map
				for (int j = 0; j < Integer.parseInt(dimensions[0]); j++)
					tiles[i][j] = new Tile(new Position(i,j), Terrain.GRASS);
				continue;
			}
			
			String[] array = s.split(",");
			
			for ( int j = 0; j < array.length; j++ ) {
				Position p = new Position(i,j);
				switch(array[j]) {
					case "^":
						tiles[i][j] = new Tile( p, Terrain.MOUNTAIN );
						break;
					case " ":
						tiles[i][j] = new Tile( p, Terrain.GRASS );
						break;
					case "~":
						tiles[i][j] = new Tile( p, Terrain.WATER );
						break;
					default:
						System.out.println("Error, unknown terrain type when reading map...");
				}
			}
		}
	}
	
	public void initializeObjects() {
		String s;
		while ((s = mapReader.readObjectsLine()).equals("")) {
			// String[] array = s.split(",");
			
			// to do:
			// decide how to figure out what kind of item it is
			// 
			// 		sample idea:
			// int x = Integer.parseInt(array[1]);
			// int y = Integer.parseInt(array[2]);
			// tiles[y][x] = new Item( array[0] );
		}
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
		if (	(pos.getY() >= getHeight()) ||
				(pos.getX() >= getWidth()) ||
				(pos.getY() < 0) ||
				(pos.getX() < 0))
					return false;
		
		return true;
	}
	
	
}
