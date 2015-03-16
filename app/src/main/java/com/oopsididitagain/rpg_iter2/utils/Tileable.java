package com.oopsididitagain.rpg_iter2.utils;

import java.util.Collection;

public interface Tileable extends EntityVisitable {
	void attemptRemoveFrom(Collection<Tile> tiles);
}
