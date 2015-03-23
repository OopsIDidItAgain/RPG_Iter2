package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;


import java.util.ArrayList;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.GameController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class InventoryController extends Controller {
	private static InventoryController instance;
	private InventoryMenu inventoryMenu;
	private Avatar avatar;
	private Inventory inventory;
    private Tile currentTile;
	
	private InventoryController() {
		this.inventoryMenu = new InventoryMenu();
	}
	
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
		this.inventory = avatar.getInventory();
	}
	public static InventoryController getInstance() {
		if ( instance == null ){
			instance = new InventoryController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = this;
		switch(command) {
		case MOVE_EAST: 
		case MOVE_SOUTH: 
		case MOVE_NORTH: 
		case MOVE_WEST: {
			inventoryMenu.changeMenuOption(command, inventory.size());
			break;
		}
		case USE:
		case EQUIP: {
			try {
				int selectedOption = inventoryMenu.getSelectedOption();
				InventoryItem selectedItem = inventory.getItemAtIndex(selectedOption);
				if(selectedItem != null) {
                    selectedItem.accept(avatar);
                    GameController gc = GameController.getInstance();
                    gc.performPassiveSkills();
                    if (selectedItem.removeable()) // Mixed Instance Cohesion :'(
                        inventory.remove(selectedItem);
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
		case DROP: {
			c = InventoryController.getInstance();
			int selectedOption = inventoryMenu.getSelectedOption();
			InventoryItem selectedItem = inventory.getItemAtIndex(selectedOption);
            if(selectedItem != null) {
                inventory.remove(selectedItem);
                avatar.drop(selectedItem, currentTile);
                System.out.println(selectedItem);
                for (Tileable tileable : currentTile.getTilebles())
                    System.out.println(tileable);
            }
			break;
		}
		default: break;
		}
		return c;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		InventoryViewInteraction inventoryViewInteraction = new InventoryViewInteraction(inventoryMenu, inventory);
		return inventoryViewInteraction;
	}

    public void setCurrentTile(Tile t){
        this.currentTile = t;
    }


}
