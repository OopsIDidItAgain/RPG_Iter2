package com.oopsididitagain.rpg_iter2.models.entities;

import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Storyline;
import com.oopsididitagain.rpg_iter2.models.items.InteractiveItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryEquipableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

/**
 * Created by parango on 3/11/15.
 */
public abstract class Npc extends Entity {

	Storyline story;

	private int turnsCannotMove;

	public Npc(String id, Position position, StatBlob statblob) {
		super(id, position, statblob);
		// TODO Auto-generated constructor stub
	}

	public Storyline getStoryline() {

		return story;
	}

	public void setCantMove(int turns) {
		this.turnsCannotMove = turns;
	}
	
	public void decrementTurnsCannotMove(int additional) {
		this.turnsCannotMove = turnsCannotMove - 1 - additional;
		if(turnsCannotMove < 0) {
			turnsCannotMove = 0;
		}
	}

	public int getTurnsCannotMove() {
		return turnsCannotMove;
	}

	public void setStoryline(Storyline story) {

		this.story = story;
	}

	public Controller talk() {

		return ActionMenuController.getInstance();
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.denyMovement();
		movementProbe.addEntity(this);
	}

	@Override
	public void visit(TakeableItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InstantStatModifier modifier) {
		statBlob().merge(modifier.statBlob());
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(getTileablePriority());
	}

	@Override
	public boolean removeable() {
		return false;
	}

	@Override
	public void visit(InventoryUnusableItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attemptInhibition(MovementProbe movementProbe) {
		movementProbe.denyMovement();
	}

	@Override
	public void visit(InventoryArmorItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InventoryWeaponItem item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(InteractiveItem item) {
		// TODO Auto-generated method stub

	}

	public abstract boolean accept(Battle battle);

	public AttackingNPC cloneNpc() {
		Position p = this.getPosition();
		Position newP = new Position(p.getY(), p.getX(), Direction.WEST);

		AttackingNPC a = new AttackingNPC(this.getId(), newP, this.statblob);
		a.entityStatus = this.entityStatus;
		a.inventory = this.inventory;
		a.isCurrentlyFlying = this.isCurrentlyFlying;
		a.bank = this.bank;

		return a;
	}

	
	@Override
	public String toSaveableFormat() {
		StringBuilder sb = new StringBuilder("");
		String[] arr = { getId(), Integer.toString(getX()), Integer.toString(getY()), getDirection().toString(),
			Integer.toString(entityStatus.getStatus()), Boolean.toString(isCurrentlyFlying), Double.toString(bank.amountOfMoney),
			statBlob().toSaveFormat() };
		sb.append(IOUtil.commaSeperate(arr) + "\n");
		sb.append(story.printStory().replace("\n", "") + "\n");
		sb.append("INVENTORY\n");
		sb.append(inventory.toSaveableFormat());
		sb.append("\n");
		return sb.toString();		
	}
}
