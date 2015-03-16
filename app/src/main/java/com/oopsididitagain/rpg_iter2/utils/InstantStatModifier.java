package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.Stats.StatBlob;

public interface InstantStatModifier extends StatBlobHolder {
	public void affect(StatModifiable target);
}
