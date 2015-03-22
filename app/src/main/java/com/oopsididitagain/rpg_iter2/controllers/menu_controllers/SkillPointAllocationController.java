package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.SkillAllocationViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.models.menus.SkillAllocationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu.Option;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class SkillPointAllocationController extends Controller{

	private static SkillPointAllocationController instance;
	private SkillAllocationMenu skillMenu;
	private Avatar avatar;
	private Inventory inventory;
	private Occupation occupation;
	
	private SkillPointAllocationController() {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
		this.occupation = avatar.getOccupation();
		int length = occupation.getGameSkillListString().size();
		this.skillMenu = new SkillAllocationMenu(length);
	}
	
	public static SkillPointAllocationController getInstance() {
		if ( instance == null ){
			instance = new SkillPointAllocationController();
		}
		return instance;
	}

	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller controller = this;

        switch(command){
            case MOVE_SOUTH:
            	skillMenu.changeMenuOption(command);
                break;
            case MOVE_NORTH:
            	skillMenu.changeMenuOption(command);
                break;
            case SKILLALLOCATION:
            	controller = GameController.getInstance();
            case ENTER:
            case USE:
            	int selop = skillMenu.getMenuOption();
            	occupation.increaseMultiplier(selop);
            	avatar.minusUnusedSkillPoints();
            	break;
            case EXIT:
            default:
            	break;
        }

		return controller;
	}

	@Override
	public SkillAllocationViewInteraction populateInteraction() {
		SkillAllocationViewInteraction sk = new SkillAllocationViewInteraction(this.occupation,this.avatar,this.skillMenu);
		return sk;
	}

}
