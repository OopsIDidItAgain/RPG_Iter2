package com.oopsididitagain.rpg_iter2.models.menus;





public class MainMenu {
	
	public enum Option {
		Tutorial("Tutorial"), New("New Game"), Load("Load Game"), ExitGame(
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

	public MainMenu() {
		currentOption = Option.Tutorial;
	}

	public void previousOption() {
		switch (currentOption) {
		case Tutorial:
			currentOption = Option.ExitGame;
			break;
		case New:
			currentOption = Option.Tutorial;
			break;
		case Load:
			currentOption = Option.New;
			break;
		case ExitGame:
			currentOption = Option.Load;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Tutorial:
			currentOption = Option.New;
			break;
		case New:
			currentOption = Option.Load;
			break;
		case Load:
			currentOption = Option.ExitGame;
			break;
		case ExitGame:
			currentOption = Option.Tutorial;
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
