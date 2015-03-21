package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class TimedStatModifier implements Effect{
	StatBlob baseStatblob;
	StatBlob statblob;
	int radius;
	
	public TimedStatModifier(StatBlob statblob, int radius){
		this.baseStatblob = statblob;
		this.radius = radius;
	}
	@Override
	public void applyMultiplier(int m) {
		// TODO Auto-generated method stub
		
	}
	public void affect(StatBlob target) {//put a timer here
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		// TODO Auto-generated method stub
		
	}
	
}
