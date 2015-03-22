package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.model_view_interaction.QuestionViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class QuestionMenuController extends Controller{
	private static QuestionMenuController instance;
	private String question;
	private QuestionMenuController(){
		question = "Default constructor question.";
		
	}
	
	public static QuestionMenuController getInstance() {
		if ( instance == null ){
			instance = new QuestionMenuController();
		}
		return instance;
	}
	
	public void setQuestion(String question){
		this.question = question;
		
	}
	
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = QuestionMenuController.getInstance();
        switch(command){
            case YES: 
            	//Enter barter controller
            	
            	break;
            case NO: 
            	controller = ActionMenuController.getInstance();
            	break;
           
           
            default:
            	break;
        }

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		QuestionViewInteraction questionViewInteraction = new QuestionViewInteraction(question);
		return questionViewInteraction;
	}

}
