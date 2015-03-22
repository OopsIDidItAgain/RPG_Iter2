package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.utils.Command;

public abstract class Occupation {
	public abstract void giveSkills(Avatar avatar);
	
	protected ArrayList<Skill> gameSkillList = new ArrayList<Skill>();
	protected ArrayList<Skill> fightSkillList = new ArrayList<Skill>();
	protected ArrayList<Skill> passiveSkillArray = new ArrayList<Skill>();
	protected Map<String,Skill> passiveSkillList = new HashMap<String,Skill>();
	
	public LinkedList<String> getGameSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		for(Skill s: gameSkillList){
			skillStrings.add(s.getName());
		}
		return skillStrings;
	}
	public LinkedList<String> getFightSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		for(Skill s: fightSkillList){
			skillStrings.add(s.getName());
		}
		return skillStrings;
	}
	public LinkedList<String> getPassiveSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		for(Skill s: passiveSkillArray){
			skillStrings.add(s.getName());
		}
		return skillStrings;
	}
	
	public ArrayList<Skill> getGameSkillList() {
		return gameSkillList;
	}
	public ArrayList<Skill> getFightSkillList() {
		return fightSkillList;
	}
	public ArrayList<Skill> getFightSkillArray() {
		return passiveSkillArray;
	}
	public Map<String, Skill> getPassiveSkillList() {
		return passiveSkillList;
	}
	public ArrayList<Skill> getPassiveSkillArray() {
		return passiveSkillArray;
	}
	public Skill getPassiveSkill(String skill) {
		return passiveSkillList.get(skill);
	}
	public abstract Skill getFightSkill(Command command);
	public abstract Skill getActiveSkill(Command command);
	public void increaseMultiplier(int selop) {
		ArrayList<Skill> totalSkills = getTotalSkills();
		if(selop < totalSkills.size() && selop >= 0){
			Skill skill = gameSkillList.get(selop);
			skill.increaseMultiplier();
			System.out.println(skill.getName() + " "  + skill.getMultiplier());
		}
	}
	public ArrayList<Skill> getTotalSkills(){
		ArrayList<Skill> totalSkills = new ArrayList<Skill>();
		totalSkills.addAll(gameSkillList);
		totalSkills.addAll(passiveSkillArray);
		return totalSkills;
	}
	
	

	
	
}
