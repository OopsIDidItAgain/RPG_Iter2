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
	static String gameIDtoimgIDFile = "src/Assets/GameIDAndImageID.csv";

	static HashMap<Integer, String> imgToPath; // image id -> path
	static HashMap<Integer, Integer> objToImg; // game object id -> image id
	static HashMap<Integer, BufferedImage> images; // game object id -> image id
	
	public Assets() {
		imgToPath = new HashMap<Integer, String>();
		objToImg = new HashMap<Integer, Integer>();
		images = new HashMap<Integer, BufferedImage>();
		initialize();
		
	}
	
	public void initialize(){
		// start it by populating it fully
		
		try {
			BufferedReader 	imgToPathReader = new BufferedReader(new FileReader(imgIDtoPathFile)),
							objToImgReader = new BufferedReader(new FileReader(gameIDtoimgIDFile));
			
			imgToPathReader.readLine(); // skip first line
			objToImgReader.readLine();  // skip first line
			
			String line;
			while ((line = imgToPathReader.readLine()) != null) {
				String[] split = line.split(",");
				int id = Integer.parseInt(split[0]);
				imgToPath.put(id, split[1]);
			}
			
			while ((line = objToImgReader.readLine()) != null) {
				String[] split = line.split(",");
				int objID = Integer.parseInt(split[0]);
				int imgID = Integer.parseInt(split[1]);
				objToImg.put(objID, imgID);
			}
			
			imgToPathReader.close();
			objToImgReader.close();
		}
		catch (IOException ex) {
			System.out.println("Your images failed to load");
			ex.printStackTrace();
		}
		createImages();
	}
	
	private void createImages(){
		for(int i = 0; i!= 2; ++i){
		
			File f = new File("src"+getPath(i));
			try {
				BufferedImage buff = (BufferedImage)ImageIO.read(f);
				images.put(i,buff);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Image failed to load");
				e.printStackTrace();
			} 
		}
	}
	public BufferedImage getBufferedImage(int gameObjID) {
		return images.get(gameObjID);
		
	}
	
	public String getPath(int gameObjID) {
		return 	imgToPath.get(
					objToImg.get(gameObjID));
	}
	
	
	
	
	
	/* --------------------
	 * FOR TESTNG PURPOSES
	 * -------------------- */
	
	/*public static void main(String[] args) {
		// create
		Assets a = new Assets();
		a.initialize();
		
		// display its name
		int rando = (int)(Math.random() * 10);
		String path = a.getPath(rando);
		System.out.println(a.getPath(rando));
		
		// display the picture
		
		JFrame frame = new JFrame("ShowImage");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);

		// b = getBufferedImage(rando);
		frame.setContentPane(a); 
		frame.setVisible(true); 
	}
	
	public void paint(Graphics g) {
	  g.drawImage( b, 0, 0, null);// buffered image
	}*/
}
