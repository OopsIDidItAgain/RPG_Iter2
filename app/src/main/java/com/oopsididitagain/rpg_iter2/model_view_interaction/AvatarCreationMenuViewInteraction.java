package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu;
import com.oopsididitagain.rpg_iter2.models.menus.AvatarCreationMenu.Option;
import com.oopsididitagain.rpg_iter2.views.View;

public class AvatarCreationMenuViewInteraction extends ModelViewInteraction {
	int padding = 10;

	private AvatarCreationMenu avatarCreationMenu;

	public AvatarCreationMenuViewInteraction(
			AvatarCreationMenu avatarCreationMenu) {
		this.avatarCreationMenu = avatarCreationMenu;
		// this.summonerButton = new
		// MenuButton(avatarCreationMenu.getCurrentOption().toString(), 0 , 0,
		// View.WIDTH/3, View.HEIGHT);
		// this.smasherButton = new
		// MenuButton(avatarCreationMenu.getOptions(1),View.WIDTH/3, 0,
		// View.WIDTH/3, View.HEIGHT);
		// this.sneakButton = new MenuButton(avatarCreationMenu.getOptions(2),
		// (2 * View.WIDTH)/3, 0, View.WIDTH/3, View.HEIGHT);

	}

	@Override
	public void accept(View view) {
		view.visit(this);

	}

	@Override
	public void drawModel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g.fillRect(0, 0, View.WIDTH, View.HEIGHT);
		Font f = new Font("arial", Font.BOLD, 30);
		g.setColor(Color.WHITE);
		Option[] options = avatarCreationMenu.getOptions();
		int widthDivLength = View.WIDTH / options.length;
		int height = View.HEIGHT;
		for (int i = 0; i < options.length; i++) {
			Option o = options[i];
			if (avatarCreationMenu.getCurrentOption().equals(o)) {
				g.setColor(Color.WHITE);
				g2d.fillRect(i * widthDivLength, 0, widthDivLength, height);
				g.setFont(f);
				g.setColor(Color.BLACK);
				g.drawString(o.toString(), i * widthDivLength + 30, 0 + 35);
			} else {
				g.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(i * widthDivLength, 0, widthDivLength, height);
				g.setFont(f);
				g.setColor(Color.BLACK);
				g.drawString(o.toString(), i * widthDivLength + 30, 0 + 35);
			}
		}
	}
}
