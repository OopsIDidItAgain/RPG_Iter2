package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;

public interface InstantStatModifier extends StatBlobHolder {
	public void affect(StatBlob target);
}