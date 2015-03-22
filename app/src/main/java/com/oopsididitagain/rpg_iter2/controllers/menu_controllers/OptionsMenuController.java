package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.KeyBoardInput;

public class OptionsMenuController extends Controller {
	private static OptionsMenuController instance;
	private OptionsMenu optionsMenu;
	
	private OptionsMenuController(OptionsMenu optionsMenu){
		this.optionsMenu = optionsMenu;
	}
	
	public static OptionsMenuController getInstance() {
		if ( instance == null ){
			instance = new OptionsMenuController(new OptionsMenu());
		}
		return instance;
	}
	
	@Override
	public Controller takeInputAndUpdate(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyBoardInput getKeyBoardInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
