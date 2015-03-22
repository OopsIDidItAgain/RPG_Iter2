package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.items.ArmorTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EffectTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EquipableTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.InteractiveItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.ObstacleItem;
import com.oopsididitagain.rpg_iter2.models.items.OneShotItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.WeaponTakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class MapDatabase {
	private BufferedReader gridReader;
	private InputStream gridFileStream;
	private BufferedReader objectsReader;
	private InputStream objectsFileStream;
	//private List<PositionedGameObject> positionedGameObjects;
	private List<TiledEntityVisitable> tiledEntityVisitables;
	private List<TiledProbeVisitable> tiledProbeVisitables;
	private Tile[][] tiles;

    String[][] mapGrid;
    int mapX;
    int mapY;
	
	public MapDatabase(int level) {

		tiledEntityVisitables = new ArrayList<TiledEntityVisitable>();
		tiledProbeVisitables = new ArrayList<TiledProbeVisitable>();
		gridFileStream = getClass().getResourceAsStream("/levels/level"+level+"/grid.csv");
		objectsFileStream = getClass().getResourceAsStream("/levels/level"+level+"/tileables.csv");

		gridReader = new BufferedReader(new InputStreamReader(gridFileStream));
		objectsReader = new BufferedReader(new InputStreamReader(objectsFileStream));

        readTerrain();
        setTiles();
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
    
    public void setTiles() {
        int mapX = getMapX();
        int mapY = getMapY();
        this.tiles = new Tile[mapY][mapX];
        for(int i = 0; i<mapY; i++){
            for(int j=0;j<mapX;j++){
                switch (getTerrainAtYX(i,j)) {
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

    private String readTerrainLine() {
        return readNextLine(gridReader);
    }

    public String getTerrainAtYX(int y, int x){
        return mapGrid[y][x];
    }
    
    public List<TiledProbeVisitable> getProbeVisitables() {
    	return tiledProbeVisitables;
    }
    
    public List<TiledEntityVisitable> getEntityVisitables() {
    	return tiledEntityVisitables;
    }

    private void readObjects(){
    	String line;
    	while ((line = readObjectsLine()) != null) {
    		System.out.println(line);
    		Map<String, EffectTakeableItem> usableMap = new HashMap<String, EffectTakeableItem>();
    		Map<String, TakeableItem> unusableMap = new HashMap<String, TakeableItem>();
    		Map<String, EquipableTakeableItem> equipableMap = new HashMap<String, EquipableTakeableItem>();
    		
    		String[] tokens = line.split(",");
    		String id = tokens[0];
    		int x = Integer.parseInt(tokens[1]);
    		int y = Integer.parseInt(tokens[2]);
    		Position position = new Position(y, x);
    		String type = tokens[3];
    		PositionedGameObject positionedGameObject = null;
    		switch(type) {
    		case "EffectTakeableItem": 
    			tiledEntityVisitables.add(new EffectTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens)));
    			usableMap.put(id, (EffectTakeableItem) positionedGameObject);
    			break;
    		case "WeaponTakeableItem":
    			tiledEntityVisitables.add(new WeaponTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens), parseRank(tokens), parseWeaponType(tokens)));
    			equipableMap.put(id, (EquipableTakeableItem) positionedGameObject);
    			break;
    		case "ArmorTakeableItem":
    			tiledEntityVisitables.add(new ArmorTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens), parseRank(tokens), parseArmorType(tokens)));
    			equipableMap.put(id, (EquipableTakeableItem) positionedGameObject);
    			break;
    		case "OneShotItem":
    			tiledEntityVisitables.add(new OneShotItem(id, position, parseStatBlob(tokens)));
    			break;
    		case "TakeableItem":
    			tiledEntityVisitables.add(new TakeableItem(id, position, parsePrice(tokens)));
    			unusableMap.put(id, (TakeableItem) positionedGameObject);
    			break;
    		case "ObstacleItem":
    			tiledProbeVisitables.add(new ObstacleItem(id, position));
    			break;
    		case "InteractiveItem":
    			String requirementId = tokens[7];
    			if (usableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[position.getY()][position.getX()], parseTerrain(tokens), usableMap.get(requirementId).toInventoryItem()));
    			else if (equipableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[position.getY()][position.getX()], parseTerrain(tokens), equipableMap.get(requirementId).toInventoryItem()));
    			else if (unusableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[position.getY()][position.getX()], parseTerrain(tokens), (InventoryUnusableItem) unusableMap.get(requirementId).toInventoryItem()));
    			break;
    		}
    		if (positionedGameObject == null) {
    			System.out.println("NULL TILEABLE WHILE BUILDING MAP DATABASE");
    			continue;
    		}
    	}
    }
    
    private Terrain parseTerrain(String[] tokens) {
    	String type = tokens[6];
    	switch(type) {
    	case "GRASS": return Terrain.GRASS;
    	case "WATER": return Terrain.WATER;
    	case "MOUNTAIN": return Terrain.MOUNTAIN;
    	}
    	return Terrain.GRASS;
    }
    
    private ArmorItemType parseArmorType(String[] tokens) {
    	String type = tokens[15];
    	switch(type) {
    	case "ARMOR": return ArmorItemType.ARMOR;
    	case "BOOTS": return ArmorItemType.BOOTS;
    	case "HELMET": return ArmorItemType.HELMET;
    	}
    	return ArmorItemType.ARMOR;
    }

    private int parseRank(String[] tokens) {
    	return Integer.parseInt(tokens[14]);
    }

    private WeaponItemType parseWeaponType(String[] tokens) {
    	String type = tokens[15];
    	switch(type) {
    	case "ONE_HANDED_WEAPON": return WeaponItemType.ONE_HANDED_WEAPON;
    	case "TWO_HANDED_WEAPON": return WeaponItemType.TWO_HANDED_WEAPON;
    	case "FISTS": return WeaponItemType.FISTS;
    	case "RANGED_WEAPON": return WeaponItemType.RANGED_WEAPON;
    	case "STAFF": return WeaponItemType.STAFF;
    	}
    	return WeaponItemType.FISTS;
    }
    
    private double parsePrice(String[] tokens) {
    	return Double.parseDouble(tokens[13]);
    }

    private StatBlob parseStatBlob(String[] tokens) {
	    StatBlob statBlob = new StatBlob(Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), 
			    Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]), Integer.parseInt(tokens[8]),
			    Integer.parseInt(tokens[9]), Integer.parseInt(tokens[10]), Integer.parseInt(tokens[11]),
			    Integer.parseInt(tokens[12]));
	    return statBlob;
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
	
	public Tile[][] getTiles() {
		return tiles;
	}
}
