package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public class SelfInflictedModifier extends StatModifier{
	public SelfInflictedModifier(StatBlob statblob,int radius) {
		super(statblob, radius);
	}
	
	public void changeStats(Entity entity, Avatar avatar) {
		avatar.visit(this);
	}

}
