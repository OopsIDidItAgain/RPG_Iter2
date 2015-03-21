package com.oopsididitagain.rpg_iter2.assets;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


// this "assets" package will hold objects of all of our resources
// to be used in the game.
//
// potential resources include:
// - pictures (decals, tiles, entities, items)
// - spreadsheets (saving + loading game)
// - sounds (maybe)
// - animations (maybe)


public  class Assets {

	private static String imgIDtoPathFile;
	private static HashMap<String, String> paths; // image id -> path
	private static HashMap<String, BufferedImage> images;  // game object id -> image id
	static {
		paths = new HashMap<String, String>(); 
		images = new HashMap<String, BufferedImage>();
		loadPaths();
        loadImages();
	}
	
	public Assets() { }
	
	private static void loadPaths(){

        imgIDtoPathFile =  Assets.class.getClass().getResource("/assets/ImageIDsAndPaths.csv").getPath();
		// start it by populating it fully
		
		try {
			BufferedReader 	pathReader = new BufferedReader(new FileReader(imgIDtoPathFile));

            pathReader.readLine(); // skip first line
			
			String line;
			while ((line = pathReader.readLine()) != null) {

				String[] split = line.split(",");
                System.out.println(split[1]);
                System.out.println(Assets.class.getClass().getResource(split[1]).getPath());
				paths.put(split[0], Assets.class.getClass().getResource(split[1]).getPath());
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
		File f = new File(getPath(s));
		try {
			BufferedImage buff = ImageIO.read(f);
			images.put(s,buff);
		} catch (IOException e) {
			System.out.println("Image failed to load");
			e.printStackTrace();
		}
	}

	public BufferedImage getBufferedImage(String gameObjID) {
        if(!images.containsKey(gameObjID)){
            System.out.println("loading"+gameObjID);
            File f = new File(getPath(gameObjID));
            try {
                BufferedImage buff = ImageIO.read(f);
                images.put(gameObjID, buff);
            } catch (IOException e) {
                System.out.println("Image failed to load");
                e.printStackTrace();
            }
        }
        return images.get(gameObjID);
	}
	
	public static String getPath(String gameObjID) {
        return paths.get(gameObjID);
	}
}
