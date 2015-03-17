package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;

public class StatModifier implements Effect, InstantStatModifier{
	StatBlob baseStatblob;
	StatBlob statblob;
	public StatModifier(StatBlob statblob){
		this.statblob = statblob;
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

}
