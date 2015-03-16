package com.oopsididitagain.rpg_iter2.models.Items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.InstantStatModifier;

public class OneShotItem extends PositionedItem implements InstantStatModifier {
	private StatBlob blob;

	public OneShotItem(String id, Position position, StatBlob blob) {
		super(id, position);
		this.blob = blob;
	}

	@Override
	public StatBlob effect() {
		return blob;
	}

}
