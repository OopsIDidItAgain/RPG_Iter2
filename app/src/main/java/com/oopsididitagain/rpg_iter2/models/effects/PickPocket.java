package com.oopsididitagain.rpg_iter2.models.effects;

import java.util.List;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;


public class PickPocket implements Effect{
	int numberOfItemsStolen;
	int baseNumberOfItemsStolen = 1;
	int baseRadius = 2;
	int radius;
	
	@Override
	public void applyMultiplier(int m) {
		numberOfItemsStolen = baseNumberOfItemsStolen * m;
		radius = baseRadius *m;
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
		skillProbe.setUpSkill(this,avatar,tiles);
		
	}


	

}
