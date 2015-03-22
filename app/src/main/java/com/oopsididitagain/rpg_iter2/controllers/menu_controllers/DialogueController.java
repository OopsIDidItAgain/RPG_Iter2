package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.ExitGameController;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.DialogueViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Storyline;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class DialogueController extends Controller {
	private static DialogueController instance;
	private  Npc npc;
	private DialogueController(){
		npc = null;
		
	}
	
	public static DialogueController getInstance() {
		if ( instance == null ){
			instance = new DialogueController();
		}
		return instance;
	}
	
	public void setNpc(Npc npc){
		
		this.npc = npc;
	}
	
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = DialogueController.getInstance();

        switch(command){
           
            case ENTER:
            case USE:
            	controller = npc.talk();
            	
            	
                break;
            case EXIT:
           
            default:
            	break;
        }

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		DialogueViewInteraction dialogueViewInteraction = new DialogueViewInteraction(npc);
		return dialogueViewInteraction;
	}

}
