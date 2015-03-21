package com.oopsididitagain.rpg_iter2.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;

public class AvatarCreationMenuKeyboardInput extends KeyBoardInput implements KeyListener, MouseListener{
	
	int input;
	AvatarCreationMenu avatarCreationMenu;
	public AvatarCreationMenuKeyboardInput( AvatarCreationMenu avatarCreationMenu){
		this.avatarCreationMenu = avatarCreationMenu;
		input = -5;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
	
		switch (e.getKeyChar()) {
		
		case 'w':
			input = 1;
		
			break;

		case 'x':
			input = 2; 
			break;
		case 'g':
			input = 4; 
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
		/*
		this.newGameButton = new Rectangle(width/2 - 100 , height/2, 200, 50);
		this.loadGameButton = new Rectangle(width/2 - 100, height/2 + 50 + padding, 200, 50);
		this.controlsButton = new Rectangle(width/2 - 100, height/2 + 110 + padding, 200, 50);
       */
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if((mouseX >= 200) && (mouseX <= 400)){
			if((mouseY >= 350) && (mouseY <= 400)){
				//handle newGameButton
				avatarCreationMenu.setOption(0);
			}else if((mouseY >= 410) && (mouseY <= 460)){
				
				//handle loadGameButton
				avatarCreationMenu.setOption(1);
			}else if((mouseY >= 470) && (mouseY <= 520)){
				
				//handle controlsButton
				avatarCreationMenu.setOption(2);
			}
		}
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
