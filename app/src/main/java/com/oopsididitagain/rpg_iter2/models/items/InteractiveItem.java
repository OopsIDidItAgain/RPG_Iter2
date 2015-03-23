package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.MovementProbe;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.PositionedGameObject;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.EntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;
import com.oopsididitagain.rpg_iter2.utils.InvalidMovementException;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.utils.TileablePriority;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;

public class InteractiveItem extends PositionedGameObject implements TiledEntityVisitable, EntityVisitable {
	private InventoryItem requirement; 
	private Tile target;
	private boolean fulfilled = false;
	private Terrain resultant;

	private InteractiveItem(String id, Position position, Tile target, Terrain resultant) {
		super(id, position);
		this.target = target;
		this.resultant = resultant;
	}
	
	public InteractiveItem(String id, Position position, Tile target, Terrain resultant, InventoryEquipableItem requirement) {
		this(id, position, target, resultant);
		this.requirement = requirement;
	}

	public InteractiveItem(String id, Position position, Tile target, Terrain resultant, InventoryUsableItem requirement) {
		this(id, position, target, resultant);
		this.requirement = requirement;
	}

	public InteractiveItem(String id, Position position, Tile target, Terrain resultant, InventoryUnusableItem requirement) {
		this(id, position, target, resultant);
		this.requirement = requirement;
	}

	@Override
	public int compareTo(Tileable o) {
		return getTileablePriority().compareTo(o.getTileablePriority());
	}

	@Override
	public boolean removeable() {
		return fulfilled;
	}

	@Override
	public TileablePriority getTileablePriority() {
		return TileablePriority.MIDDLE;
	}

	@Override
	public void accept(Entity entity) throws InvalidMovementException {
		entity.visit(this);
	}
	
	public boolean isFufilled() {
		return fulfilled;
	}

	public void checkRequirement(Inventory inventory) {
		if (inventory.contains(requirement))
			fulfilled = true;
	}

	public void activate() {
		Terrain terrain = target.getTerrain();
		target.remove(terrain);
		target.setTerrain(resultant);
	}

	@Override
	public void accept(MovementProbe movementProbe) {
		movementProbe.addPositionedGameObject(this);
	}

	@Override
	public String toSaveableFormat() {
		String[] arr =  { getId(), Integer.toString(getX()), Integer.toString(getY()),
			Integer.toString(target.getPosition().getX()), Integer.toString(target.getPosition().getY()), 
			"InteractiveItem", resultant.toString(), requirement.getId() };
		return IOUtil.commaSeperate(arr);
	}


}
