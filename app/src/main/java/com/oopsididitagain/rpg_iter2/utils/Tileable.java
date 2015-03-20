package com.oopsididitagain.rpg_iter2.utils;

import java.util.Collection;

public interface Tileable extends Assetable, EntityVisitable, ProbeVisitable, Comparable<Tileable> {
	public void attemptRemoveFrom(Collection<Tileable> tileables);
	public TileablePriority getTileablePriority();
}