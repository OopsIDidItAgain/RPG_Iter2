package com.oopsididitagain.rpg_iter2.models.occupations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.Bane;
import com.oopsididitagain.rpg_iter2.models.effects.EntityStatusModifier;
import com.oopsididitagain.rpg_iter2.models.effects.Staff;
import com.oopsididitagain.rpg_iter2.models.effects.TimedStatModifier;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Command;

public class Summoner extends Occupation{

	@Override
	public void giveSkills(Avatar avatar) {
		// TODO Auto-generated method stub
		//enchantment active and fight
		Skill enchantment_sleep = new Skill(Skill.ENCHANTMENT);
		EntityStatus es = new EntityStatus(EntityStatus.SLEEPING);
		EntityStatusModifier mod = new EntityStatusModifier(es);
		enchantment_sleep.setEffect(mod);
		fightSkillList.add(enchantment_sleep);
		gameSkillList.add(enchantment_sleep);
		
		Skill enchantment_sad = new Skill(Skill.ENCHANTMENT);
		es = new EntityStatus(EntityStatus.SAD);
		mod = new EntityStatusModifier(es);
		enchantment_sad.setEffect(mod);
		fightSkillList.add(enchantment_sad);
		gameSkillList.add(enchantment_sad);
		
		Skill enchantment_badsmell = new Skill(Skill.ENCHANTMENT);
		es = new EntityStatus(EntityStatus.SMELL);
		mod = new EntityStatusModifier(es);
		enchantment_badsmell.setEffect(mod);
		fightSkillList.add(enchantment_badsmell);
		gameSkillList.add(enchantment_badsmell);
		
		//boon active and fight
		
		Skill boon_1= new Skill(Skill.BOON);
		StatBlob affectorStatBlob = new StatBlob(10, 0, 0, 0, 0, 0, 0, 0, 0);
		TimedStatModifier timedstatMod = new TimedStatModifier(affectorStatBlob,0);
		boon_1.setEffect(timedstatMod);
		fightSkillList.add(boon_1);
		gameSkillList.add(boon_1);
		
		Skill boon_2 = new Skill(Skill.BOON);
		affectorStatBlob = new StatBlob(0, 10, 0, 0, 0, 0, 0, 0, 0);
		timedstatMod = new TimedStatModifier(affectorStatBlob,0);
		boon_2.setEffect(timedstatMod);
		fightSkillList.add(boon_2);
		gameSkillList.add(boon_2);
		
		Skill boon_3 = new Skill(Skill.BOON);
		affectorStatBlob = new StatBlob(0, 0, 10, 0, 0, 0, 0, 0, 0);
		timedstatMod = new TimedStatModifier(affectorStatBlob,0);
		boon_3.setEffect(timedstatMod);
		fightSkillList.add(boon_3);
		gameSkillList.add(boon_3);
		//bane fight

		Skill bane_1= new Skill(Skill.BANE);
		affectorStatBlob = new StatBlob(-10, 0, 0, 0, 0, 0, 0, 0, 0);
		Bane bane = new Bane(affectorStatBlob,4);
		bane_1.setEffect(bane);
		fightSkillList.add(bane_1);
		gameSkillList.add(bane_1);
		
		Skill bane_2 = new Skill(Skill.BANE);
		affectorStatBlob = new StatBlob(0, -10, 0, 0, 0, 0, 0, 0, 0);
		bane = new Bane(affectorStatBlob,4);
		bane_2.setEffect(bane);
		fightSkillList.add(bane_2);
		gameSkillList.add(bane_2);
		
		Skill bane_3 = new Skill(Skill.BANE);
		affectorStatBlob = new StatBlob(0, 0, -10, 0, 0, 0, 0, 0, 0);
		bane = new Bane(affectorStatBlob,4);
		bane_3.setEffect(bane);
		fightSkillList.add(bane_3);
		gameSkillList.add(bane_3);
		
		//staff fight
		
		Skill staff = new Skill(Skill.STAFF);
		affectorStatBlob = new StatBlob(0, 0, 0, 0, 0, 0, 0, 0, 10);
		Staff staff_ = new Staff(affectorStatBlob, 1);
		staff.setEffect(staff_);
		gameSkillList.add(staff);
	}
	
