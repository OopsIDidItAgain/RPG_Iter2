package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.menus.SkillAllocationMenu;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.views.View;

public class SkillAllocationViewInteraction extends ModelViewInteraction{
	Occupation occupation;
	Avatar avatar;
	SkillAllocationMenu menu;
	public SkillAllocationViewInteraction(Occupation occupation,Avatar avatar,SkillAllocationMenu menu){
		this.occupation = occupation;
		this.avatar = avatar;
		this.menu = menu;
	}
	@Override
	public void accept(View view) {
		view.visit(this);
		
	}
	@Override
	public void drawModel(Graphics g) {
		g.setColor(Color.black);
		LinkedList<String> skills = occupation.getGameSkillListString();
		int unusedPoint = avatar.getUnusedPoints();
		String s = "" + unusedPoint;
		g.drawString(s, 10, View.pWidth/2);
		int widthOffset = 60;
		int i = 0;
		for(String st: skills){
			if(i == menu.getMenuOption()){
				g.setColor(Color.red);
			}
			g.drawString(st, 40, widthOffset);
			widthOffset += widthOffset;
			g.setColor(Color.black);
			++i;
		}
		
	}
	
}
