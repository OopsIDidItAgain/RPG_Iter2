package com.oopsididitagain.rpg_iter2.models.stats;

import com.oopsididitagain.rpg_iter2.models.Armory;


public class StatCollection {
	//Armory of Entity in order to use it's equipped weapons and armor to calculate some stats
	private Armory armory;

	private StatBlob blob;
	
	//derived stats
	private Stat level;
	private Stat lifeCapacity;
	private Stat manaCapacity;
	private Stat offensiveRating;
	private Stat defensiveRating;
	private Stat armorRating;
	private int unusedSkillPoints = 0;
	
	public StatCollection(Armory armory, StatBlob blob) {
		this.blob = blob;
		this.armory = armory;
		deriveStats();
	}
	
	public StatCollection(Armory armory) {
		this.armory = armory;
		this.blob = new StatBlob(3, 0, 0, 0, 0, 0, 0, 20, 25);
		deriveStats();
	}
	
	private void deriveStats() {
		//derived
		if(level == null){
			level = new Stat(this.blob.getExperience() * .1);
		}
		double beforeLevel = getLevel();
		level = new Stat(this.blob.getExperience() * .1);
		double afterLevel = getLevel();
		if(afterLevel > beforeLevel){
			++unusedSkillPoints;
		}
		lifeCapacity = new Stat(20 + (0.5 * this.blob.getHardiness()));
		manaCapacity = new Stat(25.0 + this.blob.getIntellect() * this.level.getValue());
		offensiveRating = new Stat((blob.getStrength() + armory.getEquippedWeaponRank()) * this.level.getValue()); 
		defensiveRating = new Stat(blob.getAgility() * this.level.getValue());
		armorRating = new Stat(blob.getHardiness()  * armory.getEquippedArmorRank()); 
		//System.out.println(blob);
	}

	public StatBlob getBlob() {
		return blob;
	}
	
	public void setBlob(StatBlob blob) {
		this.blob = blob;
	}

	public double getLevel() {
		return Math.floor(level.getValue());
	}
	
	public boolean die() {
		// TRUE = game keeps going.
		// FALSE = no lives left. game over.
		this.blob.getLifeAmountStat().setValue(this.blob.getLivesLeft() == 0 ? 0 : lifeCapacity.getValue());
		if(this.blob.getLivesLeft() > 0) {
			this.blob.getLivesLeftStat().subtract(1);
			return true;
		}
		return false;
	}
	
	public void mergeBlob(StatBlob blob) {
		this.blob.merge(blob);
		// has to be handled here because it contains both primary and derived stats
		/*if(this.blob.getLifeAmount() <= 0) {
			//if(this.blob.getLivesLeft() > 0) {
				//this.blob.getLivesLeftStat().subtract(1);
			//}
			//this.blob.getLifeAmountStat().setValue(this.blob.getLivesLeft() == 0 ? 0 : lifeCapacity.getValue());
			
		} else */
		// if ((int)this.blob.getLifeAmount() <= 0) dyingLogic();
		System.out.println("hdF" + blob.getLifeAmount());
		if(this.blob.getLifeAmount() > lifeCapacity.getValue()) {
			blob.setLivesLeft(blob.getLivesLeft() + 1);
			this.blob.getLifeAmountStat().setValue(lifeCapacity.getValue());
		}else if(this.blob.getLifeAmount() <= 0) {
			blob.setLivesLeft(blob.getLivesLeft() - 1);
			this.blob.getLifeAmountStat().setValue(lifeCapacity.getValue());
		}
				
		deriveStats();
	}
	public void detachBlob(StatBlob blob) {
		this.blob.detach(blob);
		if(this.blob.getLifeAmount() > lifeCapacity.getValue()) {
			blob.setLivesLeft(blob.getLivesLeft() + 1);
			this.blob.getLifeAmountStat().setValue(lifeCapacity.getValue());
		}else if(this.blob.getLifeAmount() <= 0) {
			blob.setLivesLeft(blob.getLivesLeft() - 1);
			this.blob.getLifeAmountStat().setValue(lifeCapacity.getValue());
		}
		deriveStats();
	}

	public double getMana() {
		return manaCapacity.getValue();
	}
	
	public double getOffensiveRating() {
		return offensiveRating.getValue();
	}

	public double getDefensiveRating() {
		return defensiveRating.getValue();
	}

	public double getArmorRating() {
		return armorRating.getValue();
	}

	public double getLifeCapacity() {
		return lifeCapacity.getValue();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("PRIMARY STATS\n");
		sb.append(blob.toString() + "\n");
		sb.append("DERIVED STATS\n");
		sb.append("Level: " + level + "\n");
		sb.append("Life Capacity: " + lifeCapacity + "\n");
		sb.append("Mana Capacity: " + manaCapacity + "\n");
		sb.append("Offensive Rating: " + offensiveRating + "\n");
		sb.append("Defensive Rating: " + defensiveRating + "\n");
		sb.append("Armor Rating: " + armorRating + "\n");
		return sb.toString();
	}
	
	public String[] primaryStatArray() {
		String[] stats = {
				"PRIMARY STATS",
				"Lives left: "	+(int)blob.getLivesLeft(),
				"Intellect: "	+(int)blob.getIntellect(),
				"Strength: "	+(int)blob.getStrength(),
				"Agility: "		+(int)blob.getAgility(),
				"Hardiness: "	+(int)blob.getExperience(),
				"Movement: "	+(int)blob.getMovement(),
				"Experience: "	+(int)blob.getExperience(),
				"Life: "		+(int)blob.getLifeAmount()+
								"/"+(int)getLifeCapacity(),
				"Mana: "		+(int)blob.getManaAmount()+
								"/"+(int)getMana()
		};
		return stats;
	}
	
	public String[] derivedStatArray() {
		String[] stats = {
				"SECONDARY STATS",
				"Level: "			+(int)getLevel(),
				"Offensive Rating: "+(int)getOffensiveRating(),
				"DefensiveRating: "	+(int)getDefensiveRating(),
				"Armor Rating: "	+(int)getArmorRating()
		};
		return stats;
	}

	public void minusUnusedSkillPoints() {
		unusedSkillPoints--;
	}

	public int getUnusedPoints() {
		return unusedSkillPoints;
	}

	public int getMovementSpeed() {
		if(blob.getMovement() < 2){
			return 1;
		}else{
			return (int) Math.floor(blob.getMovement());
		}
	}

	public void lowerMovementSpeedBy(int speedDifference) {
		blob.lowerMovementSpeedBy(speedDifference);
	}
	public void raiseMovementSpeedBy(int speedDifference) {
		blob.raiseMovementSpeedBy(speedDifference);
	}


	public void removeLife() {
		this.blob.getLifeAmountStat().setValue(lifeCapacity.getValue());
		this.blob.getLivesLeftStat().subtract(1);
	}
		

	public int getLifeAmount() {
		return (int) blob.getLifeAmount();
	}

	public int getLivesLeft() {
		return blob.getLivesLeft();

	}

}


