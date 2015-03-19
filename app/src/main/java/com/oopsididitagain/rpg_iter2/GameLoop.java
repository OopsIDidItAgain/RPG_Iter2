package com.oopsididitagain.rpg_iter2;

import java.awt.Component;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.MainMenuController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.KeyBoardInput;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameLoop {
	View view;
	KeyBoardInput keyBoardInput;
	public void runGame(){
		Controller controller = MainMenuController.getInstance();
		this.view = new View();
		this.keyBoardInput = new KeyBoardInput();
		view.addKeyListener(keyBoardInput);
		view.requestFocus();
		while(controller != ExitGameController.getInstance()){
			int command = keyBoardInput.getInput();
			controller = controller.takeInputAndUpdate(command);
			ModelViewInteraction modelViewInteraction = controller.populateInteraction();
			view.render(modelViewInteraction);
		}
	}

	public Component getView() {
		return view;
	}

}
