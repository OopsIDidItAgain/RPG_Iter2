package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.QuestionMenuController;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public class TradingNPC extends NonAttackingNPC{

	public TradingNPC(String id, Position position, StatBlob statblob) {
		super(id, position, statblob);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Controller talk(){
		//return bartering controller
		String question = " >> Wanna trade some stuff?!";
		QuestionMenuController qmc = QuestionMenuController.getInstance();
		qmc.setQuestion(question);
		qmc.setNpc(this);
		return qmc;
		
	}

}
