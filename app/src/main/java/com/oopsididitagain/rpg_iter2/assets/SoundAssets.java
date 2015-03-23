package com.oopsididitagain.rpg_iter2.assets;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class SoundAssets {


	private static HashMap<String, String> paths; //sound id ->
    private static HashMap<String, Clip> sounds; //sound id -> sound
    private static Clip clip;
	
	static {
		paths = new HashMap<>();
		sounds = new HashMap<>();

		loadPaths();
		loadSounds();
	}
	
	private static void loadPaths() {
		InputStream soundIdToPathReader = SoundAssets.class.getClass().getResourceAsStream("/assets/SoundIDsAndPaths.csv");
		
		try {
			BufferedReader pathReader = new BufferedReader(new InputStreamReader(soundIdToPathReader));

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
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(SoundAssets.class.getResourceAsStream(getPath(soundID))
                        ));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                sounds.put(soundID,clip);

				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			iterator.remove();
		}
	}

    public static void playSound(String soundID){
        Clip clip = sounds.get(soundID);
        stopSound(soundID);

        try {
            System.out.println("Play " + soundID);
            clip.setFramePosition(0);
            clip.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void playLooped(String soundID){
        Clip clip = sounds.get(soundID);
        stopSound(soundID);

        try {
            System.out.println("Play " + soundID);
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void stopSound(String soundID){
        Clip clip = sounds.get(soundID);
        if(clip.isRunning()) {
            clip.stop();

        }
    }
	
	public static String getPath(String soundID) {
		return paths.get(soundID);
	}
	
}