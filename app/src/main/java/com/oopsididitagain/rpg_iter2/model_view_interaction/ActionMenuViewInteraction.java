package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu;
import com.oopsididitagain.rpg_iter2.models.menus.ActionMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class ActionMenuViewInteraction extends ModelViewInteraction {
	private ActionMenu actionMenu;

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;

	public ActionMenuViewInteraction(ActionMenu actionMenu) {
		this.actionMenu = actionMenu;
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
		Option[] options = actionMenu.getOptions();
		for (int i = 0; i < options.length; i++) {
			if (actionMenu.getCurrentOption().equals(options[i]))
				g.drawString(">> " + options[i].toString(), width-18, height
						+ (i + 1) * heightOffset);
			else
				g.drawString(options[i].toString(), width, height + (i + 1)
						* heightOffset);
		}
	}

}
