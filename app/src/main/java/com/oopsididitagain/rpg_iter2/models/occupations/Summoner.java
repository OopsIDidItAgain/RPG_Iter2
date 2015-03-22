package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class Summoner extends Occupation{

	@Override
	public void giveSkills() {
		// TODO Auto-generated method stub
		//enchantment active and fight
		Skill enchantment_sleep = new Skill(Skill.ENCHANTMENT);
		EntityStatus es = new EntityStatus(EntityStatus.SLEEPING);
		EntityStatusModifier mod = new EntityStatusModifier(es);
		enchantment_sleep.setEffect(mod);
		fightSkillList.add(enchantment_sleep);
		gameSkillList.add(enchantment_sleep);
		
		Skill enchantment_sad = new Skill(Skill.ENCHANTMENT);
		es = new EntityStatus(EntityStatus.SAD);
		mod = new EntityStatusModifier(es);
		enchantment_sad.setEffect(mod);
		fightSkillList.add(enchantment_sad);
		gameSkillList.add(enchantment_sad);
		
		Skill enchantment_badsmell = new Skill(Skill.ENCHANTMENT);
		es = new EntityStatus(EntityStatus.SMELL);
		mod = new EntityStatusModifier(es);
		enchantment_badsmell.setEffect(mod);
		fightSkillList.add(enchantment_badsmell);
		gameSkillList.add(enchantment_badsmell);
		
		//boon active and fight
		//bane fight
		//staff fight
	}
	
	public LinkedList<String> getGameSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		skillStrings.add(Skill.OBSERVATION);
		skillStrings.add(Skill.ENCHANTMENT);
		skillStrings.add(Skill.BANE);
		skillStrings.add(Skill.BOON);
		skillStrings.add(Skill.STAFF);
		return skillStrings;
	}
	public LinkedList<String>  getFightSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		skillStrings.add(Skill.OBSERVATION);
		skillStrings.add(Skill.ENCHANTMENT);
		skillStrings.add(Skill.BANE);
		skillStrings.add(Skill.BOON);
		skillStrings.add(Skill.STAFF);
		return skillStrings;
	}
	@Override
	public void increaseMultiplier(int selop){
		if(selop == 0){
			Skill skill = gameSkillList.get(selop);
			skill.increaseMultiplier();
		}else if(selop == 1){
			if(gameSkillList.size() >= 4){
				Skill skill = gameSkillList.get(1);
				skill.increaseMultiplier();
				Skill skill1 = gameSkillList.get(2);
				skill.increaseMultiplier();
				Skill skill2 = gameSkillList.get(3);
				skill.increaseMultiplier();
			}
		}else if(selop == 2){
			if(gameSkillList.size() >= 7){
				Skill skill = gameSkillList.get(4);
				skill.increaseMultiplier();
				Skill skill1 = gameSkillList.get(5);
				skill.increaseMultiplier();
				Skill skill2 = gameSkillList.get(6);
				skill.increaseMultiplier();
			}
			
		}else if(selop == 3){
			if(gameSkillList.size() >= 9){
				Skill skill = gameSkillList.get(7);
				skill.increaseMultiplier();
				Skill skill1 = gameSkillList.get(8);
				skill.increaseMultiplier();
				Skill skill2 = gameSkillList.get(9);
				skill.increaseMultiplier();
			}

		}
		
	}
	public Skill getFightSkill(Command command) {
		Skill skill = null;
		Random random = new Random();
		int number;
		try {
			switch(command) {
			case SKILLONE: 
				number = random.nextInt(3) + 1;
				skill = getRandomFightSkill(number);
				break;
			case SKILLTWO:
				number = random.nextInt(6) + 4;
				skill = getRandomFightSkill(number);
				break;
			case SKILLTHREE:
				number = random.nextInt(9) + 7;
				skill = getRandomFightSkill(number);
				break;
			default: return null;
			}
		} catch(Exception ex) {
			System.out.println("Probably tried to press a skill that wasn't existent!");
			ex.printStackTrace();
			return null;
		}
		return skill;

	}

	private Skill getRandomFightSkill(int tempcommand) {
		if(tempcommand < fightSkillList.size()){
			return fightSkillList.get(tempcommand);
		}
		return null;
	}
	private Skill getRandomActiveSkill(int tempcommand) {
		if(tempcommand < gameSkillList.size()){
			return gameSkillList.get(tempcommand);
		}
		return null;
	}

	@Override
	public Skill getActiveSkill(Command command) {
		Skill skill = null;
		Random random = new Random();
		int number;
		try {
			switch(command) {
			case SKILLONE: 
				skill = gameSkillList.get(0);
				break;
			case SKILLTWO:
				number = random.nextInt(3) + 1;
				System.out.println(number);
				if(number == 1){
					System.out.println("hi");
				}
				skill = getRandomActiveSkill(number);
				System.out.println(skill);
				break;
			case SKILLTHREE:
				number = random.nextInt(6) + 4;
				skill = getRandomActiveSkill(number);
				break;
			case SKILLFOUR: 
				number = random.nextInt(9) + 7;
				skill = getRandomActiveSkill(number);
				break;
			default: return null;
			}
		} catch(Exception ex) {
			System.out.println("Probably tried to press a skill that wasn't existent!");
			ex.printStackTrace();
			return null;
		}
		return skill;
	}

}
