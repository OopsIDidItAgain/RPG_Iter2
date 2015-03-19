package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.KeyCode;

public abstract class Controller {
	public abstract Controller takeInputAndUpdate(int key);
	public abstract ModelViewInteraction populateInteraction();
	

}
