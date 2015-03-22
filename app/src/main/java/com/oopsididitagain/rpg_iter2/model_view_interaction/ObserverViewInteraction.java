package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.effects.Effect;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.stats.Stat;
import com.oopsididitagain.rpg_iter2.views.View;

public class ObserverViewInteraction extends ModelViewInteraction{
	Observe observe;
	private final int height = View.pHeight/3;
	private final int width = (int) (View.pWidth/2);
	private final int x = View.pWidth/5;
	private final int y = View.pHeight/4;
	public ObserverViewInteraction(Effect effect) {
		this.observe = (Observe)effect;
	}

	@Override
	public void accept(View view) {
		view.visit(this);
		
	}

	@Override
	public void drawModel(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		LinkedList<String> displayedStats = observe.getStats();
		int heightOffset = 10;
		g.setColor(Color.white);
		for(int i = 0; i<displayedStats.size();i++){
			String s = displayedStats.get(i);
			g.drawString(s, width, height + (i+1)*heightOffset);
			heightOffset += heightOffset;
		}
		
		
	}

}
