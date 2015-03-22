package com.oopsididitagain.rpg_iter2.assets;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


// this "assets" package will hold objects of all of our resources
// to be used in the game.
//
// potential resources include:
// - pictures (decals, tiles, entities, items)
// - spreadsheets (saving + loading game)
// - sounds (maybe)
// - animations (maybe)


public  class Assets {

	private static InputStream imgIDToPathFilenameReader;
	private static HashMap<String, String> paths; // image id -> path
	private static HashMap<String, Image> images;  // game object id -> image id
	static {
		paths = new HashMap<String, String>(); 
		images = new HashMap<String, Image>();
		loadPaths();
        loadImages();
	}
	
	public Assets() { }
	
	private static void loadPaths(){

        imgIDToPathFilenameReader =  Assets.class.getClass().getResourceAsStream("/assets/ImageIDsAndPaths.csv");
		// start it by populating it fully
		
		try {
			BufferedReader 	pathReader = new BufferedReader(new InputStreamReader(imgIDToPathFilenameReader));

            pathReader.readLine(); // skip first line
			
			String line;
			while ((line = pathReader.readLine()) != null) {

				String[] split = line.split(",");
				paths.put(split[0], split[1]);
			}

            pathReader.close();
		}
		catch (IOException ex) {
			System.out.println("Your images failed to load");
			ex.printStackTrace();
		}
	}
	
	private static void loadImages(){
		String s = "avatar";
		Image image = Toolkit.getDefaultToolkit().getImage(Assets.class.getResource(getPath(s)));
		images.put(s,image);
	}

	public Image getImage(String gameObjID) {
        if(!images.containsKey(gameObjID)){
            System.out.println("loading"+gameObjID);
            Image image = Toolkit.getDefaultToolkit().getImage(Assets.class.getResource(getPath(gameObjID)));
            images.put(gameObjID, image);
        }
        return images.get(gameObjID);
	}
	
	public static String getPath(String gameObjID) {
        return paths.get(gameObjID);
	}
}
