package com.oopsididitagain.rpg_iter2;

import javax.swing.*;

/**
 *
 *
 */
public class RunGame extends JFrame{


private static GameLoop loop;
//private static MainMenu
	public static void main(String[] args) throws InterruptedException {
		loop = new GameLoop();
	    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				RunGame runGame = new RunGame();
				runGame.initialize();
			}
	    });

		loop.runGame();
	}
	
	private void initialize() {
		//this.setLayout(new MigLayout("", "[grow, fill]", "[grow, fill]"));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null); // places frame into center of screen
		this.setTitle("OOPs I Did It Again!");
		this.setVisible(true);
		this.setResizable(false);
		this.add(loop.getView());
		
	}

}
