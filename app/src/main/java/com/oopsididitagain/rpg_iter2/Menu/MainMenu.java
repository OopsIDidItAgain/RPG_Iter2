package com.oopsididitagain.rpg_iter2.Menu;

import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.utils.MenuObservable;
import com.oopsididitagain.rpg_iter2.utils.MenuObserver;



public class MainMenu implements MenuObservable{
	LinkedList<String> options;
	int size;
	int currentOption;
	MenuObserver observer;
	@Override
	public void register(MenuObserver observer) {
		this.observer = observer;
	}

	@Override
	public void unregister(MenuObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserver() {
		observer.update(options,size,currentOption);
	}

}
