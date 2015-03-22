package com.oopsididitagain.rpg_iter2.utils;

public enum Command {
	MOVE_NORTH("MOVE_NORTH"),
	MOVE_SOUTH("MOVE_SOUTH"),
	MOVE_EAST("MOVE_EAST"),
	MOVE_WEST("MOVE_WEST"),
	MOVE_NORTHEAST("MOVE_NORTHEAST"),
	MOVE_NORTHWEST("MOVE_NORTHWEST"),
	MOVE_SOUTHEAST("MOVE_SOUTHEAST"),
	MOVE_SOUTHWEST("MOVE_SOUTHWEST"),
	PAUSE("PAUSE"),
	ENTER("ENTER"),
	USE("USE"),
	INVENTORY("INVENTORY"),
	SKILLALLOCATION("SKILLALLOCATION"),
	EQUIP("EQUIP"),
	DROP("DROP"),
	SKILLONE("SKILLONE"),
	SKILLTWO("SKILLTWO"),
	SKILLTHREE("SKILLTHREE"),
	SKILLFOUR("SKILLFOUR"),
	SKILLFIVE("SKILLFIVE"),
	SKILLSIX("SKILLSIX"),
	EXIT("EXIT"),
	UNKNOWN("UNKNOWN");
	
	String identifier; 
	
	private Command(String identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public String toString() {
		return identifier;
	}
	
	public static Command fromString(String identifier) {
		switch(identifier) {
		case"MOVE_NORTH": return MOVE_NORTH;
		case"MOVE_SOUTH": return MOVE_SOUTH;
		case"MOVE_EAST": return MOVE_EAST;
		case"MOVE_WEST": return MOVE_WEST;
		case"MOVE_NORTHEAST": return MOVE_NORTHEAST;
		case"MOVE_NORTHWEST": return MOVE_NORTHWEST;
		case"MOVE_SOUTHEAST": return MOVE_SOUTHEAST;
		case"MOVE_SOUTHWEST": return MOVE_SOUTHWEST;
		case"ENTER": return ENTER;
		case"USE": return USE;
		case"DROP": return DROP;
		case"EQUIP": return EQUIP;
		case"SKILLONE": return SKILLONE;
		case"SKILLTWO": return SKILLTWO;
		case"SKILLTHREE": return SKILLTHREE;
		case"SKILLFOUR": return SKILLFOUR;
		case"SKILLFIVE": return SKILLFIVE;
		case"SKILLSIX": return SKILLSIX;
		case"EXIT": return EXIT;
		case"SKILLALLOCATION": return SKILLALLOCATION;
		}
		return UNKNOWN;
	}
}