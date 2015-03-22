package com.oopsididitagain.rpg_iter2.models.stats;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;


public class StatBlob {

	public final static StatBlob SNEAK_DEFAULT = new StatBlob(0, 10, 50, 20, 20, 0, 50, 0, 0);
	public final static StatBlob SMASHER_DEFAULT = new StatBlob(0, 50, 20, 10, 40, 0, 25, 0, 0);
	public final static StatBlob SUMMONER_DEFAULT = new StatBlob(0, 15, 20, 50, 10, 0, 25, 0, 0);
	
	//primary stats
	private Stat livesLeft;
	private Stat intellect;
	private Stat strength;
	private Stat agility;
	private Stat hardiness;
	private Stat experience;
	private Stat movement;
	private Stat lifeAmount;
	private Stat manaAmount;
	private HashMap<Integer,Stat> statMap = new HashMap<Integer,Stat>();
	private HashMap<Integer,String> statStringMap = new HashMap<Integer,String>();
	
	public StatBlob(double livesLeft, double strength, double agility, 
			double intellect, double hardiness, double experience, double movement,
			double lifeAmount, double manaAmount) {
		this.livesLeft = new Stat(livesLeft);
		statMap.put(1, this.livesLeft);
		statStringMap.put(1,"Lives Left");
		this.strength = new Stat(strength);
		statMap.put(2, this.strength);
		statStringMap.put(2,"Strength");
		this.agility = new Stat(agility);
		statMap.put(3, this.agility);
		statStringMap.put(3,"Agility");
		this.intellect = new Stat(intellect);
		statMap.put(4, this.intellect);
		statStringMap.put(4,"Intellect");
		this.hardiness = new Stat(hardiness);
		statMap.put(5, this.hardiness);
		statStringMap.put(5,"Hardiness");
		this.experience = new Stat(experience);
		statMap.put(6, this.experience);
		statStringMap.put(6,"Experience");
		this.movement = new Stat(movement);
		statMap.put(7, this.movement);
		statStringMap.put(7,"Movement");
		this.lifeAmount = new Stat(lifeAmount);
		statMap.put(8, this.lifeAmount);
		statStringMap.put(8,"Life Amount");
		this.manaAmount = new Stat(manaAmount);
		statMap.put(9, this.manaAmount);
		statStringMap.put(9,"Mana Amount");
	
	}
	
	public StatBlob(StatBlob other) {
		this.livesLeft = new Stat(other.livesLeft.getValue());
		this.strength = new Stat(other.strength.getValue());
		this.agility = new Stat(other.agility.getValue());
		this.intellect = new Stat(other.intellect.getValue());
		this.hardiness = new Stat(other.hardiness.getValue());
		this.experience = new Stat(other.experience.getValue());
		this.movement = new Stat(other.movement.getValue());
		this.lifeAmount = new Stat(other.lifeAmount.getValue());
		this.manaAmount = new Stat(other.manaAmount.getValue());
	}
	
	public int getLivesLeft(){
		return (int)livesLeft.getValue();
	}

	public double getIntellect(){
		return intellect.getValue();
	}

	public double getStrength(){
		return strength.getValue();
	}

	public double getAgility(){
		return agility.getValue();
	}

	public double getHardiness(){
		return hardiness.getValue();
	}

	public double getExperience(){
		return experience.getValue();
	}
	
	public double getMovement() {
		return movement.getValue();
	}

	public double getManaAmount() {
		return manaAmount.getValue();
	}

	public double getLifeAmount() {
		return lifeAmount.getValue();
	}
	
	public LinkedList<String> getRandomStats(int numberOfStats){
		Random random = new Random();
		LinkedList<String> stats = new LinkedList<String>();
		for(int i = 0; i!= numberOfStats; ++i){
			int number = random.nextInt(9) + 1;
			String statType = (statStringMap.get(number));
			String statNumber = (statMap.get(number)).toString();
			String finalStat = statType + ": " + statNumber;
			stats.add(finalStat);
		}
		return stats;
	}
	
	/* RETURN STAT METHODS */
	public Stat getLivesLeftStat(){
		return livesLeft;
	}

	public Stat getIntellectStat(){
		return intellect;
	}

	public Stat getStrengthStat(){
		return strength;
	}

	public Stat getAgilityStat(){
		return agility;
	}

	public Stat getHardinessStat(){
		return hardiness;
	}

	public Stat getExperienceStat(){
		return experience;
	}
	
	public Stat getMovementStat() {
		return movement;
	}

	public Stat getManaAmountStat() {
		return manaAmount;
	}

	public Stat getLifeAmountStat() {
		return lifeAmount;
	}
	
	public void merge(StatBlob other) {
		
		this.livesLeft.add(other.livesLeft);
		
		if (this.lifeAmount.getValue() + other.lifeAmount.getValue() > 0) // aka you don't die
			this.lifeAmount.add(other.lifeAmount);
			// exceptions are handled in StatCollection class's mergeBlob() method
		this.lifeAmount.add(other.lifeAmount);
		this.intellect.add(other.intellect);
		this.strength.add(other.strength);
		this.agility.add(other.agility);
		this.hardiness.add(other.hardiness);
		this.experience.add(other.experience);
		this.movement.add(other.movement);
		this.manaAmount.add(other.manaAmount);
	}
	
	public void detach(StatBlob other) {
		this.livesLeft.subtract(other.livesLeft);
		this.intellect.subtract(other.intellect);
		this.strength.subtract(other.strength);
		this.agility.subtract(other.agility);
		this.hardiness.subtract(other.hardiness);
		this.experience.subtract(other.experience);
		this.movement.subtract(other.movement);
		this.lifeAmount.subtract(other.lifeAmount);
		this.manaAmount.subtract(other.manaAmount);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(	"Intellect: " + intellect.toInt() +
					"\nStrength: " + strength.toInt() +
					"\nAgility: " + agility.toInt() +
					"\nHardiness: " + hardiness.toInt() +
					"\nExperience: " + experience.toInt() +
					"\nMovement: " + movement.toInt() + 
					"\nLife Amount: " + lifeAmount.toInt() +
					"\nMana Amount: " + manaAmount.toInt());
		return sb.toString();
	}

	public void set(StatBlob oldblob) {
		this.livesLeft = oldblob.livesLeft;
		statMap.put(1, this.livesLeft);
		statStringMap.put(1,"Lives Left");
		this.strength = oldblob.strength;
		statMap.put(2, this.strength);
		statStringMap.put(2,"Strength");
		this.agility = oldblob.agility;
		statMap.put(3, this.agility);
		statStringMap.put(3,"Agility");
		this.intellect = oldblob.intellect;
		statMap.put(4, this.intellect);
		statStringMap.put(4,"Intellect");
		this.hardiness = oldblob.hardiness;
		statMap.put(5, this.hardiness);
		statStringMap.put(5,"Hardiness");
		this.experience = oldblob.experience;
		statMap.put(6, this.experience);
		statStringMap.put(6,"Experience");
		this.movement = oldblob.movement;
		statMap.put(7, this.movement);
		statStringMap.put(7,"Movement");
		this.lifeAmount = oldblob.lifeAmount;
		statMap.put(8, this.lifeAmount);
		statStringMap.put(8,"Life Amount");
		this.manaAmount = oldblob.manaAmount;
		statMap.put(9, this.manaAmount);
		statStringMap.put(9,"Mana Amount");
		
	}

}
