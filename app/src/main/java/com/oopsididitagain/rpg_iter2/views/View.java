package com.oopsididitagain.rpg_iter2.views;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;

public class View extends JPanel{
	//abstract out viewports later
	MainMenuView mainMenuView;
	ModelViewInteraction modelViewInteraction;
	
	
	public View(){
		this.mainMenuView = new MainMenuView();
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

	public void visit(MainMenuViewInteraction mainMenuViewInteraction) {
		this.modelViewInteraction = mainMenuViewInteraction;
		this.repaint();
		
		
	}
	public void paint(MainMenuViewInteraction mainMenuView, Graphics g){
		
	}

}

