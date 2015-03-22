package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.PickPocket;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class Sneak extends Occupation{

	@Override
	public void giveSkills() {
		// TODO Auto-generated method stub
		
		//pickpocket active
		
		Skill pickpocket = new Skill(Skill.PICKPOCKET);
		PickPocket pick = new PickPocket();
		pickpocket.setEffect(pick);
		gameSkillList.add(pickpocket);
		
		//detectAndRemoveTrap active
		
		//creep active 
		
		//ranged weapon passive
		
	}

	@Override
	public Skill getFightSkill(Command command) {
		Skill skill = null;
		try {
			switch(command) {
			case SKILLONE: skill = fightSkillList.get(0);
			break;
			case SKILLTWO: skill = fightSkillList.get(1);
			break;
			case SKILLTHREE: skill = fightSkillList.get(2);
			break;
			case SKILLFOUR: skill = fightSkillList.get(3);
			break;
			case SKILLFIVE: skill = fightSkillList.get(4);
			break;
			case SKILLSIX: skill = fightSkillList.get(5);
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

	@Override
	public Skill getActiveSkill(Command command) {
		Skill skill = null;
		try {
			switch(command) {
			case SKILLONE: skill = gameSkillList.get(0);
			break;
			case SKILLTWO: skill = gameSkillList.get(1);
			break;
			case SKILLTHREE: skill = gameSkillList.get(2);
			break;
			case SKILLFOUR: skill = gameSkillList.get(3);
			break;
			case SKILLFIVE: skill = gameSkillList.get(4);
			break;
			case SKILLSIX: skill = gameSkillList.get(5);
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
