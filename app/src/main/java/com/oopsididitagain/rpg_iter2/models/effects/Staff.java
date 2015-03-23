package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class Staff extends StatModifier{
	
	
	public Staff(StatBlob statblob, int radius){
		super(statblob,radius);
	}
	
	public void changeStats(Entity entity, final Avatar avatar) {
		if(entity!=null){
			System.out.println("whack!");
			avatar.visit(this);
		}
	}
	public void applyMultiplier(int m) {
		statblob = baseStatblob.multiply(m);	
		radius = baseRadius;
	}
	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		skillProbe.performSkill(this,avatar,tiles);
	}


}
