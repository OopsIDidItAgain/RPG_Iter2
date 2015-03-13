package com.oopsididitagain.rpg_iter2.models.Effects;

/*
 * This class is used to change the status of an Entity with "enchant", we can
 * also use it to change them to difference statuses if we want.
 *
 * 
 */
import java.util.Timer;
import java.util.TimerTask;
import com.oopsididitagain.rpg_iter2.models.Entities.Entity;
import com.oopsididitagain.rpg_iter2.models.Entities.EntityStatus;

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
	public void changeStatus(Entity entity) {
		EntityStatus temp = entity.getEntityStatus(); 
		entity.setEntityStatus(this.changeStatusTo);
		TimerTask timertask = new StopTimer(temp,entity);
		Timer timer = new Timer();
		System.out.println("Entity is asleep!");
		timer.schedule(timertask, secondsAlterationInEffect * 1000);
	}
	
	class StopTimer extends TimerTask{//This is bad, finding alternative, so it can still be concurrent 
		private EntityStatus old;
		private Entity change;
		public StopTimer(EntityStatus old,Entity change){
			this.old = old;
			this.change = change;
		}
		@Override
		public void run() {
			change.setEntityStatus(old);
			System.out.println("Entity is awake!");
		}
		
	}
	
	


}
