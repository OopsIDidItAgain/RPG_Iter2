package com.oopsididitagain.rpg_iter2.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedSet;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;

/**
 * Created by parango on 3/11/15.
 */
public class IOUtil {
	
	public static File saveMap(GameMap gameMap) {
		// Check with Tess if this will work.
		gameMap = new GameMap(new MapDatabase(1));
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
		}
		File savedMap = new File(System.getProperty("user.home") + "/" + "SavedMap.csv");
		writeFile(sb.toString(), savedMap);
		return savedMap;
	}
	
	public static File saveOnMapItems(GameMap gameMap) {
		gameMap = new GameMap(new MapDatabase(1));
		StringBuilder sb = new StringBuilder("");

		for (int i = 0; i < gameMap.getHeight(); ++i) {
			for (int j = 0; j < gameMap.getWidth(); ++j) {
				Tile tile = gameMap.getTileAt(new Position(i, j));
				SortedSet<Tileable> tileables = tile.getTilebles();
				for (Tileable tileable: tileables) {
					PositionedGameObject positionedGameObject = (PositionedGameObject) tileable;
					sb.append(positionedGameObject.toSaveableFormat() + "\n");
				}
			}
		}
		
		File savedOnMapItems = new File(System.getProperty("user.home") + "/" + "SavedMapItems.csv");
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
}
