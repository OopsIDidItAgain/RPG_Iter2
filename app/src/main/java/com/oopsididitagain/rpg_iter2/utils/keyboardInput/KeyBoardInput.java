package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInput implements KeyListener {
	
	private static KeyBoardInput instance;
	
	private KeyBoardInput() {
		
	}
	
	public static KeyBoardInput getInstance() {
		if(instance == null) {
			instance = new KeyBoardInput();
		}
		return instance;
	}
	
	
	
	
	public static final int NO_KEY_PRESSED = -5;

	int input = NO_KEY_PRESSED;
	
	private int lastInput;

	public int getInput() {
		int temp = input;
		input = NO_KEY_PRESSED;
		return temp;
	}

	public int getLastInput() {
		return lastInput;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		input = e.getKeyCode();
		lastInput = e.getKeyCode();
		//System.out.println(input);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
