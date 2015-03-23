package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SoundAssets {

	private static InputStream soundIdToPathReader;
	private static HashMap<String, String> paths; //sound id -> path
	private static HashMap<String, byte[]> sounds; //sound id -> byte data
	private static HashMap<String, AudioFormat> formats; //sound id -> sound format
	private static Clip currentBgClip = null;
	
	static {
		paths = new HashMap<String, String>();
		sounds = new HashMap<String, byte[]>();
		formats = new HashMap<String, AudioFormat>();
		loadPaths();
		loadSounds();
	}
	
	private static void loadPaths() {
		soundIdToPathReader = SoundAssets.class.getClass().getResourceAsStream("/assets/SoundIDsAndPaths.csv");
		
		try {
			BufferedReader 	pathReader = new BufferedReader(new InputStreamReader(soundIdToPathReader));

            pathReader.readLine(); // skip first line
			
			String line;
			while ((line = pathReader.readLine()) != null) {

				String[] split = line.split(",");
				paths.put(split[0], split[1]);
			}

            pathReader.close();
		} catch (IOException ex) {
			System.out.println("Your sounds failed to load");
			ex.printStackTrace();
		}
	}
	
	private static void loadSounds() {
		Iterator<String> iterator = paths.keySet().iterator();
		while(iterator.hasNext()) {
			String soundID = iterator.next();
			System.out.println(SoundAssets.class.getResource(getPath(soundID)));
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( SoundAssets.class.getResource(getPath(soundID)) );
				byte[] buffer = new byte[32 * 1024];
				int read = 0;
				ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.length);
				while((read = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
					baos.write(buffer, 0, read);
				}
				sounds.put(soundID, baos.toByteArray());
				formats.put(soundID, audioInputStream.getFormat());
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			iterator.remove();
		}
	}
	
	public void playClip(String soundID) {
		byte[] data = sounds.get(soundID);
		AudioFormat format = formats.get(soundID);
		
		AudioInputStream audioInputStream;
		try {
			audioInputStream = new AudioInputStream(new ByteArrayInputStream(data), format, AudioSystem.NOT_SPECIFIED);
			audioInputStream.reset();
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void playBgClip(String soundID) {
		byte[] data = sounds.get(soundID);
		AudioFormat format = formats.get(soundID);
		
		AudioInputStream audioInputStream;
		try {
			audioInputStream = new AudioInputStream(new ByteArrayInputStream(data), format, AudioSystem.NOT_SPECIFIED);
			audioInputStream.reset();
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			if(!clip.equals(currentBgClip)) {
				currentBgClip = clip;
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopClip(String soundID) {
		byte[] data = sounds.get(soundID);
		AudioFormat format = formats.get(soundID);
		
		AudioInputStream audioInputStream;
		try {
			audioInputStream = new AudioInputStream(new ByteArrayInputStream(data), format, AudioSystem.NOT_SPECIFIED);
			audioInputStream.reset();
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.stop();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopBgClip() {
		if(currentBgClip != null) {
			currentBgClip.stop();
			currentBgClip = null;
		}
	}
	
	public void loopClip(String soundID, int cycles) {
		//sounds.get(soundID).loop(cycles);
	}
	
	public void loopClipContinuously(String soundID) {
		byte[] data = sounds.get(soundID);
		AudioFormat format = formats.get(soundID);
		
		AudioInputStream audioInputStream;
		try {
			audioInputStream = new AudioInputStream(new ByteArrayInputStream(data), format, AudioSystem.NOT_SPECIFIED);
			audioInputStream.reset();
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getPath(String soundID) {
		return paths.get(soundID);
	}
	
}