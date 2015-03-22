package com.oopsididitagain.rpg_iter2.models.entities;
/**
 * Created by parango on 3/11/15.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JOptionPane;

import com.oopsididitagain.rpg_iter2.models.Armory;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Skill;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.models.stats.StatCollection;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.StatModifiable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class Avatar extends Entity implements StatModifiable {

	
	private Occupation occupation;
	private StatCollection stats;
	private Armory armory;

	public Avatar(String id, Position position,StatBlob statblob) {
		super(id, position,statblob);
		this.armory = new Armory();
		this.stats = new StatCollection(armory,statblob);
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
		giveBaseSkills();
		occupation.giveSkills();
	}

	private void giveBaseSkills() {
		//bargain passive
		ArrayList<Skill> gameSkillList = occupation.getGameSkillList();
		ArrayList<Skill> fightSkillList = occupation.getFightSkillList();
		Map<String,Skill> passiveSkillList = occupation.getPassiveSkillList();
		
		Skill bargain = new Skill(Skill.BARGAIN);
		Discount discount = new Discount(.05);
		bargain.setEffect(discount);
		passiveSkillList.put(Skill.BARGAIN, bargain);
		//observe active
		
		Skill observe = new Skill(Skill.OBSERVATION);
		Observe obs = new Observe();
		observe.setEffect(obs);
		gameSkillList.add(observe);
	
		
		//bind wounds regular active fight
		//SKILLTWO
		//SKILLFIGHTONE
		
	}
	

	public Direction getDirection(){
		return position.getDirection();
	}
	public Occupation getOccupation() {
		return occupation;
	}
	
	public Skill getActiveSkill(Command command) {//this needs to differentiate between the states
		return occupation.getActiveSkill(command);
	}

	
	public Skill getActiveFightSkill(Command command) {//this needs to differentiate between the states
		return occupation.getFightSkill(command);
	}

	public Skill getPassiveSkill(String skill) {//this needs to differentiate between the states
		return occupation.getPassiveSkill(skill);
	}
	
	public LinkedList<String> getActiveSkillList(){
		return occupation.getGameSkillListString();
	}
	
	public LinkedList<String> getFightSkillList(){
		return occupation.getFightSkillListString();
	}
	
	public void visit(InventoryWeaponItem item) {
		InventoryWeaponItem conflict;
		if (item.isEquipped())
			conflict = armory.unequip(item);
		else {
			conflict = armory.equip(item);
			stats.mergeBlob(item.statBlob());
		}

		if (conflict != null) 
			stats.detachBlob(conflict.statBlob());
	}

	public void visit(InventoryArmorItem item) {
		InventoryArmorItem conflict;
		if (item.isEquipped())
			conflict = armory.unequip(item);
		else {
			conflict = armory.equip(item);
			stats.mergeBlob(item.statBlob());
		}
		if (conflict != null) 
			stats.detachBlob(conflict.statBlob());
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
	
	public String StatToString(){
		return stats.primaryViewport() + stats.derivedViewport();
	}

	public void minusUnusedSkillPoints() {
		this.stats.minusUnusedSkillPoints();
		
	}

	public int getUnusedPoints() {
		return stats.getUnusedPoints();
	}

	
}
