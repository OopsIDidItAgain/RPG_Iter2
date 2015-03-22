package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.utils.Commands;

public class GameKeyboardInput extends KeyBoardInput implements KeyListener, MouseListener {



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()){
		case '1':
			input = 1;
			break;
		case '2':
			input = 2; 
			break;
		case '3':
			input = 3; 
			break;
		case '4':
			input = 4; 
			break;
		case '5':
			input = 5; 
			break;
		case '6':
			input = 6; 
			break;
		case KeyEvent.VK_ENTER:
			input = 'e';
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
