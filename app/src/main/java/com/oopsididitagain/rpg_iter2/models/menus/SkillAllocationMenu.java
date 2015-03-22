package com.oopsididitagain.rpg_iter2.models.menus;

import com.oopsididitagain.rpg_iter2.utils.Command;

public class SkillAllocationMenu{
	private int length;
	private int selectedOption = 0;
	public SkillAllocationMenu(int length){
		this.length = length;
	}
	public void changeMenuOption(Command command) {
		switch (command) {
		case MOVE_NORTH:
			if (selectedOption < length-1) {
				selectedOption += 1;
			}
			break;
		case MOVE_SOUTH:
			if (selectedOption > 0) {
				selectedOption -= 1;
			}
			break;
		default:
			break;
		}
	}
	public int getMenuOption(){
		return selectedOption;
	}
	

}
