package com.oopsididitagain.rpg_iter2.models.entities;
/**
 * Created by parango on 3/11/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.items.InventoryEquipableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.models.stats.StatCollection;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.StatModifiable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class Avatar extends Entity implements StatModifiable {

	private ArrayList<Skill> gameSkillList = new ArrayList<Skill>();
	private ArrayList<Skill> fightSkillList = new ArrayList<Skill>();
	private Map<String,Skill> passiveSkillList = new HashMap<String,Skill>();
	private Occupation occupation;
	private StatCollection stats;

	public Avatar(String id, Position position,StatBlob statblob) {
		super(id, position,statblob);
		
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
		giveBaseSkills();
		occupation.giveSkills(gameSkillList,fightSkillList,passiveSkillList);
		
	}

	private void giveBaseSkills() {
		//bargain passive
		
		Skill bargain = new Skill();
		Discount discount = new Discount(.05);
		bargain.setEffect(discount);
		passiveSkillList.put(Skill.BARGAIN, bargain);
		
		//observe active
		
		Skill observe = new Skill();
		Observe obs = new Observe();
		observe.setEffect(obs);
		gameSkillList.add(observe);
		
		//bind wounds regular active fight
		
	}
	

	public Direction getDirection(){
		return position.getDirection();
	}
	public Occupation getOccupation() {
		return occupation;
	}
	
	public Skill getActiveSkill(int command) {//this needs to differentiate between the states
		return gameSkillList.get(command);
	}

	
	public void visit(InventoryEquipableItem item) {
		// ArmoryStuff
		// armory.add(item);
	}

	public void visit(InventoryUnusableItem item) {
		JOptionPane.showMessageDialog(null, "Can't Use Here!");
	}
	
	@Override
	public void visit(InstantStatModifier modifier) {
		modifier.affect(statBlob());
	}

	@Override
	public StatBlob statBlob() {
		return stats.getBlob();
	}

	@Override
	public void visit(TakeableItem item) {
		try {
			item.take();
		} catch (ItemAlreadyTakenException ex) {
			ex.printStackTrace();
			return;
		}
		inventory.add(item);
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.denyMovement();
		movementProbe.addEntity(this);
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public boolean removeable() {
		return false;
	}

	public void drop(InventoryItem selectedItem) {
		Position position = this.position.createPositionAtDirection(getDirection());

	}
	public void attemptInhibition(MovementProbe movementProbe) {
		// TODO Auto-generated method stub
		
	}

	
}
