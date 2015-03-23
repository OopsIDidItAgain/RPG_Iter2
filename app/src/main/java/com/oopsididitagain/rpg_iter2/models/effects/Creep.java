package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;

public class Creep implements Effect{
	int time;
	int baseTime = 2;
	int radius = 0;
	
	@Override
	public void applyMultiplier(int m) {
		this.time = m*baseTime;
		
	}

	@Override
	public int getRadius() {
		return radius;
	}

	@Override
	public void applySkill(final Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		avatar.setTileablePriority(TileablePriority.HIDDEN);	
		final int speedDifference = 2;
		avatar.lowerMovementSpeedBy(speedDifference);	
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				avatar.raiseMovementSpeedBy(speedDifference);
				avatar.setTileablePriority(TileablePriority.HIGH);		
			}		
		};
		Timer timer = new Timer();
		timer.schedule(timertask, time * 1000);
		

		
	}

}
