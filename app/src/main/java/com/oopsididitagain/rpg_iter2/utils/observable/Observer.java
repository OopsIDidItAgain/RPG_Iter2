package com.oopsididitagain.rpg_iter2.utils.observable;

import java.util.LinkedList;

public interface Observer {
	public void update(LinkedList<String> options,int size, int currentOption);
}
