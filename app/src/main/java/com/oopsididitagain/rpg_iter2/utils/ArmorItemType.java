package com.oopsididitagain.rpg_iter2.utils;

public enum ArmorItemType {
	BOOTS("BOOTS"),
	ARMOR("ARMOR"),
	HELMET("HELMET");
	
	private String desc;
	private ArmorItemType(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() { return desc; }
}
