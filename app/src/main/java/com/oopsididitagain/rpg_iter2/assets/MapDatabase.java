package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.AreaEffect;
import com.oopsididitagain.rpg_iter2.models.Decal;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.Trap;
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
import com.oopsididitagain.rpg_iter2.utils.AreaEffectType;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class MapDatabase {
	private BufferedReader gridReader;
	private InputStream gridFileStream;
	private BufferedReader objectsReader;
	private InputStream objectsFileStream;
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
	    Map<String, EffectTakeableItem> usableMap = new HashMap<String, EffectTakeableItem>();
	    Map<String, TakeableItem> unusableMap = new HashMap<String, TakeableItem>();
	    Map<String, EquipableTakeableItem> equipableMap = new HashMap<String, EquipableTakeableItem>();
    		
    	while ((line = readObjectsLine()) != null) {
    		System.out.println(line);
    		String[] tokens = line.split(",");
    		String id = tokens[0];
    		int x = Integer.parseInt(tokens[1]);
    		int y = Integer.parseInt(tokens[2]);
    		Position position = new Position(y, x);
    		String type = tokens[3];
    		switch(type) {
    		case "EffectTakeableItem": 
    			EffectTakeableItem eitem = new EffectTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens));
				tiledEntityVisitables.add(eitem);
    			usableMap.put(id, eitem);
    			break;
    		case "WeaponTakeableItem":
    			WeaponTakeableItem witem = new WeaponTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens), parseRank(tokens), parseWeaponType(tokens));
				tiledEntityVisitables.add(witem);
    			equipableMap.put(id, witem);
    			break;
    		case "ArmorTakeableItem":
    			ArmorTakeableItem aitem = new ArmorTakeableItem(id, position, parsePrice(tokens), parseStatBlob(tokens), parseRank(tokens), parseArmorType(tokens));
				tiledEntityVisitables.add(aitem);
    			equipableMap.put(id, aitem);
    			break;
    		case "OneShotItem":
    			tiledEntityVisitables.add(new OneShotItem(id, position, parseStatBlob(tokens)));
    			break;
    		case "TakeableItem":
    			TakeableItem titem = new TakeableItem(id, position, parsePrice(tokens));
    			tiledEntityVisitables.add(new TakeableItem(id, position, parsePrice(tokens)));
    			unusableMap.put(id, titem);
    			break;
    		case "ObstacleItem":
    			tiledProbeVisitables.add(new ObstacleItem(id, position));
    			break;
    		case "InteractiveItem":
    			String requirementId = tokens[7];
    			Position targetPosition = new Position(Integer.parseInt(tokens[5]), Integer.parseInt(tokens[4]));
    			if (usableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[targetPosition.getY()][targetPosition.getX()], parseTerrain(tokens), usableMap.get(requirementId).toInventoryItem()));
    			else if (equipableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[targetPosition.getY()][targetPosition.getX()], parseTerrain(tokens), equipableMap.get(requirementId).toInventoryItem()));
    			else if (unusableMap.containsKey(requirementId))
				    tiledEntityVisitables.add(new InteractiveItem(id, position, tiles[targetPosition.getY()][targetPosition.getX()], parseTerrain(tokens), (InventoryUnusableItem) unusableMap.get(requirementId).toInventoryItem()));
    			break;
    		case "AreaEffect":
    			AreaEffectType aetype = parseAreaEffectType(tokens);
    			Decal decal = parseDecal(aetype);
    			tiledEntityVisitables.add(new AreaEffect(decal, position, aetype));
    			break;
    		case "Trap":
    			Trap trap = new Trap(id, position);
    			tiledEntityVisitables.add(trap);
    			break;
    		}
    	}
    }
    
    private Decal parseDecal(AreaEffectType type) {
    	switch (type) {
		case HEAL_DAMAGE:
			return new Decal("heal_damage_decal");
		case INSTANT_DEATH:
			return new Decal("instant_death_decal");
		case LEVEL_UP:
			return new Decal("level_up_decal");
		case TAKE_DAMAGE:
			return new Decal("take_damage_decal");
    	}
		return new Decal("heart_decal");
    }
    
    private AreaEffectType parseAreaEffectType(String[] tokens) {
    	switch(tokens[4]) {
    	case "LEVEL_UP": return AreaEffectType.LEVEL_UP;
    	case "HEAL_DAMAGE": return AreaEffectType.HEAL_DAMAGE;
    	case "TAKE_DAMAGE": return AreaEffectType.TAKE_DAMAGE;
    	case "INSTANT_DEATH": return AreaEffectType.INSTANT_DEATH;
    	}
    	return AreaEffectType.LEVEL_UP;
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
