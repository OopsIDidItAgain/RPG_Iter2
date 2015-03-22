package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;


import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.keyboardInput.*;

public class InventoryController extends Controller {
	private static InventoryController instance;
	private InventoryMenu inventoryMenu;
	private Avatar avatar;
	private Inventory inventory;
	private InventoryMenuKeyBoardInput keyBoardInput;
	
	private InventoryController() {
		GameController gameController = GameController.getInstance();
		this.keyBoardInput =  new InventoryMenuKeyBoardInput();
		this.avatar = gameController.getAvatar();
		this.inventory = avatar.getInventory();
		this.inventoryMenu = new InventoryMenu();
	}
	
	public static InventoryController getInstance() {
		if ( instance == null ){
			instance = new InventoryController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(int key) {
		Controller c = this;
		switch(key) {
		case Commands.MOVE_EAST: 
		case Commands.MOVE_SOUTH: 
		case Commands.MOVE_NORTH: 
		case Commands.MOVE_WEST: {
			inventoryMenu.changeMenuOption(key, inventory.size());
			break;
		}
		case Commands.USE:
		case Commands.EQUIP: {
			try {
				int selectedOption = inventoryMenu.getSelectedOption();
				InventoryItem selectedItem = inventory.getItemAtIndex(selectedOption);
				selectedItem.accept(avatar);
			} catch (IndexOutOfBoundsException ex) {
				ex.printStackTrace();
				break;
			}
			break;
		}
		case Commands.INVENTORY: {
			c = GameController.getInstance();
			break;
		}
		case Commands.DROP: {
			c = GameController.getInstance();
			int selectedOption = inventoryMenu.getSelectedOption();
			InventoryItem selectedItem = inventory.getItemAtIndex(selectedOption);
			inventory.remove(selectedItem);
			avatar.drop(selectedItem);
			break;
		}
			
		}
		return c;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		InventoryViewInteraction inventoryViewInteraction = new InventoryViewInteraction(inventoryMenu, inventory);
		return inventoryViewInteraction;
	}

	@Override
	public KeyBoardInput getKeyBoardInput() {
		return keyBoardInput;
	}

}
