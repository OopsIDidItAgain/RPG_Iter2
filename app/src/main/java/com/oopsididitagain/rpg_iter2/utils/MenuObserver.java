package com.oopsididitagain.rpg_iter2.utils;

import java.awt.List;

public interface MenuObserver {
	public void update(List<String> options,int size, int currentOption);
}
