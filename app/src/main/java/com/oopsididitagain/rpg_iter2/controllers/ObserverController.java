package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ObserverViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class ObserverController extends Controller{
	private static ObserverController instance;
	private EntityMapInteraction entityMapInteraction;
	private Skill observe;
	private Avatar avatar;
	
	private ObserverController(){

	}

	public static ObserverController getInstance() {
		if ( instance == null ){
			instance = new ObserverController();
		}
		return instance;
	}
	

	public void setEntityMapInteraction(EntityMapInteraction entityMapInteraction) {
		this.entityMapInteraction = entityMapInteraction;
	}

	public void setSkill(Skill observe) {
		this.observe = observe;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = this;
		switch(command) {
		case ENTER: 
		case EXIT: 
			c = GameController.getInstance();
			break;
		default: break;
		}
		return c;
	}

	@Override
	public ObserverViewInteraction populateInteraction() {
		entityMapInteraction.applySkill(avatar, observe);
		ObserverViewInteraction observerViewInteraction = new ObserverViewInteraction(observe.getEffect());
		return observerViewInteraction;
	}

	

}
