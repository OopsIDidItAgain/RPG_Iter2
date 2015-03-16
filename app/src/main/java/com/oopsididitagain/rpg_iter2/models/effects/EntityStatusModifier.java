package com.oopsididitagain.rpg_iter2.models.effects;

/*
 * This class is used to change the status of an Entity with "enchant", we can
 * also use it to change them to difference statuses if we want.
 *
 * 
 */
import java.util.Timer;
import java.util.TimerTask;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;

public class EntityStatusModifier implements Effect{
	int secondsAlterationInEffect = 5;
	EntityStatus changeStatusTo;
	
	EntityStatusModifier(EntityStatus changeStatusTo){
		this.changeStatusTo = changeStatusTo;

	}
	@Override
	public void applyMultiplier(int m) {
		this.secondsAlterationInEffect *= m;
		
	}
	public void changeStatus(final Entity entity) {
		final EntityStatus temp = entity.getEntityStatus(); 
		entity.setEntityStatus(this.changeStatusTo);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				entity.setEntityStatus(temp);
				System.out.println("Entity is awake!");				
			}		
		};
		Timer timer = new Timer();
		System.out.println("Entity is asleep!");
		timer.schedule(timertask, secondsAlterationInEffect * 1000);
	}
}
