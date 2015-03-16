package com.oopsididitagain.rpg_iter2.Observable;
public interface MenuObservable {
	public void register(MenuObserver o);
	public void unregister(MenuObserver o);
	public void notifyObserver();
}
