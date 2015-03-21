package com.oopsididitagain.rpg_iter2.controllers.menu_controllers;

import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.models.menus.InventoryMenu;
import com.oopsididitagain.rpg_iter2.views.View;

public class InventoryViewInteraction extends ModelViewInteraction {
	private InventoryMenu inventoryMenu;

	public InventoryViewInteraction(InventoryMenu inventoryMenu) {
		this.inventoryMenu = inventoryMenu;
	}

	@Override
	public void accept(View view) {
		view.visit(this);
	}

	@Override
	public void drawModel(Graphics g) {
		// TODO: Draw the inventory to the Graphics
	}

}
