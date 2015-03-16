package com.oopsididitagain.rpg_iter2.utils.observable;
public interface Observable {
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
}
