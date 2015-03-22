package com.oopsididitagain.rpg_iter2.models.menus;

public class PauseMenu {
	public enum Option {
		Save("Save Game"), Load("Load Game"), Options("Options"), ExitGame(
				"Exit Game");

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

	public PauseMenu() {
		currentOption = Option.Save;
	}

	public void previousOption() {
		switch (currentOption) {
		case Save:
			break;
		case Load:
			currentOption = Option.Save;
			break;
		case Options:
			currentOption = Option.Load;
			break;
		case ExitGame:
			currentOption = Option.Options;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Save:
			currentOption = Option.Load;
			break;
		case Load:
			currentOption = Option.Options;
			break;
		case Options:
			currentOption = Option.ExitGame;
			break;
		case ExitGame:
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
