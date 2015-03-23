package com.oopsididitagain.rpg_iter2.models.items;

import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;
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

	@Override
	public InventoryUsableItem toInventoryItem() {
		InventoryUsableItem item = new InventoryUsableItem(this);
		return item;
	}
	
	public String toSaveableFormat() {
		String[] arr = { getId(), Integer.toString(getX()),
				Integer.toString(getY()), "EffectTakeableItem",
				statBlob.toSaveFormat(),
				Double.toString(price())};
		return IOUtil.commaSeperate(arr);
	}
}
