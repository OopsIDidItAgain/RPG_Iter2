package com.oopsididitagain.rpg_iter2.utils;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomControlsHandler {

	private HashMap<Integer, Command> controls;
	private HashMap<Integer, Command> modifiedControls;

	private static CustomControlsHandler instance;

	private CustomControlsHandler() {
		controls = new HashMap<Integer, Command>();
		// Initialize with defaults
		controls.put(87, Command.MOVE_NORTH); // w
		controls.put(88, Command.MOVE_SOUTH); // x
		controls.put(68, Command.MOVE_EAST); // d
		controls.put(65, Command.MOVE_WEST); // a
		controls.put(69, Command.MOVE_NORTHEAST); // e
		controls.put(81, Command.MOVE_NORTHWEST); // q
		controls.put(67, Command.MOVE_SOUTHEAST); // c
		controls.put(90, Command.MOVE_SOUTHWEST);
		controls.put(80, Command.PAUSE);
		controls.put(10, Command.ENTER);
		controls.put(73, Command.INVENTORY);
		controls.put(83, Command.USE);
		controls.put(74, Command.EQUIP);
		controls.put(75, Command.DROP);
		controls.put(27, Command.EXIT);
		controls.put(49, Command.SKILLONE);
		controls.put(50, Command.SKILLTWO);
		controls.put(51, Command.SKILLTHREE);
		controls.put(52, Command.SKILLFOUR);
		controls.put(53, Command.SKILLFIVE);
		controls.put(54, Command.SKILLSIX);
		controls.put(77, Command.SKILLALLOCATION);
		saveOldControls();


	}

	public static CustomControlsHandler getInstance() {
		if (instance == null)
			instance = new CustomControlsHandler();
		return instance;
	}

	public void saveOldControls() {
		if (modifiedControls == null) {
			modifiedControls = new HashMap<Integer, Command>();
			for (Map.Entry<Integer, Command> entry : controls.entrySet()) {
				modifiedControls.put(entry.getKey(), entry.getValue());
			}
		}
	}

	public void acceptNewControls() {
		if (modifiedControls != null) {
			controls = new HashMap<Integer, Command>();
			for (Map.Entry<Integer, Command> entry : modifiedControls
					.entrySet()) {
				controls.put(entry.getKey(), entry.getValue());
			}
			modifiedControls = null;
		}
	}

	public void bind(Integer key, Command command) {
		if (modifiedControls.containsKey(key)) {
			// key has a command, collision issue
			// remove the old key-command pair
			modifiedControls.remove(key);
			// add new key-command pair
			modifiedControls.put(key, command);
		} else {
			// key is free bind command to key
			// find command, remove key from command
			int prevKey = getKeyboardKey(command);
			if (prevKey != -1)
				modifiedControls.remove(prevKey);
			// put new key with command
			modifiedControls.put(key, command);
		}
	}

	//
	// public static void bind(Integer prevKey, Integer newKey, Command command)
	// {
	// //TODO: add warning when trying to assign same key to two different
	// commands
	// //TODO: add removing new key from previous assignment in the view
	// if(prevKey != null) {
	// controls.remove(prevKey);
	// }
	// controls.remove(newKey);
	// }
	//
	public String getKeyboardKeyString(Command c) {
		for (Map.Entry<Integer, Command> entry : controls.entrySet()) {
			if (entry.getValue().equals(c)) {
				return KeyEvent.getKeyText(entry.getKey());
			}
		}
		return null;
	}

	public String getModifiedKeyboardKeyString(Command c) {
		if (modifiedControls == null) {
			return getKeyboardKeyString(c);
		}
		for (Map.Entry<Integer, Command> entry : modifiedControls.entrySet()) {
			if (entry.getValue().equals(c)) {
				return KeyEvent.getKeyText(entry.getKey());
			}
		}
		return null;
	}

	public int getKeyboardKey(Command c) {
		for (Map.Entry<Integer, Command> entry : controls.entrySet()) {
			if (entry.getValue().equals(c)) {
				return entry.getKey();
			}
		}
		return -1;
	}

	public Command getKeyboardKeyCommand(Integer key) {
		if (controls.get(key) == null)
			return Command.UNKNOWN;
		else
			return controls.get(key);
	}

	public void saveControls(HashMap<Integer, Command> controls, File file) {
		BufferedWriter writer = null;
		try {
			if (file.createNewFile()) {
				// for debugging purposes
			}

			writer = new BufferedWriter(new FileWriter(file));

			Iterator<Integer> iterator = controls.keySet().iterator();
			while (iterator.hasNext()) {
				Integer key = iterator.next();
				Command command = controls.get(key);
				writer.write(key + "," + command.toString() + "\n");
				iterator.remove();
			}
			writer.flush();
		} catch (IOException ioe) {
			System.err.println("Failed to save controls.");
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException ieo) {
				System.err.println("Error closing writer.");
			}
		}
	}

	public HashMap<Integer, Command> loadControls(File file) {
		HashMap<Integer, Command> controls = new HashMap<Integer, Command>();

		BufferedReader reader = null;
		if (file.exists()) {
			// load saved controls
			try {
				reader = new BufferedReader(new FileReader(file));

				String line;
				while ((line = reader.readLine()) != null) {
					String[] split = line.split(",");
					String enumerationId = split[1];
					controls.put(Integer.parseInt(split[0]),
							Command.fromString(enumerationId));
				}
			} catch (IOException ioe) {
				System.err.println("Error loading custom controls");
				ioe.printStackTrace();
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException ioe) {
					System.err.println("Error closing BufferedReader");
					ioe.printStackTrace();
				}
			}
		}

		return controls;
	}

	public void setControls(HashMap<Integer, Command> newControls) {
		controls = newControls;
	}

}