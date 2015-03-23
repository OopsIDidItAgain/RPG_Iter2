package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.Skill;
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
	public void drawModel(Graphics g) { // <-- HEY GUYS, LOOKS LIKE THIS ISN'T BEING USED 
										// RIGHT NOW. DELETE?
										//
										// - DANNY
										//It is, its when you press "m" -Tess
		g.setColor(Color.black);
		LinkedList<String> skillString = new LinkedList<String>();
		ArrayList<Skill> skills = occupation.getTotalSkills();
		for(Skill s: skills) {
			skillString.add(s.getName());
		}
		int unusedPoint = avatar.getUnusedPoints();
		String s = "" + unusedPoint;
		g.drawString(s, 2, View.pWidth/2);
		int widthOffset = 20;
		int i = 0;
		for(String st: skillString){
			if(i == menu.getMenuOption()){
				g.setColor(Color.red);
			}
			g.drawString(st, 40, widthOffset);
			widthOffset += 20;
			g.setColor(Color.black);
			++i;
		}
		
	}
	
}
