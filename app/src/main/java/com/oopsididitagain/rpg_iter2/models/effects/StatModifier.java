package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;

public class StatModifier implements Effect, InstantStatModifier{
	StatBlob baseStatblob;
	StatBlob statblob;
	int radius;
	public StatModifier(StatBlob statblob, int radius){
		this.statblob = baseStatblob;
		this.radius = radius;
	}
	
	@Override
	public void applyMultiplier(int m) {
		//statblob = statblob.multiply(m);
		
	}
	@Override
	public StatBlob statBlob() {
		return statblob;
	}
	@Override
	public void affect(StatBlob target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRadius() {
		return this.radius;
	}

	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		// TODO Auto-generated method stub
		
	}



}
