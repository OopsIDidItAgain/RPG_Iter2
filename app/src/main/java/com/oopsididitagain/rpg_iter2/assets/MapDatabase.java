package com.oopsididitagain.rpg_iter2.assets;

import com.oopsididitagain.rpg_iter2.models.Terrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapDatabase {
	BufferedReader gridReader;
	File gridFile;
	
	BufferedReader objectsReader;
	File objectsFile;
    String[][] grid;
    int mapX;
    int mapY;
	
	public MapDatabase(String filepath) {
		gridFile = new File(getClass().getResource("/levels/"+filepath+"/grid.csv").getPath());
		objectsFile = new File(getClass().getResource("/levels/"+filepath+"/tileables.csv").getPath());
		
		try { 
			gridReader = new BufferedReader(new FileReader(gridFile));
			objectsReader = new BufferedReader(new FileReader(objectsFile));
		} catch (FileNotFoundException e) {
			System.out.println("Linking files to readers did not work....");
			e.printStackTrace();
		}

        readTerrain();
        readObjects();


	}

    public int getMapY() {
        return mapY;
    }

    public int getMapX() {
        return mapX;
    }

    private void readTerrain(){

        String[] dimensions = readTerrainLine().split(",");
        mapX = Integer.parseInt(dimensions[0]);
        mapY = Integer.parseInt(dimensions[1]);

        grid = new String[mapY][mapX];

        readTerrainLine(); // blank line

        String s;

        for (int i = 0; i < mapY/* y */; i++) {
            s = readTerrainLine();
            System.out.println(s);

            if (s.equals("")) {
                // default behavior if something's wrong with the map
                for (int j = 0; j < mapX; j++) {
                    System.out.println("Map did not load properly");
                    grid[i][j] = Terrain.GRASS.getId();
                }
                continue;
            }

            String[] array = s.split(",");
            for ( int j = 0; j < mapX; j++ ) {

                switch(array[j]) {
                    case "^":
                        grid[i][j] = "M";
                        break;
                    case "-":
                        grid[i][j] = "G" ;
                        break;
                    case "~":
                        grid[i][j] = "W";
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

        return grid[y][x];
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
