package com.oopsididitagain.rpg_iter2.models;

import java.util.LinkedList;

public class Storyline {
	private LinkedList<String> storyline = new LinkedList<String>();
	
	public Storyline(){
		
		storyline.add("Hi, I'm the default Storyline() constructor");
	}
	public Storyline(String story){
		this.storyline.add(story);
	}
	public String printStory(){
		StringBuilder sb = new StringBuilder();
		for(String s: storyline){
			sb.append(s);
		}
		return sb.toString();
	}
	

}
