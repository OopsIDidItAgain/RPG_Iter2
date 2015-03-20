package com.oopsididitagain.rpg_iter2.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class CustomControls {
	
	private static HashMap<Integer, Integer> customControls;
	private static final String FILE_PATH_NAME = "src/controls/controls.csv";
	
	static {
		customControls = new HashMap<Integer, Integer>();
		loadDefaultControls();
	}
	
	public static void bind(Integer key, Integer command) {
		customControls.remove(key);
		customControls.put(key, command);
	}
	
	public static Integer getKeyboardKeyCommand(Integer key) {
		return customControls.get(key);
	}
	
	public static void saveControls() {
		File file = new File(FILE_PATH_NAME);
		BufferedWriter writer = null;
		try {
			if(file.createNewFile()) {
				//for debugging purposes
			}
			
			writer = new BufferedWriter(new FileWriter(file));
			
			Iterator<Integer> iterator = customControls.keySet().iterator();
			while(iterator.hasNext()) {
				Integer key = iterator.next();
				Integer command = customControls.get(key);
				writer.write(key + "," + command + "\n");
				iterator.remove();
			}
			writer.flush();
		} catch(IOException ioe) {
			
		} finally {
			
		}
	}
	
	public static void loadControls(boolean loadCustomControls) {
		loadDefaultControls();
		if(loadCustomControls) {
			File file = new File(FILE_PATH_NAME);
			BufferedReader reader = null;
			if(file.exists()) {
				//load saved controls
				try {
					reader = new BufferedReader(new FileReader(file));
					
					String line;
					while((line = reader.readLine()) != null) {
						String[] split = line.split(",");
						bind(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
					}
				} catch(IOException ioe) {
					System.err.println("Error loading custom controls");
					ioe.printStackTrace();
				} finally {
					try {
						if(reader != null) {
							reader.close();
						}
					} catch(IOException ioe) {
						System.err.println("Error closing BufferedReader");
						ioe.printStackTrace();
					}
				}
			}
		}
	}
	
	private static void loadDefaultControls() {
		//direction commands
		bind(String.valueOf('q').codePointAt(0), Commands.MOVE_NORTH_WEST);
		bind(String.valueOf('w').codePointAt(0), Commands.MOVE_NORTH);
		bind(String.valueOf('e').codePointAt(0), Commands.MOVE_NORTH_EAST);
		bind(String.valueOf('a').codePointAt(0), Commands.MOVE_WEST);
		bind(String.valueOf('d').codePointAt(0), Commands.MOVE_EAST);
		bind(String.valueOf('z').codePointAt(0), Commands.MOVE_SOUTH_WEST);
		bind(String.valueOf('x').codePointAt(0), Commands.MOVE_SOUTH);
		bind(String.valueOf('c').codePointAt(0), Commands.MOVE_SOUTH_EAST);
		
		//Game Controls
		bind(String.valueOf('i').codePointAt(0), Commands.INVENTORY);
		bind(String.valueOf('p').codePointAt(0), Commands.PAUSE);
		bind(String.valueOf('`').codePointAt(0), Commands.EXIT);
		bind(String.valueOf('\r').codePointAt(0), Commands.ENTER);
		bind(String.valueOf('m').codePointAt(0), Commands.DROP);
		bind(String.valueOf('u').codePointAt(0), Commands.USE);
		bind(String.valueOf('h').codePointAt(0), Commands.EQUIP);
	}
	
}