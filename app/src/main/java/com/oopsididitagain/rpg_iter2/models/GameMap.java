package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

/*
 * It holds a 2d array of tiles.
 */
public class GameMap {
	private Tile[][] tiles;
	
	/*
	public GameMap(Tile[][] tiles) {
		this.tiles = tiles;
		this.HEIGHT = tiles.length;
		this.WIDTH = tiles[HEIGHT - 1].length;
	}*/
	
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
	
}
