package com.oopsididitagain.rpg_iter2.models.menus;

import com.oopsididitagain.rpg_iter2.models.menus.PauseMenu.Option;

public class AvatarCreationMenu {
	
	public enum Option {
		Sneak("Sneak"), Summoner("Summoner"), Smasher("Smasher");

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


    public AvatarCreationMenu(){
        currentOption = Option.Sneak;
       
    }

	public void previousOption() {
		switch (currentOption) {
		case Sneak:
			break;
		case Summoner:
			currentOption = Option.Sneak;
			break;
		case Smasher:
			currentOption = Option.Summoner;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Sneak:
			currentOption = Option.Summoner;
			break;
		case Summoner:
			currentOption = Option.Smasher;
			break;
		case Smasher:
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
