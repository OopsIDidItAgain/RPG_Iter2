package com.oopsididitagain.rpg_iter2.models.Entities;
/**
 * Created by parango on 3/11/15.
 */

import java.util.HashMap;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Effects.Discount;
import com.oopsididitagain.rpg_iter2.models.Occupations.Occupation;
import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.models.Stats.StatCollection;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.StatModifiable;

public class Avatar extends Entity implements SkilledEntity, StatModifiable {

	private Map<String, Skill> map = new HashMap<String, Skill>();
	private Occupation occupation;
	private StatCollection stats;

	public Avatar(Position position) {
		super(position);
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
		giveBaseSkills();
		occupation.giveSkills(map);
	}

	private void giveBaseSkills() {
		//bargain
		Skill bargain = new Skill("bargain");
		Discount discount = new Discount(.05);
		bargain.setEffect(discount);
		addSkill(bargain);
		
		//observe
		//bind wounds
		
	}

	public Direction getDirection(){
		return position.getDirection();
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

	@Override
	public Skill getSkill(String skill) {
		return map.get(skill);
	}

	public void accept(InstantStatModifier modifier) {
		stats.mergeBlob(modifier.statBlob());
	}

	@Override
	public StatBlob statBlob() {
		return stats.getBlob();
	}
	

}
