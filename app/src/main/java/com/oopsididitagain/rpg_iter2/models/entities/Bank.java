package com.oopsididitagain.rpg_iter2.models.entities;

public class Bank {
	double amountOfMoney;
	public Bank(double amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	
	public void purchaseTransaction(double price){
		amountOfMoney -= price;
	}
	
	public void sellTransaction(double price){
		amountOfMoney += price;
	}

	public String getAvailableFunds() {
		String amount = "" + amountOfMoney;
		return amount;
	}
}
