package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;
import com.oopsididitagain.rpg_iter2.utils.Commands;

public class ObserveKeyboardInput extends KeyBoardInput implements KeyListener, MouseListener  {
	int input = -5;

	public ObserveKeyboardInput(){
		input = -5;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()){
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
