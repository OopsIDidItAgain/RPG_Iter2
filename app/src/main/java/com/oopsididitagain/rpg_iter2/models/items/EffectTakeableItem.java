package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.StatBlobHolder;

public class EffectTakeableItem extends TakeableItem implements
		StatBlobHolder {
	private StatBlob statBlob;

	public EffectTakeableItem(String id, Position position, double price, 
			StatBlob statBlob) {
		super(id, position, price);
		this.statBlob = statBlob;
	}

	@Override
	public StatBlob statBlob() {
		return statBlob;
	}

}
