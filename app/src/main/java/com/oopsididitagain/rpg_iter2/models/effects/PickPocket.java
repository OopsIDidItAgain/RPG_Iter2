package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.List;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.Direction;


public class PickPocket implements Effect{
	int numberOfItemsStolen = 1;
	int baseNumberOfItemsStolen = 1;
	int radius;
	
	@Override
	public void applyMultiplier(int m) {
		numberOfItemsStolen = baseNumberOfItemsStolen * m;
	}


	public void pickPocket(Inventory stealingEntity, Inventory robbedEntity) {
		List<InventoryItem> stolenItems = robbedEntity.getItems(numberOfItemsStolen);
		stealingEntity.add(stolenItems);
	}


	@Override
	public int getRadius() {
		return this.radius;
	}


	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		// TODO Auto-generated method stub
		
	}


	

}
