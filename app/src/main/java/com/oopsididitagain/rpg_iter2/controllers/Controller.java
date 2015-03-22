package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.KeyBoardInput;


public abstract class Controller {
	public abstract Controller takeInputAndUpdate(Command command);
	public abstract ModelViewInteraction populateInteraction();
}
