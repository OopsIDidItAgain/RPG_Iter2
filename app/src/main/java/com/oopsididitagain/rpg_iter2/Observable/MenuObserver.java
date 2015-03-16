package com.oopsididitagain.rpg_iter2.Observable;

import java.util.LinkedList;

public interface MenuObserver {
	public void update(LinkedList<String> options,int size, int currentOption);
}
