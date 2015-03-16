package com.oopsididitagain.rpg_iter2.utils;

public class ItemAlreadyTakenException extends Exception {
	private static final long serialVersionUID = -65425013303878988L;

	public ItemAlreadyTakenException() {
	}

	public ItemAlreadyTakenException(String message) {
		super(message);
	}
	
}
