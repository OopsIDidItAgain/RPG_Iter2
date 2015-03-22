package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.utils.Commands;

public class KeyBoardInput implements KeyListener, MouseListener {

	int input = -5;

	public int getInput() {
		int temp = input;
		input = -5;
		return temp;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int temp = e.getKeyCode() - 96;
		if (temp > 0 && temp <= 9) {
			input = temp;
			return;
		}
		switch (e.getKeyChar()) {
		case 'q':
			input = Commands.MOVE_NORTH_WEST;
			break;
		case 'w':
			input = Commands.MOVE_NORTH;
			break;
		case 'e':
			input = Commands.MOVE_NORTH_EAST;
			break;
		case 'd':
			input = Commands.MOVE_EAST;
			break;
		case 'c':
			input = Commands.MOVE_SOUTH_EAST;
			break;
		case 'x':
			input = Commands.MOVE_SOUTH;
			break;
		case 'z':
			input = Commands.MOVE_SOUTH_WEST;
			break;
		case 'a':
			input = Commands.MOVE_WEST;
			break;
		case 's':
			input = Commands.USE;
			break;
		case 'p':
			input = Commands.PAUSE;
			break;
		case KeyEvent.VK_ENTER:
			input = Commands.ENTER;
			break;
		case '1':
			input = Commands.SKILLONE;
			break;
		case '2':
			input = Commands.SKILLTWO; 
			break;
		case '3':
			input = Commands.SKILLTHREE; 
			break;
		case '4':
			input = Commands.SKILLFOUR; 
			break;
		case '5':
			input = Commands.SKILLFIVE; 
			break;
		case '6':
			input = Commands.SKILLSIX; 
			break;
		case 'i':
			input = Commands.INVENTORY;
			break;
		case 'j':
			input = Commands.EQUIP;
			break;
		case 'k':
			input = Commands.DROP;
			break;
		case '`':
			input = Commands.EXIT;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
