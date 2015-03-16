package com.oopsididitagain.rpg_iter2.utils;
public interface MenuObservable {
	public void register(MenuObserver o);
	public void unregister(MenuObserver o);
	public void notifyObserver();
}
