package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInput implements KeyListener {

	public static final int NO_KEY_PRESSED = -5;

	int input = NO_KEY_PRESSED;

	public int getInput() {
		int temp = input;
		input = NO_KEY_PRESSED;
		return temp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		input = e.getKeyCode();
		System.out.println(input);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
