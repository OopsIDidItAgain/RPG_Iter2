
package com.oopsididitagain.rpg_iter2.models.entities;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Storyline;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryEquipableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

/**
 * Created by parango on 3/11/15.
 */
public class Npc extends Entity {
	
	Storyline story;
	public Npc(String id, Position position, StatBlob statblob) {
		super(id, position,statblob);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
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

	
}
