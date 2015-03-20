package com.oopsididitagain.rpg_iter2.models;

public class MiniMap {
	Position center;
	Tile[][] tiles;
	public Position getCenter() {
		return center;
	}
	public void setCenter(Position center) {
		this.center = center;
	}
	public Tile[][] getTiles() {
		return tiles;
	}
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	public boolean inBounds(Position position) {
		int x = position.getX();
		int y = position.getY();
		if(x < 0 || x >= tiles[0].length){
			return false;
		}
		if(y < 0 || y >= tiles.length){
			return false;
		}
		return true;
	}
	public Tile getTile(Position position) {
		return tiles[position.getY()][position.getX()];
	}
}
