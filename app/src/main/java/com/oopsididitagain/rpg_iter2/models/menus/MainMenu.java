package com.oopsididitagain.rpg_iter2.models.menus;




public class MainMenu {
	private String options[];
	private int selectedOption;

    public MainMenu(){
        options = new String[]{"New Game", "Load Game", "Controls"};

        selectedOption = 0;
       
    }
    
    public void selectedOptionUp(){
    	if(selectedOption > 0 )
            selectedOption -=1;
    }

    public void selectedOptionDown(){
        if(selectedOption < options.length-1 )
            selectedOption +=1;
    }
    
    public int getSelectedOptionIndex(){
    	
    	return selectedOption;
    }

    public String getSelectedOption(){

        return options[selectedOption];
    }

    public String getOption(int i){
    	return options[i];

    }

    public int getNumOfOptions(){
        return  options.length;
    }


}
