package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapDatabase {
	BufferedReader gridReader;
	File gridFile;
	
	BufferedReader objectsReader;
	File objectsFile;
	
	public MapDatabase(String filepath) {
		gridFile = new File(getClass().getResource("/levels/"+filepath+"/grid.csv").getPath());
		objectsFile = new File(getClass().getResource("levels/"+filepath+"/tileables.csv").getPath());
		
		try { 
			gridReader = new BufferedReader(new FileReader(gridFile));
			objectsReader = new BufferedReader(new FileReader(objectsFile));
		} catch (FileNotFoundException e) {
			System.out.println("Linking files to readers did not work....");
			e.printStackTrace();
		}
	}
	
	public String readGridLine() {
		return readNextLine(gridReader);
	}
	
	public String readObjectsLine() {
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
}
