package com.oopsididitagain.rpg_iter2.models.effects;

/*
 * This class is used to change the status of an Entity with "enchant", we can
 * also use it to change them to difference statuses if we want.
 *
 * 
 */
import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class EntityStatusModifier implements Effect{
	int secondsAlterationInEffect;
	int baseSecondsAlterationInEffect = 5;
	int baseRadius = 3;
	StatBlob manaHurt = new StatBlob(0, 0, 0, 0, 0, 0, 0, 0, -1);
	StatBlob manaRegain = new StatBlob(0, 0, 0, 0, 0, 0, 0, 0, 1);
	int radius;
	EntityStatus changeStatusTo;
	boolean canPerform = true;
	
	public EntityStatusModifier(EntityStatus changeStatusTo){
		this.changeStatusTo = changeStatusTo;

	}
	@Override
	public void applyMultiplier(int m) {
		this.secondsAlterationInEffect = baseSecondsAlterationInEffect * m;
		this.radius = baseRadius * m;
		
	}
	public void changeStatus(final Entity entity) {
		final EntityStatus temp = entity.getEntityStatus(); 
		entity.setEntityStatus(this.changeStatusTo);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				entity.setEntityStatus(new EntityStatus(EntityStatus.PLAYING));
				System.out.println("Entity is awake!");				
			}		
		};
		Timer timer = new Timer();
		System.out.println("Entity is asleep!");
		timer.schedule(timertask, secondsAlterationInEffect * 1000);
	}
	@Override
	public int getRadius() {
		return this.radius;
	}
	@Override
	public void applySkill(Avatar avatar, MiniMap tiles,SkillProbe skillProbe) {
		if(avatar.statBlob().getManaAmount() > 0){
			canPerform = false;
			takeTimedMana(avatar.statBlob());
			skillProbe.performSkill(this,avatar,tiles);
		}else{
			System.out.println("not enough mana");
		}
	}
	
	private void takeTimedMana(final StatBlob statBlob) {
		final StatBlob oldblob = statBlob;
		statBlob.merge(manaHurt);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				statBlob.merge(manaRegain);		
			}		
		};
		Timer timer = new Timer();
		timer.schedule(timertask, 10000);
		
	}
}
