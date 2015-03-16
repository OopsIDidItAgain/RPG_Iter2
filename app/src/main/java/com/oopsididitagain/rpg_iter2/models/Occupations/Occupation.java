package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.Map;
import com.oopsididitagain.rpg_iter2.models.Skill;

public abstract class Occupation {
	public abstract void giveSkills(Map<String,Skill> skillMap);
}
