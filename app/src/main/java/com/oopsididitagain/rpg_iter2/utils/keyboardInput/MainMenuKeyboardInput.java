package com.oopsididitagain.rpg_iter2.utils.keyboardInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.oopsididitagain.rpg_iter2.models.menus.MainMenu;

public class MainMenuKeyboardInput extends KeyBoardInput {
	
	int input;
	MainMenu mainMenu;
	public MainMenuKeyboardInput( MainMenu mainMenu){
		this.mainMenu = mainMenu;
		input = -5;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
	
		switch (e.getKeyChar()) {
		
		case 'q':
			input = 0;
			break;

		case 'w':
			input = 1;
			break;

        case 'e':
            input = 2;
            break;

        case 'a':
            input = 3;
            break;

        case 's':
            input = 4;
            break;

        case 'd':
            input = 5;
            break;

        case 'z':
            input = 6;
            break;

        case 'x':
            input = 7;
            break;

        case 'c':
            input = 8;
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

	
}
