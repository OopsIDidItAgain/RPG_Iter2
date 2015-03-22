package com.oopsididitagain.rpg_iter2.models.menus;


public class ActionMenu {
	public enum Option {
		Talk("Talk"), Attack("Attack"), UseSkill("Use Skill"), UseOption(
				"Use Option");

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

	public ActionMenu() {
		currentOption = Option.Talk;
	}

	public void previousOption() {
		switch (currentOption) {
		case Talk:
			break;
		case Attack:
			currentOption = Option.Talk;
			break;
		case UseSkill:
			currentOption = Option.Attack;
			break;
		case UseOption:
			currentOption = Option.UseSkill;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Talk:
			currentOption = Option.Attack;
			break;
		case Attack:
			currentOption = Option.UseSkill;
			break;
		case UseSkill:
			currentOption = Option.UseOption;
			break;
		case UseOption:
			break;
		default:
			break;
		}
	}

	public Option[] getOptions() {
		return currentOption.getDeclaringClass().getEnumConstants();
	}
	
	public Option getCurrentOption() {
		return currentOption;
	}
}
