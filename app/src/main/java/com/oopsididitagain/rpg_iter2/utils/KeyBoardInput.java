package com.oopsididitagain.rpg_iter2.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class KeyBoardInput implements KeyListener, MouseListener {
	public abstract int getInput();
	
	

	/*
	
		switch (e.getKeyChar()) {
		case 'q':
			input = KeyCode.NORTH_WEST;
			
			break;
		case 'w':
			input = KeyCode.NORTH;
			break;
		case 'e':
			input = KeyCode.NORTH_EAST;
			break;
		case 'd':
			input = KeyCode.EAST;
			break;
		case 'c':
			input = KeyCode.SOUTH_EAST;
			break;
		case 'x':
			input = KeyCode.SOUTH;
			break;
		case 'z':
			input = KeyCode.SOUTH_WEST;
			break;
		case 'a':
			input = KeyCode.WEST;
			break;
		case 's':
			input = KeyCode.USE;
			break;
		case 'p':
			input = KeyCode.PAUSE;
			break;
		case KeyEvent.VK_ENTER:
			input = KeyCode.ENTER;
			break;
		case 'i':
			input = KeyCode.INVENTORY;
			break;
		case 'j':
			input = KeyCode.EQUIP;
			break;
		case 'k':
			input = KeyCode.DROP;
			break;
		case 'f':
			input = KeyCode.FLY;
			break;
		case 'g':
			input = KeyCode.FARM;
			break;
		}
	}
*/
	

}
