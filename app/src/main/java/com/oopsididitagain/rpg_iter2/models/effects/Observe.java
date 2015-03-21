package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.Stat;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class Observe implements Effect {
	int radius;
	int baseRadius = 3;
	private int baseNumberOfStats = 1;
	private int numberOfStats;
	
	LinkedList<String> stats = new LinkedList<String>();
	@Override
	public void applyMultiplier(int m) {
		numberOfStats = m * baseNumberOfStats;
		if(numberOfStats > 9){
			numberOfStats = 9;
		}
		radius = m * baseRadius;
	}

	@Override
	public int getRadius() {
		return radius;
	}
	public void observe(StatBlob statBlob){
		LinkedList<String> stats = statBlob.getRandomStats(numberOfStats);
		this.stats = stats;
	}
	public LinkedList<String> getStats(){
		return stats;
	}

	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		skillProbe.setUpSkill(this, avatar, tiles);
		
	}

}
