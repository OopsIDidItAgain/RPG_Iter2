package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;



public class ExitGameController extends Controller{
	private static ExitGameController instance;


	private ExitGameController(){


	}

	public static ExitGameController getInstance() {
		if ( instance == null ){
			instance = new ExitGameController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		// TODO Auto-generated method stub
		return null;
	}

}
