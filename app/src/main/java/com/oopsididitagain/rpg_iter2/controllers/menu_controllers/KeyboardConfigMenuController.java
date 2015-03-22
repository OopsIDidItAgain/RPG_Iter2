package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import java.awt.event.KeyEvent;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.KeyboardConfigMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.KeyboardConfigMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.CustomControlsHandler;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.KeyBoardInput;

public class KeyboardConfigMenuController extends Controller {
	private static KeyboardConfigMenuController instance;
	private KeyboardConfigMenu keyboardConfigMenu;
	private CustomControlsHandler handler;
	private KeyBoardInput input;
	private int lastPressed;
	private boolean hasModified;
	
	private KeyboardConfigMenuController(KeyBoardInput input,
			KeyboardConfigMenu keyboardConfigMenu) {
		this.keyboardConfigMenu = keyboardConfigMenu;
		this.input = input;
		this.handler = CustomControlsHandler.getInstance();
	}

	public static KeyboardConfigMenuController getInstance() {
		if (instance == null) {
			instance = new KeyboardConfigMenuController(
					KeyBoardInput.getInstance(), new KeyboardConfigMenu());
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = KeyboardConfigMenuController.getInstance();
		if (keyboardConfigMenu.isSelected() != true) {
			switch (command) {
			case MOVE_NORTH:
				keyboardConfigMenu.previousOption();
				break;
			case MOVE_SOUTH:
				keyboardConfigMenu.nextOption();
				break;
			case ENTER:
			case USE:
				lastPressed = input.getLastInput();
				if (keyboardConfigMenu.getCurrentOption().equals(Command.EXIT)) {
					controller = OptionsMenuController.getInstance();
					handler.acceptNewControls();
				} else {
					keyboardConfigMenu.toggleSelect();
				}
				break;
			case EXIT:
				controller = OptionsMenuController.getInstance();
				handler.acceptNewControls();
				break;
			default:
				break;
			}

		} else {
			modify();
		}

		return controller;
	}

	private void modify() {
		if (input.getLastInput() == lastPressed) {
			// do nothing
		} else {
			System.out.println(KeyEvent.getKeyText(input.getLastInput()));
			handler.saveOldControls();
			handler.bind(input.getLastInput(),
					keyboardConfigMenu.getCurrentOption());
			hasModified = true;
			keyboardConfigMenu.toggleSelect();
		}
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		KeyboardConfigMenuViewInteraction KeyboardConfigMenuViewInteraction = new KeyboardConfigMenuViewInteraction(
				this.keyboardConfigMenu);
		return KeyboardConfigMenuViewInteraction;
	}

}
