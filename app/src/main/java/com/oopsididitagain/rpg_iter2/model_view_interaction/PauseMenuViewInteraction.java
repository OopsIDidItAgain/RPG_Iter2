package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu;
import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class PauseMenuViewInteraction extends ModelViewInteraction {

	private PauseMenu pauseMenu;

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;

	public PauseMenuViewInteraction(PauseMenu pauseMenu) {
		this.pauseMenu = pauseMenu;
	}

	@Override
	public void accept(View view) {
		view.visit(this);
	}

	@Override
	public void drawModel(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		int heightOffset = 10;
		g.setColor(Color.white);
		Option[] options = pauseMenu.getOptions();
		for (int i = 0; i < options.length; i++) {
			if (pauseMenu.getCurrentOption().equals(options[i]))
				g.drawString(">> " + options[i].toString(), width-18, height
						+ (i + 1) * heightOffset);
			else
				g.drawString(options[i].toString(), width, height + (i + 1)
						* heightOffset);
		}
	}

}
