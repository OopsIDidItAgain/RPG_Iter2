package com.oopsididitagain.rpg_iter2.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;

/**
 * Created by parango on 3/11/15.
 */
public class IOUtil {
	
	public static File saveMap(GameMap gameMap, int level) {
		StringBuilder sb = new StringBuilder("");
		sb.append(gameMap.getHeight() + "," + gameMap.getWidth() + "\n\n");
		for (int i = 0; i < gameMap.getHeight(); ++i) {
			for (int j = 0; j < gameMap.getWidth(); ++j) {
				Tile tile = gameMap.getTileAt(new Position(i, j));
				Terrain terrain = tile.getTerrain();
				switch(terrain.getId()) {
				case "M": sb.append("^");
				break;
				case "G": sb.append("-");
				break;
				case "W": sb.append("~");
				break;
				}
				if (j != gameMap.getWidth() - 1)
					sb.append(",");
			}
			sb.append("\n");
			sb.append(level);
			sb.append("\n");
		}
		File savedMap = new File(System.getProperty("user.home") + "/" + "SavedMap.csv");
		writeFile(sb.toString(), savedMap);
		return savedMap;
	}
	
	public static File saveOnMapItems(GameMap gameMap) {
		StringBuilder sb = new StringBuilder("");

		Queue<Entity> entities = new LinkedList<Entity>();
		for (int i = 0; i < gameMap.getHeight(); ++i) {
			for (int j = 0; j < gameMap.getWidth(); ++j) {
				Tile tile = gameMap.getTileAt(new Position(i, j));
				LinkedList<Tileable> tileables = tile.getTilebles();
				for (Tileable tileable: tileables) {
					try {
						PositionedGameObject positionedGameObject = (PositionedGameObject) tileable;
						if (positionedGameObject instanceof Entity) 
							entities.offer((Entity)positionedGameObject);
						else
							sb.append(positionedGameObject.toSaveableFormat() + "\n");
					} catch (Exception ex) {
						//ex.printStackTrace();
					}
				}
			}
		}
		sb.append("\n");
		for (Entity e: entities)
			sb.append(e.toSaveableFormat());
		
		File savedOnMapItems = new File(System.getProperty("user.home") + "/" + "SavedMapItems.csv");
		writeFile(sb.toString(), savedOnMapItems);
		return savedOnMapItems; 
	}
	
	private static void writeFile(String contents, File toFile) {
		try {
			FileWriter writer = new FileWriter(toFile);
			writer.write(contents);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String commaSeperate(String[] arr) {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < arr.length; ++i) {
			sb.append(arr[i]);
			if (i != arr.length - 1)
				sb.append(",");
		}
		return sb.toString();
	}

	public static int parseLevel(File grid) {
		int level = 0; 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(grid));
			String[] sizes = reader.readLine().split(",");
			int height = Integer.parseInt(sizes[0]);
			reader.readLine(); // Skip Empty Line after Sizes
			for (int i = 0; i < height; ++i)
				reader.readLine();
			level = Integer.parseInt(reader.readLine().split(",")[0]);
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return level;
		
	}
}
