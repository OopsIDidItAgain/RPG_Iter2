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
		if (item == null) return null;
		InventoryWeaponItem conflict = null;
		conflict = this.weapon;
		this.weapon = item;
		this.weapon.setEquipped(true);
		if (conflict != null)
			conflict.setEquipped(false);
		return conflict;
	}

	public InventoryWeaponItem unequip(InventoryWeaponItem item) {
		if (item == null) return null;
		InventoryWeaponItem temp = null;
		temp = this.weapon;
		this.weapon = null;
		temp.setEquipped(false);
		return temp;
	}

	public InventoryArmorItem equip(InventoryArmorItem item) {
		if (item == null) return null;
		InventoryArmorItem conflict = null;
		switch(item.getArmorItemType()) {
			case HELMET: {
				conflict = helmet;
				helmet = item;
				helmet.setEquipped(true);
				break;
			}
			case ARMOR: {
				conflict = armor;
				armor = item;
				armor.setEquipped(true);
				break;
			}
			case BOOTS: {
				conflict = boots;
				boots = item;
				boots.setEquipped(true);
				break;
			}
		}
		if (conflict != null)
			conflict.setEquipped(false);
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
		temp.setEquipped(false);
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
