package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.menus.KeyboardConfigMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.views.View;

public class KeyboardConfigMenuViewInteraction extends ModelViewInteraction {

	private KeyboardConfigMenu menu;

	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 20;
	private final int height = View.pHeight - y;
	private final int width = (int) (View.pWidth / 2);

	public KeyboardConfigMenuViewInteraction(
			KeyboardConfigMenu keyboardConfigMenu) {
		menu = keyboardConfigMenu;
	}

	@Override
	public void accept(View view) {
		view.accept(this);
	}

	@Override
	public void drawModel(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		int heightOffset = 10;
		g.setColor(Color.white);
		Command[] options = menu.getOptions();
		for (int i = 0; i < options.length-1; i++) {
			if (menu.getCurrentOption().equals(options[i]))
				g.drawString(">> " + options[i].toString(), width - 18, y
						+ (i + 1) * heightOffset);
			else
				g.drawString(options[i].toString(), width, y + (i + 1)
						* heightOffset);
		}
	}

}
