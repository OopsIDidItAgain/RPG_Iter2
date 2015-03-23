package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.assets.SoundAssets;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.BattleViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class BattleController extends Controller {

	private static BattleController instance;
	private Battle battle;
	private Npc npc;
	private SoundAssets sa = new SoundAssets();

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
		sa.stopBgClip();
		sa.playBgClip("battle");
		// battle.sortEntities();
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = BattleController.getInstance();
		if (!battle.isDone()) {
			switch (command) {
			case ENTER:
			case USE:
				controller = battle.use();
				break;
			case EXIT:
				controller = ActionMenuController.getInstance();
				break;
			case MOVE_EAST:
			case MOVE_NORTH:
			case MOVE_NORTHEAST:
			case MOVE_NORTHWEST:
			case MOVE_SOUTH:
			case MOVE_SOUTHEAST:
			case MOVE_SOUTHWEST:
			case MOVE_WEST:
				controller = battle.move(command);
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
		} else {
			sa.stopBgClip();
			
			GameController gc = GameController.getInstance();
			gc.remove(npc);
			controller = gc;
		}

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		BattleViewInteraction battleViewInteraction = new BattleViewInteraction(
				battle);
		return battleViewInteraction;
	}

	public void setNpc(Npc npc) {
		this.npc = npc;
	}

}
