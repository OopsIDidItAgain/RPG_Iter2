package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu;
import com.oopsididitagain.rpg_iter2.models.menus.OptionsMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class OptionsMenuViewInteraction extends ModelViewInteraction {

	private OptionsMenu optionsMenu;

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;
	
	public OptionsMenuViewInteraction(OptionsMenu optionsMenu) {
		this.optionsMenu = optionsMenu;
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
		Option[] options = optionsMenu.getOptions();
		for (int i = 0; i < options.length; i++) {
			if (optionsMenu.getCurrentOption().equals(options[i]))
				g.drawString(">> " + options[i].toString(), width-18, height
						+ (i + 1) * heightOffset);
			else
				g.drawString(options[i].toString(), width, height + (i + 1)
						* heightOffset);
		}
	}

}
