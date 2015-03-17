package com.oopsididitagain.rpg_iter2.utils;

import java.util.Collection;

public interface Tileable extends EntityVisitable, ProbeVisitable {
	void attemptRemoveFrom(Collection<Tileable> tileables);
}