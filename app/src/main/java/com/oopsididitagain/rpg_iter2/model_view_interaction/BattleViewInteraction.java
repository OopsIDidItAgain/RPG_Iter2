package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.views.View;

public class BattleViewInteraction extends ModelViewInteraction {
	private Battle battle;

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;

	public BattleViewInteraction(Battle battle) {
		this.battle = battle;
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
	    g.drawString("BATTLE MODE", x + 10, y + 20);
	}

}