	public LinkedList<String> getGameSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		skillStrings.add(Skill.BARGAIN);
		skillStrings.add(Skill.OBSERVATION);
		skillStrings.add(Skill.ENCHANTMENT);
		skillStrings.add(Skill.BOON);
		skillStrings.add(Skill.BANE);
		skillStrings.add(Skill.STAFF);
		return skillStrings;
	}
	public LinkedList<String>  getFightSkillListString() {
		LinkedList<String> skillStrings = new LinkedList<String>();
		skillStrings.add(Skill.OBSERVATION);
		skillStrings.add(Skill.ENCHANTMENT);
		skillStrings.add(Skill.BANE);
		skillStrings.add(Skill.BOON);
		skillStrings.add(Skill.STAFF);
		return skillStrings;
	}
	@Override
	public void increaseMultiplier(int selop){
		if(selop == 0){
			Skill skill = gameSkillList.get(selop);
			skill.increaseMultiplier();
			System.out.println(skill.getName() + " "  + skill.getMultiplier());
		}else if(selop == 1){
			Skill skill = gameSkillList.get(selop);
			skill.increaseMultiplier();
			System.out.println(skill.getName() + " "  + skill.getMultiplier());
		}
		else if(selop == 2){
			if(gameSkillList.size() >= 5){
				Skill skill = gameSkillList.get(2);
				skill.increaseMultiplier();
				System.out.println(skill.getName() + " "  + skill.getMultiplier());
				Skill skill1 = gameSkillList.get(3);
				skill1.increaseMultiplier();
				System.out.println(skill1.getName() + " "  + skill1.getMultiplier());
				Skill skill2 = gameSkillList.get(4);
				skill2.increaseMultiplier();
				System.out.println(skill2.getName() + " "  + skill2.getMultiplier());
			}
		}else if(selop == 3){
			if(gameSkillList.size() >= 8){
				Skill skill = gameSkillList.get(5);
				skill.increaseMultiplier();
				System.out.println(skill.getName() + " "  + skill.getMultiplier());
				Skill skill1 = gameSkillList.get(6);
				skill1.increaseMultiplier();
				System.out.println(skill1.getName() + " "  + skill1.getMultiplier());
				Skill skill2 = gameSkillList.get(7);
				skill2.increaseMultiplier();
				System.out.println(skill2.getName() + " "  + skill2.getMultiplier());
			}
			
		}else if(selop == 4){
			if(gameSkillList.size() >= 10){
				Skill skill = gameSkillList.get(8);
				skill.increaseMultiplier();
				System.out.println(skill.getName() + " "  + skill.getMultiplier());
				Skill skill1 = gameSkillList.get(9);
				skill1.increaseMultiplier();
				System.out.println(skill1.getName() + " "  + skill1.getMultiplier());
				Skill skill2 = gameSkillList.get(10);
				skill2.increaseMultiplier();
				System.out.println(skill2.getName() + " "  + skill2.getMultiplier());
			}
		}else if(selop == 5){
			Skill skill = gameSkillList.get(selop);
			skill.increaseMultiplier();
		}
	
	}
	
	public ArrayList<Skill> getTotalSkills(){
		ArrayList<Skill> list = new ArrayList<Skill>();
		list.add(gameSkillList.get(0));
		list.add(gameSkillList.get(1));
		list.add(gameSkillList.get(2));
		list.add(gameSkillList.get(5));
		list.add(gameSkillList.get(8));
		list.add(gameSkillList.get(11));
		return list;
	}

	public Skill getFightSkill(Command command) {
		Skill skill = null;
		Random random = new Random();
		int number;
		try {
			switch(command) {
			case SKILLONE: 
				number = random.nextInt(3) + 1;
				skill = getRandomFightSkill(number);
				break;
			case SKILLTWO:
				number = random.nextInt(6) + 4;
				skill = getRandomFightSkill(number);
				break;
			case SKILLTHREE:
				number = random.nextInt(9) + 7;
				skill = getRandomFightSkill(number);
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

	private Skill getRandomFightSkill(int tempcommand) {
		if(tempcommand < fightSkillList.size()){
			return fightSkillList.get(tempcommand);
		}
		return null;
	}
	private Skill getRandomActiveSkill(int tempcommand) {
		if(tempcommand < gameSkillList.size()){
			return gameSkillList.get(tempcommand);
		}
		return null;
	}

	@Override
	public Skill getActiveSkill(Command command) {
		Skill skill = null;
		Random random = new Random();
		int number;
		try {
			switch(command) {
			case SKILLONE: 
				skill = gameSkillList.get(0);
				break;
			case SKILLTWO: 
				skill = gameSkillList.get(1);
				break;
			case SKILLTHREE:
				number = random.nextInt(4 - 2 + 1) + 2;
				System.out.println(number);
				skill = getRandomActiveSkill(number);
				System.out.println(skill.getName());
				break;
			case SKILLFOUR:
				number = random.nextInt(7 - 5 + 1) + 5;
				System.out.println(number);
				skill = getRandomActiveSkill(number);
				System.out.println(skill.getName());
				break;
			case SKILLFIVE: 
				number = random.nextInt(10 - 8 + 1) + 8;
				System.out.println(number);
				skill = getRandomActiveSkill(number);
				System.out.println(skill.getName());
				break;
			case SKILLSIX: 
				skill = gameSkillList.get(11);
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

}
