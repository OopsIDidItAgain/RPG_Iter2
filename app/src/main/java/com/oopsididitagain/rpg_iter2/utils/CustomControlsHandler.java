package com.oopsididitagain.rpg_iter2.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class CustomControlsHandler {
	
	private static HashMap<Integer, Command> controls;
	
	static {
		controls = new HashMap<Integer, Command>();
	}
	
	public static void bind(Integer key, Integer command) {
		bind(null, key, command);
	}
	
	public static void bind(Integer prevKey, Integer newKey, Integer command) {
		//TODO: add warning when trying to assign same key to two different commands
		//TODO: add removing new key from previous assignment in the view
		if(prevKey != null) {
			controls.remove(prevKey);
		}
		controls.remove(newKey);
	}
	
	public static Command getKeyboardKeyCommand(Integer key) {
		return controls.get(key);
	}
	
	public static void saveControls(HashMap<Integer, Command> controls, File file) {
		BufferedWriter writer = null;
		try {
			if(file.createNewFile()) {
				//for debugging purposes
			}
			
			writer = new BufferedWriter(new FileWriter(file));
			
			Iterator<Integer> iterator = controls.keySet().iterator();
			while(iterator.hasNext()) {
				Integer key = iterator.next();
				Command command = controls.get(key);
				writer.write(key + "," + command.toString() + "\n");
				iterator.remove();
			}
			writer.flush();
		} catch(IOException ioe) {
			System.err.println("Failed to save controls.");
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch(IOException ieo) {
				System.err.println("Error closing writer.");
			}
		}
	}
	
	public static HashMap<Integer, Command> loadControls(File file) {
		HashMap<Integer, Command> controls = new HashMap<Integer, Command>();
		
		BufferedReader reader = null;
		if(file.exists()) {
			//load saved controls
			try {
				reader = new BufferedReader(new FileReader(file));
				
				String line;
				while((line = reader.readLine()) != null) {
					String[] split = line.split(",");
					String enumerationId = split[1];
					controls.put(Integer.parseInt(split[0]), Command.fromString(enumerationId));
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
		
		return controls;
	}
	
	public static void setControls(HashMap<Integer, Command> newControls) {
		controls = newControls;
	}
	
}