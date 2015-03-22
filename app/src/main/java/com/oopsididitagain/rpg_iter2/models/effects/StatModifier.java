package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;

public class StatModifier implements Effect, InstantStatModifier{
	StatBlob baseStatblob;
	StatBlob statblob;
	int radius;
	int baseRadius;
	
	public StatModifier(StatBlob statblob, int radius){
		this.baseStatblob = statblob;
		this.baseRadius = radius;
	}
	
	@Override
	public void applyMultiplier(int m) {
		statblob = baseStatblob;
		
	}
	@Override
	public StatBlob statBlob() {
		return statblob;
	}

	@Override
	public int getRadius() {
		return this.radius;
	}

	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		skillProbe.performSkill(this, avatar, tiles);
		
	}
	
	public void changeStats(Entity entity, Avatar avatar) {
		// TODO Auto-generated method stub
		
	}



}
