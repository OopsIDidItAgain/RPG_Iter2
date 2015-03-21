package com.oopsididitagain.rpg_iter2.models.menus;

public class AvatarCreationMenu {
	private String options[];
	private int currentOption;

    public AvatarCreationMenu(){
        options = new String[]{"Sneak", "Summoner", "Smasher"};
        
        currentOption = 0;
       
    }
    
    public void setOption(int option){
    	
    	currentOption = option;
    	System.out.println(option);
    }
    
    public int getCurrentOption(){
    	
    	return currentOption;
    }
    public String getOptions(int i){
    	return options[i];

    }
}
