package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;

public abstract class Occupation {
	public abstract void giveSkills(ArrayList<Skill> skillMap, ArrayList<Skill> fightSkillList, Map<String, Skill> passiveSkillList);
}
