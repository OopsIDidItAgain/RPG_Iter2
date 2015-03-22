package com.oopsididitagain.rpg_iter2.utils;

import com.oopsididitagain.rpg_iter2.models.Battle;

public interface Battleable extends StatBlobHolder {
	public boolean accept(Battle battle);
}