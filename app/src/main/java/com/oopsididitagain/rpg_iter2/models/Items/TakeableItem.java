package com.oopsididitagain.rpg_iter2.models.Items;

import java.util.Collection;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.EntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.ItemAlreadyTakenException;
import com.oopsididitagain.rpg_iter2.utils.Priceable;
import com.oopsididitagain.rpg_iter2.utils.Tileable;

public class TakeableItem extends PositionedItem implements EntityVisitable, Priceable {

	private boolean taken;
	private double price;

	public TakeableItem(String id, Position position, double price) {
		super(id, position);
		this.price = price;
	}

	@Override
	public void attemptRemoveFrom(Collection<Tileable> tileables) {
		if (taken) tileables.remove(this);
	}

	@Override
	public void accept(Entity entity) {
		entity.visit(this);
	}
	
	public void take() throws ItemAlreadyTakenException {
		if (taken) throw new ItemAlreadyTakenException("Item: " + getId());
		taken = true;
	}

	@Override
	public double price() {
		return price;
	}

}
