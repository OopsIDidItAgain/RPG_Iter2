package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class TimedStatModifier extends StatModifier{
	StatBlob baseStatblob;
	StatBlob statblob;
	int radius;
	
	public TimedStatModifier(StatBlob statblob, int radius){
		super(statblob,radius);
	}
	
	public void changeStats(Entity entity, Avatar avatar) {
		// TODO Auto-generated method stub
		
	}

	
}
