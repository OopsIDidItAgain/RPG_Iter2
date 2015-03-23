package com.oopsididitagain.rpg_iter2.models;

import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class Trap extends PositionedGameObject implements TiledEntityVisitable{

	TileablePriority tp = TileablePriority.HIDDEN;

	public Trap(String id, Position position) {
		super(id, position);
	}

	private boolean detected = false;
		
	public boolean isDetected() {
		return detected;
	}
	
	public void detect() {
		tp = TileablePriority.MIDDLE;
	}
	
	public void remove() {
		//sprung = true;
	}
	
	@Override
	public void accept(final Entity entity) throws InvalidMovementException {
		// TODO Auto-generated method stub
		EntityStatus trapped = new EntityStatus(EntityStatus.TRAPPED);
		final EntityStatus temp = entity.getEntityStatus(); 
		entity.setEntityStatus(trapped);
		TimerTask timertask = new TimerTask(){
			@Override
			public void run() {
				entity.setEntityStatus(new EntityStatus(EntityStatus.PLAYING));
				System.out.println("Entity is free!");				
			}		
		};
		Timer timer = new Timer();
		System.out.println("Entity is trapped!");
		timer.schedule(timertask, 5 * 1000);
		
	}

	@Override
	public boolean removeable() {
		return false;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return tp;
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public void accept(MovementProbe movementProbe) {

		movementProbe.add(this);
		
	}
	
}