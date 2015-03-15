package com.oopsididitagain.rpg_iter2.Menu;

import com.oopsididitagain.rpg_iter2.utils.Observable;
import com.oopsididitagain.rpg_iter2.utils.Observer;

public class MainMenu implements Observable{
	Observer observer;
	@Override
	public void register(Observer observer) {
		this.observer = observer;
	}

	@Override
	public void unregister(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		observer.update();
		
	}

}
