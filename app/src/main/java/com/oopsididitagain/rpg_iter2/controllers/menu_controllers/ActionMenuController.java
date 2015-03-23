package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.assets.SoundAssets;
import com.oopsididitagain.rpg_iter2.controllers.BattleController;
import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.controllers.TradeController;
import com.oopsididitagain.rpg_iter2.controllers.UseController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ActionMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu.Option;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class ActionMenuController extends Controller {

	private static ActionMenuController instance;
	private ActionMenu actionMenu;
	private static Npc npc;
	private Battle battle;
	private Avatar avatar;

	private ActionMenuController(ActionMenu actionMenu) {
		this.actionMenu = actionMenu;
		battle = new Battle();
	}

	public static ActionMenuController getInstance() {
		if (instance == null) {
			instance = new ActionMenuController(new ActionMenu(false));
		}
		return instance;
	}

	public void setNpc(Npc npc) {
		this.npc = npc;
		actionMenu.setCanAttack(npc.accept(battle));
		actionMenu.reset();
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = ActionMenuController.getInstance();

		switch (command) {
		case MOVE_NORTH:
			actionMenu.previousOption();
			break;
		case MOVE_SOUTH:
			actionMenu.nextOption();
			break;
		case ENTER:
		case USE:
			Option o = actionMenu.getCurrentOption();
			switch (o) {
			case Talk:
				// Dialogue part
				DialogueController dc = DialogueController.getInstance();
				dc.setNpc(npc);
				controller = dc;
				break;
			case Attack:
				// logic for canAttack in setNpc
				avatar.setFlying(false);
                SoundAssets.stopSound("main");
                SoundAssets.playSound("battle");

				BattleController bc = BattleController.getInstance();
				Battle battle = new Battle();
				bc.set(battle);
				bc.setNpc(npc);
				battle.addMonster(npc);
				battle.addPartyMember(avatar);

				controller = bc;
				break;
			case Trade:
				System.out.println("trade");
				TradeController tradeController = TradeController.getInstance();
				GameController gc = GameController.getInstance();
				Avatar av = gc.getAvatar();
				tradeController.setNpc(npc, av);
				controller = tradeController;
				break;
			case UseOption:
				// TODO save logic
				System.out.println("use item");
				UseController useController = UseController.getInstance();
				GameController GameC = GameController.getInstance();
				Avatar avatar = GameC.getAvatar();
				useController.setNpc(npc, avatar);
				controller = useController;
				break;
			case Exit:
				actionMenu.reset(); // go back to first option
				controller = GameController.getInstance();
				break;
			default:
				break;
			}
			break;
		case EXIT:
		case PAUSE:
			controller = GameController.getInstance();
			break;
		default:
			break;
		}

		return controller;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		ActionMenuViewInteraction actionMenuViewInteraction = new ActionMenuViewInteraction(
				this.actionMenu);
		return actionMenuViewInteraction;
	}
}
