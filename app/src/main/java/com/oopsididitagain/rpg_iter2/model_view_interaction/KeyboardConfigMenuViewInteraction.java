package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.menus.KeyboardConfigMenu;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.CustomControlsHandler;
import com.oopsididitagain.rpg_iter2.views.View;

public class KeyboardConfigMenuViewInteraction extends ModelViewInteraction {

	private KeyboardConfigMenu menu;
	private CustomControlsHandler handler;

	private final int x = View.pWidth / 4;
	private final int y = View.pHeight / 20;
	private final int height = View.pHeight - y;
	private final int width = (int) (View.pWidth / 2);

	public KeyboardConfigMenuViewInteraction(
			KeyboardConfigMenu keyboardConfigMenu) {
		menu = keyboardConfigMenu;
		handler = CustomControlsHandler.getInstance();
	}

	@Override
	public void accept(View view) {
		view.accept(this);
	}

	@Override
	public void drawModel(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		int heightOffset = 10;
		Command[] options = menu.getOptions();
		for (int i = 0; i < options.length - 1; i++) {
			String output = "";
			if (options[i].equals(Command.EXIT))
				output = options[i].toString();
			else
				output = options[i].toString() + " - "
						+ handler.getModifiedKeyboardKeyString(options[i]);
			if (menu.getCurrentOption().equals(options[i])) {
				if (menu.isSelected())
					g.setColor(Color.RED);
				g.drawString(">> " + output, width - 18, y + (i + 1)
						* heightOffset);
			} else {
				g.setColor(Color.white);
				g.drawString(output, width, y + (i + 1) * heightOffset);
			}
		}
	}

}
