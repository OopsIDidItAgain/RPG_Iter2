package com.oopsididitagain.rpg_iter2.models.menus;

import com.oopsididitagain.rpg_iter2.utils.Commands;

public class InventoryMenu {

	private int selectedOption = 0;

	public int getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}

	public void changeMenuOption(int keyCode, int numOfItems) {
		switch (keyCode) {
		case Commands.MOVE_NORTH:
			System.out.println("n");
			if (selectedOption < 4)
				;
			else
				selectedOption -= 4;
			break;
		case Commands.MOVE_SOUTH:
			System.out.println("s");
			System.out.println(selectedOption);
			System.out.println(numOfItems);
			if (selectedOption + 4 < numOfItems)
				selectedOption += 4;
			break;
		case Commands.MOVE_EAST:
			System.out.println("e");
			System.out.println(selectedOption);
			System.out.println(numOfItems);
			if (selectedOption < numOfItems) {
				selectedOption += 1;
			}
			break;
		case Commands.MOVE_WEST:
			System.out.println("w");
			System.out.println(selectedOption);
			System.out.println(numOfItems);
			if (selectedOption != 0)
				selectedOption -= 1;
			break;
		default:
			break;
		}
	}

}