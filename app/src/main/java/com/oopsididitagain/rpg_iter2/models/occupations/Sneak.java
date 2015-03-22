package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.PickPocket;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;

public class Sneak extends Occupation{

	@Override
	public void giveSkills(ArrayList<Skill> skillMap,//START SKILL AT THREE AND FIGHT AT TWO
			ArrayList<Skill> fightSkillList, Map<String, Skill> passiveSkillList) {
		// TODO Auto-generated method stub
		
		//pickpocket active
		
		Skill pickpocket = new Skill(Skill.PICKPOCKET);
		PickPocket pick = new PickPocket();
		pickpocket.setEffect(pick);
		skillMap.add(pickpocket);
		
		//detectAndRemoveTrap active
		
		//creep active 
		
		//ranged weapon passive
		
	}





}
