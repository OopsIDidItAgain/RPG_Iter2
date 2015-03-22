package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.utils.Assetable;

public class Decal implements Assetable {
	
	// question for the masses: is it possible to have a decal 
	// on the board without any area effect?? right now, they 
	// are only used as attributes of area effects.
	// 
	// - Danny
	
	private String id;
	
	public Decal(String id) {
		this.id = id;
	}
	
	public String getId() 			{ return id; }
	public void setId(String id) 	{ this.id = id; }




}
