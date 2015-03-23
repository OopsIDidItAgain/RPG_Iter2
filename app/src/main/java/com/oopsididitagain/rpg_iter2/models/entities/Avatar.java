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
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.effects.Discount;
import com.oopsididitagain.rpg_iter2.models.effects.Observe;
import com.oopsididitagain.rpg_iter2.models.effects.SelfInflictedModifier;
import com.oopsididitagain.rpg_iter2.models.items.InteractiveItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.Teleporter;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.models.stats.StatCollection;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.StatModifiable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class Avatar extends Entity implements StatModifiable {

	
	private Occupation occupation;
	private StatCollection stats;
	private Armory armory;
	TileablePriority tileablePriority = TileablePriority.HIGH;

	public Avatar(String id, Position position,StatBlob statblob) {
		super(id, position,statblob);
		this.armory = new Armory();
		this.stats = new StatCollection(armory,statblob);
	}
	
	public Avatar(String id, Position position,StatBlob statblob, Armory armory) {
		super(id, position,statblob);
		this.armory = new Armory();		
		this.stats = new StatCollection(armory,statblob);
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
		giveBaseSkills();
		occupation.giveSkills(this);
	}

	public void setTileablePriority(TileablePriority tileablePriority) {
		this.tileablePriority = tileablePriority;
	}
	
	@Override
	public TileablePriority getTileablePriority() {
		return this.tileablePriority;
	}

	private void giveBaseSkills() {
		//bargain passive
		ArrayList<Skill> gameSkillList = occupation.getGameSkillList();
		ArrayList<Skill> fightSkillList = occupation.getFightSkillList();
		Map<String,Skill> passiveSkillList = occupation.getPassiveSkillList();
		
		Skill bargain = new Skill(Skill.BARGAIN);
		Discount discount = new Discount(.05);
		bargain.setEffect(discount);
		gameSkillList.add(bargain);
		//observe active
		
		Skill observe = new Skill(Skill.OBSERVATION);
		Observe obs = new Observe();
		observe.setEffect(obs);
		gameSkillList.add(observe);
	
		
		//bind wounds regular active fight
		Skill bindWounds = new Skill(Skill.BINDWOUNDS);
		StatBlob statblob = new StatBlob(0, 0, 10, 0, 0, 0, 0, 0, 0);
		SelfInflictedModifier self = new SelfInflictedModifier(statblob,0);
		bindWounds.setEffect(self);
		gameSkillList.add(bindWounds);
	
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
		stats.mergeBlob(modifier.statBlob());
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
		attemptInhibition(movementProbe);
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

	public void drop(InventoryItem selectedItem, Tile currentTile) {
        inventory.remove(selectedItem);
        currentTile.add(selectedItem.toTakeableItem(currentTile.getPosition()));
	}
	
	public String[] primaryStats() {
		return stats.primaryStatArray();
	}
	
	public String[] derivedStats() {
		return stats.derivedStatArray();
	}

	public void minusUnusedSkillPoints() {
		this.stats.minusUnusedSkillPoints();
		
	}

	public int getUnusedPoints() {
		return stats.getUnusedPoints();
	}

//	public boolean accept(Battle battle) {
//		return battle.visit(this);
//	}

	@Override
	public void visit(InteractiveItem item) {
		item.checkRequirement(inventory);
		if (item.isFufilled()) 
			item.activate();
	}

	@Override
	public void attemptInhibition(MovementProbe movementProbe) {
		movementProbe.denyMovement();
	}

	public WeaponItemType getWeaponType() {
		return armory.getWeaponItemType();
	}

	public ArrayList<Skill> getPassiveSkillList() {
		return occupation.getPassiveSkillArray();
	}

	public void setWeapon(InventoryWeaponItem inventoryWeaponItem) {
		armory.equip(inventoryWeaponItem);
	}

	public int getMovementSpeed() {
		return stats.getMovementSpeed();
	}
	
	public boolean kill() {
		// returns false if no lives left and game's over
		return stats.die();
	}

	public Avatar cloneAvatar() {
		Position p = this.getPosition();
		Position newP = new Position(p.getY(), p.getX(), Direction.EAST);
		
		Avatar a = new Avatar(this.getId(),newP,this.statblob);
		a.entityStatus = this.entityStatus;
		a.inventory = this.inventory;
		a.isCurrentlyFlying = this.isCurrentlyFlying;
		a.bank = this.bank;
		a.occupation = this.occupation;
		a.stats = this.stats;
		a.armory = this.armory;
		
		return a;
	}

	public double offensiveRating() {
		return stats.getOffensiveRating();
	}
	
	public double defensiveRating() {
		return stats.getDefensiveRating();
	}

	@Override
	public void visit(Teleporter item) {
		// maybe figure out a way to get game state instance?
		
		// go back to origin
		// this.setPosition(new Position(0,0));
	}
	public void lowerMovementSpeedBy(int speedDifference) {
		stats.lowerMovementSpeedBy(speedDifference);		
	}
	public void raiseMovementSpeedBy(int speedDifference) {
		stats.raiseMovementSpeedBy(speedDifference)	;	
	}

	@Override
	public String toSaveableFormat() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Avatar\n");
		String[] arr = { getId(), Integer.toString(getX()), Integer.toString(getY()), getDirection().toString(),
			Integer.toString(entityStatus.getStatus()), Boolean.toString(isCurrentlyFlying), Double.toString(bank.amountOfMoney),
			statBlob().toSaveFormat() };
		sb.append(IOUtil.commaSeperate(arr) + "\n");
		sb.append(occupation.toSaveableFormat() + "\n");
		sb.append("INVENTORY\n");
		sb.append(inventory.toSaveableFormat());
		sb.append("\n");
		return sb.toString();
	}
}
