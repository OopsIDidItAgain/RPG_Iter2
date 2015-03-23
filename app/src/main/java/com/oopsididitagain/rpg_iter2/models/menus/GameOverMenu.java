package com.oopsididitagain.rpg_iter2.models.menus;

public class GameOverMenu {
	
	public enum Option {
		StartOver("Start Over"), ExitGame("Exit Game");

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

	public GameOverMenu() {
		currentOption = Option.StartOver;
	}

	public void previousOption() {
		switch (currentOption) {
		case StartOver:
			currentOption = Option.ExitGame;
			break;
		case ExitGame:
			currentOption = Option.StartOver;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case StartOver:
			currentOption = Option.ExitGame;
			break;
		case ExitGame:
			currentOption = Option.StartOver;
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
