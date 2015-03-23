package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public enum AreaEffectType implements StatBlobHolder {
	LEVEL_UP(new StatBlob(0, 0, 0, 0, 0, 1, 0, 0, 0)),
	HEAL_DAMAGE(new StatBlob(0, 0, 0, 0, 0, 0, 0, 2, 0)),
	TAKE_DAMAGE(new StatBlob(0, 0, 0, 0, 0, 0, 0, -2, 0)),
	INSTANT_DEATH(new StatBlob(0, 0, 0, 0, 0, 0, 0, -1000000, 0));
	
	private StatBlob statBlob;
	
	private AreaEffectType(StatBlob statBlob) {
		this.statBlob = statBlob;
	}

	@Override
	public StatBlob statBlob() {
		return statBlob;
	}
	
	public String toString() {
		switch(this) {
		case HEAL_DAMAGE:
			return "HEAL_DAMAGE";
		case INSTANT_DEATH:
			return "INSTANT_DEATH";
		case LEVEL_UP:
			return "LEVEL_UP";
		case TAKE_DAMAGE:
			return "TAKE_DAMAGE";
		default:
			return "HEAL_DAMAGE";
		}
	}
}
