package com.oopsididitagain.rpg_iter2.models.entities;
/**
 * Created by parango on 3/11/15.
 */

import java.util.HashMap;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class Avatar extends Entity implements SkilledEntity{

	private Map<String, Skill> map = new HashMap<String, Skill>();
	private Occupation occupation;

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



	@Override
	public Position getPostion() {
		return position;
	}
	
	

}
