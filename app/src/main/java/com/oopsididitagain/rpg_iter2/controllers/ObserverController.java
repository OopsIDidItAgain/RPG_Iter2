package com.oopsididitagain.rpg_iter2.controllers;

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ObserverViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.stats.Stat;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;

public class ObserverController extends Controller{
	private static ObserverController instance;
	private EntityMapInteraction entityMapInteraction;
	private Skill observe;
	private Avatar avatar;
	private ObserveKeyboardInput keyboardInput;
	
	private ObserverController(){
		this.keyboardInput = new ObserveKeyboardInput();

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
	public Controller takeInputAndUpdate(int key) {
		Controller c = this;
		if(key == Commands.ENTER){
			c = GameController.getInstance();
		}
		return c;
	}

	@Override
	public ObserverViewInteraction populateInteraction() {
		entityMapInteraction.applySkill(avatar, observe);
		 ObserverViewInteraction observerViewInteraction = new ObserverViewInteraction(observe.getEffect());
		 return observerViewInteraction;
	}

	@Override
	public KeyBoardInput getKeyBoardInput() {
		return keyboardInput;
	}

	

}
