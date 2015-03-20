package com.oopsididitagain.rpg_iter2.assets;

import java.util.HashMap;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


// this "Assets" package will hold objects of all of our resources 
// to be used in the game.
//
// potential resources include:
// - pictures (decals, tiles, entities, items)
// - spreadsheets (saving + loading game)
// - sounds (maybe)
// - animations (maybe)


public class Assets extends Panel {
	// static BufferedImage b; // for testing purposes
	
	static String imgIDtoPathFile = "src/Assets/ImageIDsAndPaths.csv";

	static HashMap<String, String> imgToPath; // image id -> path
	static HashMap<String, BufferedImage> images; // game object id -> image id
	
	public Assets() {
		imgToPath = new HashMap<String, String>();
		images = new HashMap<String, BufferedImage>();
		initialize();
		
	}
	
	public void initialize(){
		// start it by populating it fully
		
		try {
			BufferedReader 	imgToPathReader = new BufferedReader(new FileReader(imgIDtoPathFile));
			
			imgToPathReader.readLine(); // skip first line
			
			String line;
			while ((line = imgToPathReader.readLine()) != null) {
				String[] split = line.split(",");
				imgToPath.put(split[0], split[1]);
			}
			
			imgToPathReader.close();
		}
		catch (IOException ex) {
			System.out.println("Your images failed to load");
			ex.printStackTrace();
		}
		createImages();
	}
	
	private void createImages(){
		String s = "avatar";
		File f = new File(getPath(s));
		try {
			BufferedImage buff = (BufferedImage)ImageIO.read(f);
			images.put(s,buff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Image failed to load");
			e.printStackTrace();
		} 
	}
	public BufferedImage getBufferedImage(String gameObjID) {
		return images.get(gameObjID);
		
	}
	
	public String getPath(String gameObjID) {
		return 	imgToPath.get(gameObjID);
	}
}
