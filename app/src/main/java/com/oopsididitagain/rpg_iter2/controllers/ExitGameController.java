package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.KeyBoardInput;



public class ExitGameController extends Controller{
	public static ExitGameController instance;


	private ExitGameController(){


	}

	public static ExitGameController getInstance() {
		if ( instance == null ){
			instance = new ExitGameController();
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
