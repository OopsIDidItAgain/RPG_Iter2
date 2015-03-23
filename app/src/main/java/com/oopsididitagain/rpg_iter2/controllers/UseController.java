package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.DialogueController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.DialogueViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUsableItem;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Priceable;

public class UseController extends Controller {

	private static UseController instance;
	private  Inventory AvatarInventory;
	private  Npc npc;
	private InventoryMenu menu;
	private UseController(){
		AvatarInventory = null;
		npc = null;
		this.menu = new InventoryMenu();
		
	}
	
	public static UseController getInstance() {
		if ( instance == null ){
			instance = new UseController();
		}
		return instance;
	}
	
	public void setNpc(Npc npc,Avatar avatar){
		this.npc= npc;
		this.AvatarInventory = avatar.getInventory();
	}
	
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = this;
		switch(command) {
		case MOVE_EAST: 
		case MOVE_SOUTH: 
		case MOVE_NORTH: 
		case MOVE_WEST: {
			menu.changeMenuOption(command, AvatarInventory.size());
			break;
		}
		case USE:
		case EQUIP: {
			try {
				int selectedOption = menu.getSelectedOption();
				InventoryItem selectedItem = AvatarInventory.getItemAtIndex(selectedOption);
				if(selectedItem != null) {
                    selectedItem.accept(npc);
                    GameController gc = GameController.getInstance();
                    gc.performPassiveSkills();
                    if (selectedItem.removeable()) // Mixed Instance Cohesion :'(
                        AvatarInventory.remove(selectedItem);
                    System.out.println(npc.statBlob().getLifeAmountStat());
				}
			} catch (IndexOutOfBoundsException ex) {
				ex.printStackTrace();
				break;
			}
			break;
		}
		case INVENTORY: {
			c = GameController.getInstance();
			break;
		}
		default: break;
		}
		return c;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		InventoryViewInteraction inventoryViewInteraction = new InventoryViewInteraction(menu, AvatarInventory);
		return inventoryViewInteraction;
	}
	
}
