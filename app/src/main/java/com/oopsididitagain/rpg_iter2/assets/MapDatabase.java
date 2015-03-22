package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.oopsididitagain.rpg_iter2.models.Terrain;

public class MapDatabase {
	private BufferedReader gridReader;
	private InputStream gridFileStream;
	
	private BufferedReader objectsReader;
	private InputStream objectsFileStream;

    String[][] mapGrid;
    int mapX;
    int mapY;
	
	public MapDatabase(int level) {

		gridFileStream = getClass().getResourceAsStream("/levels/level"+level+"/grid.csv");
		objectsFileStream = getClass().getResourceAsStream("/levels/level"+level+"/tileables.csv");

		gridReader = new BufferedReader(new InputStreamReader(gridFileStream));
		objectsReader = new BufferedReader(new InputStreamReader(gridFileStream));

        readTerrain();
        readObjects();
	}

    public int getMapY() {
        return mapY;
    }

    public int getMapX() {
        return mapX;
    }

    public String getObjectsAtYX(int y, int x){

        return mapGrid[y][x];
    }

    private void readTerrain(){

        String[] dimensions = readTerrainLine().split(",");
        mapX = Integer.parseInt(dimensions[0]);
        mapY = Integer.parseInt(dimensions[1]);

        mapGrid = new String[mapY][mapX];

        readTerrainLine(); // blank line

        String s;

        for (int i = 0; i < mapY/* y */; i++) {
            s = readTerrainLine();
            System.out.println(s);

            if (s.equals("")) {
                // default behavior if something's wrong with the map
                for (int j = 0; j < mapX; j++) {
                    System.out.println("Map did not load properly");
                    mapGrid[i][j] = Terrain.GRASS.getId();
                }
                continue;
            }

            String[] array = s.split(",");
            for ( int j = 0; j < mapX; j++ ) {

                switch(array[j]) {
                    case "^":
                        mapGrid[i][j] = "M";
                        break;
                    case "-":
                        mapGrid[i][j] = "G" ;
                        break;
                    case "~":
                        mapGrid[i][j] = "W";
                        break;
                    default:
                        System.out.println("Error, unknown terrain type when reading map...");
                        break;
                }
            }
        }


    }

    private String readTerrainLine() {
        return readNextLine(gridReader);
    }

    public String getTerrainAtYX(int y, int x){

        return mapGrid[y][x];
    }

    private void readObjects(){
        //TODO
        String s;
        //while ((s = readObjectsLine()).equals("")) {
            // String[] array = s.split(",");

            // to do:
            // decide how to figure out what kind of item it is
            //
            // 		sample idea:
            // int x = Integer.parseInt(array[1]);
            // int y = Integer.parseInt(array[2]);
            // tiles[y][x] = new Item( array[0] );
       // }

    }


	
	private String readObjectsLine() {
		return readNextLine(objectsReader);
	}
	
	private String readNextLine(BufferedReader br) {
		try {
			return br.readLine();
		} catch (IOException e) {
			System.out.println("Reading line didn't work....");
			e.printStackTrace();
			return null;
		}
	}
}
