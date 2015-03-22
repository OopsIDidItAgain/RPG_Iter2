package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.utils.Commands;

public class InventoryMenuKeyBoardInput extends KeyBoardInput implements KeyListener, MouseListener{
	int input = -5;
	public InventoryMenuKeyBoardInput(){
		input = -5;
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()){
		case Commands.MOVE_NORTH:
			input = Commands.MOVE_NORTH;
			break;
		case Commands.MOVE_WEST:
			input = Commands.MOVE_WEST; 
			break;
		case Commands.MOVE_SOUTH:
			input = Commands.MOVE_SOUTH; 
			break;
		case Commands.MOVE_EAST:
			input = Commands.MOVE_EAST; 
			break;
		case Commands.SELECT:
			input = Commands.SELECT; 
			break;
		case Commands.INVENTORY:
			input = Commands.INVENTORY; 
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
