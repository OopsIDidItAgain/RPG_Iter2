package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.Commands;

public class GameKeyboardInput extends KeyBoardInput implements KeyListener, MouseListener {

	int input = -5;
	public GameKeyboardInput(){
		input = -5;
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()){
		case 'w':
			input = Commands.MOVE_NORTH;
			break;
		case 'x':
			input = Commands.MOVE_SOUTH;
			break;
		case 'a':
			input = Commands.MOVE_WEST;
			break;
		case 'd':
			input = Commands.MOVE_EAST;
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
		case KeyEvent.VK_ENTER:
			input = Commands.ENTER;
			break;

		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInput() {
		int tmp = input;
		input = -5;
		return tmp;
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
