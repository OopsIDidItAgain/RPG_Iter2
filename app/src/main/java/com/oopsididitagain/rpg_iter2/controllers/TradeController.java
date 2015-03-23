package com.oopsididitagain.rpg_iter2.controllers;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.DialogueController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.DialogueViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Priceable;

public class TradeController extends Controller{

	private static TradeController instance;
	private  Inventory NPCInventory;
	private  Avatar avatar;
	private InventoryMenu menu;
	private TradeController(){
		NPCInventory = null;
		avatar = null;
		this.menu = new InventoryMenu();
		
	}
	
	public static TradeController getInstance() {
		if ( instance == null ){
			instance = new TradeController();
		}
		return instance;
	}
	
	public void setNpc(Npc npc,Avatar avatar){
		this.avatar= avatar;
		this.NPCInventory = npc.getInventory();
	}
	
	
	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = this;
		switch(command) {
		case MOVE_EAST: 
		case MOVE_SOUTH: 
		case MOVE_NORTH: 
		case MOVE_WEST: {
			menu.changeMenuOption(command, NPCInventory.size());
			break;
		}
		case USE:
		case EQUIP: {
			try {
				
				int selectedOption = menu.getSelectedOption();
				Priceable pricedItem = NPCInventory.getItemAtIndex(selectedOption);
				if(pricedItem != null){
					avatar.purchaseItem(pricedItem);
					GameController gc = GameController.getInstance();
					gc.performPassiveSkills();
						NPCInventory.remove(pricedItem);
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
		InventoryViewInteraction inventoryViewInteraction = new InventoryViewInteraction(menu,NPCInventory,avatar.getBank());
		return inventoryViewInteraction;
	}
}
