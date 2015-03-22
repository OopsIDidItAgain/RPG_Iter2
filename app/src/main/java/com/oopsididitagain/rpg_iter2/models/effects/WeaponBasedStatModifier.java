package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class WeaponBasedStatModifier extends StatModifier{
	WeaponItemType weaponType; 
	WeaponItemType currentWeaponType;
	
	public WeaponBasedStatModifier(StatBlob statblob, int radius, WeaponItemType weaponType) {
		super(statblob, radius);
		this.weaponType = weaponType;
	}

	public void changeStats(Entity entity, Avatar avatar) {
		WeaponItemType weaponType = avatar.getWeaponType();
		if(weaponType == null){
			return;
		}
		
		if(avatar.getWeaponType() == weaponType && currentWeaponType != weaponType){
			currentWeaponType = weaponType;
			avatar.visit(this);
			System.out.println("hi");
		}
	}

}
