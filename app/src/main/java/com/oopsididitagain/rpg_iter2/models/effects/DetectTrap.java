package com.oopsididitagain.rpg_iter2.models.effects;

import com.oopsididitagain.rpg_iter2.models.MiniMap;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.Trap;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.probes.SkillProbe;

public class DetectTrap implements Effect{
	int radius;
	int baseRadius = 5;
	int removeRadius;
	int baseRemoveRadius = 2;
	@Override
	public void applyMultiplier(int m) {
		radius = baseRadius * m;
		removeRadius = baseRadius * m;
	}

	@Override
	public int getRadius() {
		return radius;
	}

	@Override
	public void applySkill(Avatar avatar, MiniMap tiles, SkillProbe skillProbe) {
		skillProbe.setUpSkill(this, avatar, tiles);
		skillProbe.setUpSkill(this, avatar, tiles,removeRadius);
	}

	public void detectTrap(Trap t) {
		t.detect();
	}

	
	public void removeTrap(Trap trap, Tile currentTile) {
		currentTile.remove(trap);
	}

}
