package com.oopsididitagain.rpg_iter2.views;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.AvatarCreationMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;

public class View extends JPanel{
	//abstract out viewports later
	ModelViewInteraction modelViewInteraction;
	
	public View(){
		
	}

	public void render(ModelViewInteraction mv) {
		mv.accept(this);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(modelViewInteraction != null){
			modelViewInteraction.drawModel(g);
		}

	}
	public void visit(InventoryViewInteraction inventoryViewInteraction) {
		this.modelViewInteraction = inventoryViewInteraction;
		this.repaint();
	}

	public void visit(MainMenuViewInteraction mainMenuViewInteraction) {
		this.modelViewInteraction = mainMenuViewInteraction;
		this.repaint();
	}

	public void visit(AvatarCreationMenuViewInteraction avatarCreationMenuViewInteration) {
		this.modelViewInteraction = avatarCreationMenuViewInteration;
		this.repaint();
	}

	public void visit(GameViewInteraction gameViewInteraction) {
		this.modelViewInteraction = gameViewInteraction;
		this.repaint();
	}

}

