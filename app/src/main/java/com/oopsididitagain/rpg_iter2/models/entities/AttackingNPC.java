package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.QuestionMenuController;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Battleable;

public class AttackingNPC extends Npc implements Battleable{

	public AttackingNPC(String id, Position position, StatBlob statblob) {
		super(id, position, statblob);
	}
	
	@Override
	public Controller talk(){
		//return battle controller
		
		return GameController.getInstance();
	}
	
	public boolean accept(Battle battle) {
		return battle.visit(this);
	}
	
	@Override
	public String toSaveableFormat() {
		return "AttackingNpc\n" + super.toSaveableFormat();
	}
	
}
