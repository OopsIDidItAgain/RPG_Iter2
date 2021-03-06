package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class TimedStatModifier extends StatModifier{
	boolean canPerform = true;
	StatBlob manaHurt = new StatBlob(0, 0, 0, 0, 0, 0, 0, 0, -1);
	
	public TimedStatModifier(StatBlob statblob, int radius){
		super(statblob,radius);
	}
	
	public void changeStats(Entity entity, final Avatar avatar) {
		final StatBlob oldBlob = avatar.statBlob();
		oldBlob.merge(statblob);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				oldBlob.detach(statblob);
			}
		};
		Timer timer = new Timer();
		timer.schedule(timertask, 10000);
	}
	
	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		if(avatar.statBlob().getManaAmount() > 0){
			canPerform = false;
			takeTimedMana(avatar.statBlob());
			skillProbe.performSkill(this,avatar,tiles);
		}else{
			System.out.println("not enough mana");
		}
		
	}
	
	private void takeTimedMana(final StatBlob statBlob) {
		statBlob.merge(manaHurt);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				statBlob.detach(manaHurt);		
			}		
		};
		Timer timer = new Timer();
		timer.schedule(timertask, 10000);
		
	}
	

	
}
