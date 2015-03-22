package com.oopsididitagain.rpg_iter2;

import java.awt.Component;
import java.util.concurrent.TimeUnit;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.AvatarCreationMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.MainMenuController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;
import com.oopsididitagain.rpg_iter2.views.View;

public class GameLoop {
	View view;
	KeyBoardInput keyboardInput;
	ModelViewInteraction modelViewInteraction;
	Controller controller;
	
	
	
	public GameLoop(){   //GameLoop constructor
		controller = MainMenuController.getInstance();
		this.modelViewInteraction = controller.populateInteraction();
		keyboardInput =  new KeyBoardInput();
		this.view = new View();
		view.addKeyListener(keyboardInput);
		view.addMouseListener(keyboardInput);
		view.setFocusable(true);
		view.requestFocusInWindow();
	}

	public void runGame() throws InterruptedException{   //Our main gameloop logic
		while(!controller.equals(ExitGameController.getInstance())){
			update();
			view.render(modelViewInteraction);
			TimeUnit.MILLISECONDS.sleep(18);
		}
	}

	public void update(){
		
		Controller temp = controller;
		
		controller = controller.takeInputAndUpdate(keyboardInput.getInput());
		//keyboardInput = controller.getKeyBoardInput();
		//System.out.println(controller);
		//System.out.println(keyboardInput.getInput());
		if(!controller.equals(temp)){
			// view.removeKeyListener(keyBoardtemp);
			// view.addKeyListener(keyboardInput);
			// view.removeMouseListener(keyBoardtemp);
			// view.addMouseListener(keyboardInput);
			view.requestFocusInWindow();
			modelViewInteraction = controller.populateInteraction();
		}
		
		//keyboardInput = controller.getKeyBoardInput();

		
	}

	public Component getView() {
		return view;
	}

}
