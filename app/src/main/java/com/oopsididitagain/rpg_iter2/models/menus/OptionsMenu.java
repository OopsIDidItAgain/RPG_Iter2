package com.oopsididitagain.rpg_iter2.models.menus;

public class OptionsMenu {

	public enum Option {
		Graphics("Graphics Settings"), Audio("Audio Settings"), Keyboard("Keyboard Input"), Back("Back");

		public enum Commands {
			
		}
		
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


    public OptionsMenu(){
        currentOption = Option.Graphics;
       
    }

	public void previousOption() {
		switch (currentOption) {
		case Graphics:
			break;
		case Audio:
			currentOption = Option.Graphics;
			break;
		case Keyboard:
			currentOption = Option.Audio;
			break;
		case Back:
			currentOption = Option.Keyboard;
			break;
		default:
			break;
		}
	}

	public void nextOption() {
		switch (currentOption) {
		case Graphics:
			currentOption = Option.Audio;
			break;
		case Audio:
			currentOption = Option.Keyboard;
			break;
		case Keyboard:
			currentOption = Option.Back;
			break;
		case Back:
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
