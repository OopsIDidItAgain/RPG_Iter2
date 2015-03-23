package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.QuestionMenuController;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public class NonTradingNPC extends NonAttackingNPC{

	public NonTradingNPC(String id, Position position, StatBlob statblob) {
		super(id, position, statblob);
	}

	@Override
	public Controller talk(){
				
		return ActionMenuController.getInstance();
		
	}
	
	@Override
	public String toSaveableFormat() {
		return "NonTradingNpc\n" + super.toSaveableFormat();
	}
}
