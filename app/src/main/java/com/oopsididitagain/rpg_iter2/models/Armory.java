package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;

public class Armory {
	InventoryArmorItem helmet;
	InventoryArmorItem boots;
	InventoryArmorItem armor;
	
	InventoryWeaponItem weapon;
	public Armory() {}
	
	public InventoryArmorItem getHelmet() { return helmet; }
	public InventoryArmorItem getBoots() 	{ return boots;  }
	public InventoryArmorItem getArmor() 	{ return armor;  }
	public InventoryWeaponItem getWeapon()	{ return weapon; }
	
	public InventoryWeaponItem equip(InventoryWeaponItem item) {
		InventoryWeaponItem conflict = null;
		conflict = this.weapon;
		this.weapon = item;
		return conflict;
	}

	public InventoryWeaponItem unequip(InventoryWeaponItem item) {
		InventoryWeaponItem temp = null;
		temp = this.weapon;
		this.weapon = null;
		return temp;
	}

	public InventoryArmorItem equip(InventoryArmorItem item) {
		InventoryArmorItem conflict = null;
		switch(item.getArmorItemType()) {
			case HELMET: {
				conflict = helmet;
				helmet = item;
				break;
			}
			case ARMOR: {
				conflict = armor;
				armor = item;
				break;
			}
			case BOOTS: {
				conflict = boots;
				boots = item;
				break;
			}
		}
		return conflict;
	}
	
	public InventoryArmorItem unequip(InventoryArmorItem item) {
		if (item == null) return null;
		InventoryArmorItem temp = null;
		switch(item.getArmorItemType()) {
			case HELMET: {
				if (item.equals(helmet)) {
					temp = helmet;
					helmet = null;
				}
				break;
			}
			case ARMOR: {
				if (item.equals(armor)) {
					temp = armor;
					armor = null;
				}
				break;
			}
			case BOOTS: {
				if (item.equals(boots)) {
					temp = boots;
					boots = null;
				}
				break;
			}
			default: return null;
		}
		return temp;
	}
	
	public int getEquippedWeaponRank() { 
		return (weapon == null) ? 0 : weapon.getRank();
	}

	public int getEquippedArmorRank() { 
		int armorRank = (armor == null) ? 0 : armor.getRank();
		int bootsRank = (boots == null) ? 0 : boots.getRank();
		int helmetRank = (helmet == null) ? 0 : helmet.getRank();
		return armorRank + bootsRank + helmetRank;
	}

}
