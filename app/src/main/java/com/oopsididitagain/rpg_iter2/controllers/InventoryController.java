package com.oopsididitagain.rpg_iter2.controllers;


import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.utils.Commands;
import com.oopsididitagain.rpg_iter2.utils.KeyBoardInput;

public class InventoryController extends Controller {
	private static InventoryController instance;
	private Avatar avatar;
	private Inventory inventory;
	private int selected;
	
	private InventoryController() {
		GameController gameController = GameController.getInstance();
		this.avatar = gameController.getAvatar();
		this.inventory = avatar.getInventory();
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
		case Commands.EQUIP: {
			try {
				InventoryItem selectedItem = inventory.getItemAtIndex(selected);
				selectedItem.accept(avatar);
			} catch (IndexOutOfBoundsException ex) {
				break;
			}
			break;
		}
			
		}
		return c;
	}

	@Override
	public ModelViewInteraction populateInteraction() {
		return null;
	}

	@Override
	public KeyBoardInput getKeyBoardInput() {
		// TODO Auto-generated method stub
		return null;
	}

}
