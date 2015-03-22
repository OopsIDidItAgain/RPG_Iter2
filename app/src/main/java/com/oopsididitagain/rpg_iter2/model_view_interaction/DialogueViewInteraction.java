package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.models.Storyline;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.views.View;

public class DialogueViewInteraction extends ModelViewInteraction {
	

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;
	private Npc npc;
	public DialogueViewInteraction( Npc npc) {
		this.npc = npc;
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
	    g.drawString(npc.getStoryline().printStory(), x + 10, y + 20);
	    g.drawString("Press ENTER: to continue", x + 10, y + width - 85 );
	}
	
}
