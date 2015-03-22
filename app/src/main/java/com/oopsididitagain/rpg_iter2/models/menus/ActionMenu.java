package com.oopsididitagain.rpg_iter2.models.menus;

public class ActionMenu {
	public enum Option {
		Talk("Talk"), Attack("Attack"), Trade("Trade"), UseOption("Use Option"), Exit(
				"Exit");

		private String name;

		Option(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

	private Option currentOption;
	private boolean canAttack;

	public ActionMenu(boolean canAttack) {
		currentOption = Option.Talk;
		this.canAttack = canAttack;
	}

	public void previousOption() {
		switch (currentOption) {
		case Talk:
			break;
		case Attack:
			currentOption = Option.Talk;
			break;
		case Trade:
			if (canAttack)
				currentOption = Option.Attack;
			else
				currentOption = Option.Talk;
			break;
		case UseOption:
			currentOption = Option.Trade;
			break;
		case Exit:
			currentOption = Option.UseOption;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Talk:
			if (canAttack)
				currentOption = Option.Attack;
			else
				currentOption = Option.Trade;
			break;
		case Attack:
			currentOption = Option.Trade;
			break;
		case Trade:
			currentOption = Option.UseOption;
			break;
		case UseOption:
			currentOption = Option.Exit;
			break;
		default: // includes "Exit"
			break;
		}
	}

	public boolean canAttack() {
		return canAttack;
	}

	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	public Option[] getOptions() {
		return currentOption.getDeclaringClass().getEnumConstants();
	}

	public Option getCurrentOption() {
		return currentOption;
	}

	public void reset() {
		// for when you close the action menu
		currentOption = Option.Talk;
	}
}
