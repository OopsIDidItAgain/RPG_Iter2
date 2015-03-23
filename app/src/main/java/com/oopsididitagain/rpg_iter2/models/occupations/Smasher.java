package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.WeaponBasedStatModifier;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class Smasher extends Occupation{


	@Override
	public void giveSkills(Avatar avatar) {
		//One handed passive
		StatBlob statblob = new StatBlob(0, 0, 10, 0, 0, 0, 0, 0, 0);
		WeaponBasedStatModifier wp = new WeaponBasedStatModifier(statblob, 0, WeaponItemType.ONE_HANDED_WEAPON);
		Skill oneHandedWeapon = new Skill(Skill.ONEHAND);
		oneHandedWeapon.setEffect(wp);
		passiveSkillArray.add(oneHandedWeapon);
		passiveSkillList.put(oneHandedWeapon.getName(),oneHandedWeapon);
		//TwoHanded passive
		
		wp = new WeaponBasedStatModifier(statblob, 0, WeaponItemType.TWO_HANDED_WEAPON);
		Skill twoHandedWeapon = new Skill(Skill.TWOHAND);
		twoHandedWeapon.setEffect(wp);
		passiveSkillArray.add(twoHandedWeapon);
		passiveSkillList.put(twoHandedWeapon.getName(),twoHandedWeapon);
		//Brawl passive
		wp = new WeaponBasedStatModifier(statblob, 0, WeaponItemType.FISTS);
		Skill brawl = new Skill(Skill.BRAWL);
		brawl.setEffect(wp);
		passiveSkillArray.add(brawl);
		passiveSkillList.put(brawl.getName(),brawl);
		//TODO
	
	}

	@Override
	public Skill getFightSkill(Command command) {
		Skill skill = null;
		try {
			switch(command) {
			case SKILLONE: skill = fightSkillList.get(0);
			break;
			case SKILLTWO: skill = fightSkillList.get(1);
			break;
			case SKILLTHREE: skill = fightSkillList.get(2);
			break;
			case SKILLFOUR: skill = fightSkillList.get(3);
			break;
			case SKILLFIVE: skill = fightSkillList.get(4);
			break;
			case SKILLSIX: skill = fightSkillList.get(5);
			break;
			default: return null;
			}
		} catch(Exception ex) {
			System.out.println("Probably tried to press a skill that wasn't existent!");
			ex.printStackTrace();
			return null;
		}
		return skill;
	}


	@Override
	public Skill getActiveSkill(Command command) {
		Skill skill = null;
		try {
			switch(command) {
			case SKILLONE: skill = gameSkillList.get(0);
			break;
			case SKILLTWO: skill = gameSkillList.get(1);
			break;
			case SKILLTHREE: skill = gameSkillList.get(2);
			break;
			case SKILLFOUR: skill = gameSkillList.get(3);
			break;
			case SKILLFIVE: skill = gameSkillList.get(4);
			break;
			case SKILLSIX: skill = gameSkillList.get(5);
			break;
			default: return null;
			}
		} catch(Exception ex) {
			System.out.println("Probably tried to press a skill that wasn't existent!");
			ex.printStackTrace();
			return null;
		}
		return skill;
	}

	public String toSaveableFormat() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Smasher\n");
		List<Skill> skills = getTotalSkills();
		for(int i = 0; i < skills.size(); ++i) {
			Skill skill = skills.get(i);
			sb.append(skill.getMultiplier());
			if (i != skills.size() - 1) 
				sb.append(",");
		}
		return sb.toString();
	}
	
}
