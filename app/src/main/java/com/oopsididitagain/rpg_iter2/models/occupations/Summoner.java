package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;

public class Summoner extends Occupation{

	@Override
	public void giveSkills(ArrayList<Skill> skillMap,//START SKILL AT THREE AND FIGHT AT TWO
			ArrayList<Skill> fightSkillList, Map<String, Skill> passiveSkillList) {
		// TODO Auto-generated method stub
		//enchantment active and fight
		Skill enchantment = new Skill(Skill.ENCHANTMENT);
		EntityStatus es = new EntityStatus(EntityStatus.SLEEPING);
		EntityStatusModifier mod = new EntityStatusModifier(es);
		enchantment.setEffect(mod);
		fightSkillList.add(enchantment);
		skillMap.add(enchantment);
		
		//boon active and fight
		//bane fight
		//staff fight
	}

}
