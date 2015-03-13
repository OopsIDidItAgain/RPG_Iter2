package com.oopsididitagain.rpg_iter2.models.Entities;
/**
 * Created by parango on 3/11/15.
 */

import java.util.HashMap;
import java.util.Map;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Occupations.Occupation;

public class Avatar extends Entity {

	private Map<String, Skill> map = new HashMap<String, Skill>();
	private Occupation occupation;

	public Avatar(Position position) {
		super(position);
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void addSkill(Skill s) {
		map.put(s.getName(), s);
	}

	public Map<String, Skill> getSkills() {
		return this.map;
	}

}
