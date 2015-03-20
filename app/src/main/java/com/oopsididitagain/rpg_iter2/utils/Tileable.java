package com.oopsididitagain.rpg_iter2.utils;


public interface Tileable extends Assetable, Comparable<Tileable> {
	public boolean removeable();
	public TileablePriority getTileablePriority();
}