package com.oopsididitagain.rpg_iter2;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;

/**
 *
 *
 */
public class RunGame extends JFrame{

	private static final long serialVersionUID = 6868516152488185382L;
	private static GameLoop loop;

	public static void main(String[] args) throws InterruptedException {
		/*loop = new GameLoop();
	    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				RunGame runGame = new RunGame();
				runGame.initialize();
			}
	    });

		loop.runGame();
		System.exit(NORMAL);*/
		IOUtil.saveMap(new GameMap(new MapDatabase(1)));
	}
	
	private void initialize() {
		//this.setLayout(new MigLayout("", "[grow, fill]", "[grow, fill]"));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(600, 700);
		this.setLocationRelativeTo(null); // places frame into center of screen
		this.setTitle("Demeter Lays Down the Law 2: Tell, Don't Ask");
		this.setVisible(true);
		this.setResizable(false);
		this.add(loop.getView());
	}

}
