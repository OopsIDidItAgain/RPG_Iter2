package com.oopsididitagain.rpg_iter2.models.menus;

import com.oopsididitagain.rpg_iter2.utils.Command;

public class KeyboardConfigMenu {

	private Command selected;
	private boolean configuring;

	public KeyboardConfigMenu() {
		selected = Command.MOVE_NORTH;

	}

	public void nextOption() {
		switch (selected) {
		case MOVE_NORTH:
			selected = Command.MOVE_SOUTH;
			break;
		case MOVE_SOUTH:
			selected = Command.MOVE_EAST;
			break;
		case MOVE_EAST:
			selected = Command.MOVE_WEST;
			break;
		case MOVE_WEST:
			selected = Command.MOVE_NORTHEAST;
			break;
		case MOVE_NORTHEAST:
			selected = Command.MOVE_NORTHWEST;
			break;
		case MOVE_NORTHWEST:
			selected = Command.MOVE_SOUTHEAST;
			break;
		case MOVE_SOUTHEAST:
			selected = Command.MOVE_SOUTHWEST;
			break;
		case MOVE_SOUTHWEST:
			selected = Command.PAUSE;
			break;
		case PAUSE:
			selected = Command.ENTER;
			break;
		case ENTER:
			selected = Command.USE;
			break;
		case USE:
			selected = Command.INVENTORY;
			break;
		case INVENTORY:
			selected = Command.EQUIP;
			break;
		case EQUIP:
			selected = Command.DROP;
			break;
		case DROP:
			selected = Command.SKILLONE;
			break;
		case SKILLONE:
			selected = Command.SKILLTWO;
			break;
		case SKILLTWO:
			selected = Command.SKILLTHREE;
			break;
		case SKILLTHREE:
			selected = Command.SKILLFOUR;
			break;
		case SKILLFOUR:
			selected = Command.SKILLFIVE;
			break;
		case SKILLFIVE:
			selected = Command.SKILLSIX;
			break;
		case SKILLSIX:
			selected = Command.EXIT;
			break;
		case EXIT:
			break;
		default:
			break;
		}
	}

	public void previousOption() {
		switch (selected) {
		case MOVE_NORTH:
			break;
		case MOVE_SOUTH:
			selected = Command.MOVE_NORTH;
			break;
		case MOVE_EAST:
			selected = Command.MOVE_SOUTH;
			break;
		case MOVE_WEST:
			selected = Command.MOVE_EAST;
			break;
		case MOVE_NORTHEAST:
			selected = Command.MOVE_WEST;
			break;
		case MOVE_NORTHWEST:
			selected = Command.MOVE_NORTHEAST;
			break;
		case MOVE_SOUTHEAST:
			selected = Command.MOVE_NORTHWEST;
			break;
		case MOVE_SOUTHWEST:
			selected = Command.MOVE_SOUTHEAST;
			break;
		case PAUSE:
			selected = Command.MOVE_SOUTHWEST;
			break;
		case ENTER:
			selected = Command.PAUSE;
			break;
		case USE:
			selected = Command.ENTER;
			break;
		case INVENTORY:
			selected = Command.USE;
			break;
		case EQUIP:
			selected = Command.INVENTORY;
			break;
		case DROP:
			selected = Command.EQUIP;
			break;
		case SKILLONE:
			selected = Command.DROP;
			break;
		case SKILLTWO:
			selected = Command.SKILLONE;
			break;
		case SKILLTHREE:
			selected = Command.SKILLTWO;
			break;
		case SKILLFOUR:
			selected = Command.SKILLTHREE;
			break;
		case SKILLFIVE:
			selected = Command.SKILLFOUR;
			break;
		case SKILLSIX:
			selected = Command.SKILLFIVE;
			break;
		case EXIT:
			selected = Command.SKILLSIX;
			break;
		default:
			break;
		}
	}

	public Command[] getOptions() {
		return selected.getDeclaringClass().getEnumConstants();
	}

	public Command getCurrentOption() {
		return selected;
	}

	public boolean isSelected() {
		return configuring;
	}

	public void toggleSelect() {
		if (configuring == true)
			configuring = false;
		else
			configuring = true;
	}

}
