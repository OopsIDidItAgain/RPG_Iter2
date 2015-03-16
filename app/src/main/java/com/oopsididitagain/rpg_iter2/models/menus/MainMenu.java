package com.oopsididitagain.rpg_iter2.models.menus;

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.utils.observable.Observable;
import com.oopsididitagain.rpg_iter2.utils.observable.Observer;




public class MainMenu implements Observable {
	LinkedList<String> options;
	int size;
	int currentOption;
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
		observer.update(options,size,currentOption);
	}

}
