package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

/*
 * It holds a 2d array of tiles.
 */
public class GameMap {
	private Tile[][] tiles;
	
	
	public GameMap(Tile[][] tiles) {
		this.tiles = tiles;
		//this.HEIGHT = tiles.length;
		//this.WIDTH = tiles[HEIGHT - 1].length;
	}
	
	public Tile getTileAt(Position position) throws PositionOutOfBoundsException {
		position.checkBounds(0, 0, getWidth(), getHeight());
		return tiles[position.getY()][position.getX()];
	}

    @Deprecated /*ONLY USE FOR DEBUGGING PURPOSES*/
    public GameMap (int y, int x){
        tiles = new Tile[y][x];

        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                tiles[i][j]= new Tile(new Position(i,j),Terrain.GRASS);
            }
        }
    }

    public GameMap(MapDatabase mDb){

        readObjectsFromMapDB(mDb);
        readTerrainsFromMapDB(mDb);

    }

    private void readTerrainsFromMapDB(MapDatabase mDb){

        int mapX = mDb.getMapX();
        int mapY = mDb.getMapY();
        tiles = new Tile[mapY][mapX];
        for(int i = 0; i<mapY; i++){
            for(int j=0;j<mapX;j++){
                switch (mDb.getTerrainAtYX(i,j)) {
                    case "G":
                        tiles[i][j] = new Tile(new Position(i, j), Terrain.GRASS);
                        break;
                    case "M":
                        tiles[i][j] = new Tile(new Position(i, j), Terrain.MOUNTAIN);
                        break;
                    case "W":
                        tiles[i][j] = new Tile(new Position(i, j), Terrain.WATER);
                        break;
                    default:
                        tiles[i][j] = new Tile(new Position(i, j), Terrain.GRASS);
                        break;
                }
            }
        }


    }

    private void readObjectsFromMapDB(MapDatabase mDb){
        //TODO
    }


	
	public int getHeight() {
		return (tiles == null) ? 0 : tiles.length;
	}
	
	public int getWidth() {
		return (tiles == null) ? 0 : tiles[0].length;
	}
	
	public MiniMap getTiles(int y, int x, int radius) {
	
		int upperBoundHeight = y + radius;
		if(upperBoundHeight > tiles.length){
			upperBoundHeight = tiles.length;
		}
		int lowerBoundHeight = y - radius;
		if(lowerBoundHeight < 0){
			lowerBoundHeight = 0;
		}
		int newHeight = upperBoundHeight - lowerBoundHeight;
		
		int upperBoundWidth = x + radius;
		if(upperBoundWidth > tiles[0].length){
			upperBoundWidth = tiles[0].length;
		}
		int lowerBoundWidth = x - radius;
		if(lowerBoundWidth < 0){
			lowerBoundWidth = 0;
		}
		int newWidth = upperBoundWidth - lowerBoundWidth;
		Tile[][] returningTiles = new Tile[newHeight][newWidth];
		MiniMap minimap = new MiniMap();
		int startingX = 0;
		int startingY = 0;
		for(int i =lowerBoundHeight; i != upperBoundHeight; ++i){
			for(int j = lowerBoundWidth; j != upperBoundWidth; ++j){
				if(i == y && j == x){
					Position center = new Position(startingY,startingY);
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
	
}
