package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.model_view_interaction.BattleViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class BattleController extends Controller {

	private static BattleController instance;
	private Battle battle;

	private BattleController() {
	}

	public static BattleController getInstance() {
		if (instance == null) {
			instance = new BattleController();
		}
		return instance;
	}

	public void set(Battle battle) {
		this.battle = battle;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = BattleController.getInstance();

		switch (command) {
		case ENTER:
		case USE:
			break;
		case EXIT:
			break;
		case MOVE_EAST:
			break;
		case MOVE_NORTH:
			break;
		case MOVE_NORTHEAST:
			break;
		case MOVE_NORTHWEST:
			break;
		case MOVE_SOUTH:
			break;
		case MOVE_SOUTHEAST:
			break;
		case MOVE_SOUTHWEST:
			break;
		case MOVE_WEST:
			break;
		case SKILLFIVE:
			break;
		case SKILLFOUR:
			break;
		case SKILLONE:
			break;
		case SKILLSIX:
			break;
		case SKILLTHREE:
			break;
		case SKILLTWO:
			break;
		default:
			break;

		}

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		BattleViewInteraction battleViewInteraction = new BattleViewInteraction(
				battle);
		return battleViewInteraction;
	}

}
