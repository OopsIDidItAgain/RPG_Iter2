package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;

import com.oopsididitagain.rpg_iter2.views.View;


public abstract class ModelViewInteraction {
	public abstract void accept(View view);

	public abstract void drawModel(Graphics g);
}
