package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
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
		Skill enchantment = new Skill(Skill.ENCHANTMENT);
		EntityStatus es = new EntityStatus(EntityStatus.SLEEPING);
		EntityStatusModifier mod = new EntityStatusModifier(es);
		enchantment.setEffect(mod);
		fightSkillList.add(enchantment);
		gameSkillList.add(enchantment);
		
		//boon active and fight
		//bane fight
		//staff fight
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
