package com.oopsididitagain.rpg_iter2.utils;

public enum WeaponItemType {
	STAFF("STAFF"),
	RANGED_WEAPON("RANGED_WEAPON"),
	ONE_HANDED_WEAPON("ONE_HANDED_WEAPON"),
	TWO_HANDED_WEAPON("TWO_HANDED_WEAPON"),
	FISTS("FISTS");
	
	private String desc;
	private WeaponItemType(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() { return desc; }
}
