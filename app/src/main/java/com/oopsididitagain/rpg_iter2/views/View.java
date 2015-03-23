package com.oopsididitagain.rpg_iter2.views;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.oopsididitagain.rpg_iter2.model_view_interaction.ActionMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.AvatarCreationMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.BattleViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.DialogueViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameOverViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.InventoryViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.KeyboardConfigMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.MainMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ModelViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.ObserverViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.OptionsMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.PauseMenuViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.QuestionViewInteraction;
import com.oopsididitagain.rpg_iter2.model_view_interaction.SkillAllocationViewInteraction;

public class View extends JPanel {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 700;

	// abstract out viewports later
	public static final int pHeight = 700;
	public static final int pWidth = 600;
	private ModelViewInteraction modelViewInteraction;
	private GameViewInteraction background;
	private boolean drawMapBackground = false;

	public View() {

	}

	public void render(ModelViewInteraction mv) {
		mv.accept(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (modelViewInteraction != null) {
			if (drawMapBackground) {
				background.drawModel(g);
				drawMapBackground = false;
			}
			modelViewInteraction.drawModel(g);
		}

	}
	public void visit(ActionMenuViewInteraction actionMenuViewInteraction) {
		this.modelViewInteraction = actionMenuViewInteraction;
		drawMapBackground = true;
		this.repaint();
	}

	public void visit(InventoryViewInteraction inventoryViewInteraction) {
		this.modelViewInteraction = inventoryViewInteraction;
		drawMapBackground = true;
		this.repaint();
	}

	public void visit(GameViewInteraction gameViewInteraction) {
		this.modelViewInteraction = gameViewInteraction;
		this.background = gameViewInteraction;
		this.repaint();
	}

	public void visit(MainMenuViewInteraction mainMenuViewInteraction) {
		this.modelViewInteraction = mainMenuViewInteraction;
		this.repaint();
	}

	public void visit(
			AvatarCreationMenuViewInteraction avatarCreationMenuViewInteration) {
		this.modelViewInteraction = avatarCreationMenuViewInteration;
		this.repaint();
	}

	public void visit(ObserverViewInteraction observerViewInteraction) {
		this.modelViewInteraction = observerViewInteraction;
		drawMapBackground = true;
		this.repaint();

	}

	public void visit(PauseMenuViewInteraction pauseMenuViewInteraction) {
		this.modelViewInteraction = pauseMenuViewInteraction;
		drawMapBackground = true;
		this.repaint();

	}

	public void visit(OptionsMenuViewInteraction optionsMenuViewInteraction) {
		this.modelViewInteraction = optionsMenuViewInteraction;
		this.repaint();

	}

	public void accept(
			KeyboardConfigMenuViewInteraction keyboardConfigMenuViewInteraction) {
		this.modelViewInteraction = keyboardConfigMenuViewInteraction;
		this.repaint();

	}

	public void visit(SkillAllocationViewInteraction skillAllocationViewInteraction) {
		this.modelViewInteraction = skillAllocationViewInteraction;
		//drawMapBackground = true;
		this.repaint();
		
	}

	public void visit(DialogueViewInteraction dialogueViewInteraction) {
		drawMapBackground = true;
		this.modelViewInteraction = dialogueViewInteraction;
		this.repaint();
	}

	public void visit(QuestionViewInteraction questionViewInteraction) {
		drawMapBackground = true;
		this.modelViewInteraction = questionViewInteraction;
		this.repaint();		
	}

	public void visit(GameOverViewInteraction gameOverViewInteraction) {
		this.modelViewInteraction = gameOverViewInteraction;
		this.repaint();
	}

	public void accept(BattleViewInteraction battleViewInteraction) {
		this.modelViewInteraction = battleViewInteraction;
		this.repaint();		
	}



}
