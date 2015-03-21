package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

/*
 * This is the class that all effects of skills should implement
 * 
 */
public interface Effect {
	public void applyMultiplier(int m);
	public int getRadius();
	public void applySkill(Avatar avatar, MiniMap tiles,SkillProbe skillProbe);
}
