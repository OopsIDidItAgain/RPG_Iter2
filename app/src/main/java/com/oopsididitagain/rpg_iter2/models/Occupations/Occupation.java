package com.oopsididitagain.rpg_iter2.models.Occupations;

import java.util.Map;
import com.oopsididitagain.rpg_iter2.models.Skill;

public abstract class Occupation {
	public abstract void giveSkills(Map<String,Skill> skillMap);
}
