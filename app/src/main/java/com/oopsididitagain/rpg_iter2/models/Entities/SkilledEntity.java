package com.oopsididitagain.rpg_iter2.models.Entities;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public interface SkilledEntity {
	public Skill getSkill(String skill);

	public Direction getDirection();

	public Position getPostion();

}
