package com.oopsididitagain.rpg_iter2.model_view_interaction;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.SortedSet;

import com.oopsididitagain.rpg_iter2.assets.Assets;
import com.oopsididitagain.rpg_iter2.models.Battle;
import com.oopsididitagain.rpg_iter2.models.GameMap;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.Tileable;
import com.oopsididitagain.rpg_iter2.views.View;

public class BattleViewInteraction extends ModelViewInteraction {
	private Battle battle;
	private Assets assets;
	private GameMap battleground;

	private final int height = View.pHeight / 3;
	private final int width = (int) (View.pWidth / 2);
	private final int x = View.pWidth / 5;
	private final int y = View.pHeight / 4;

	public BattleViewInteraction(Battle battle) {
		this.battle = battle;
		this.assets = new Assets();
		battleground = battle.getGameMap();
	}

	@Override
	public void accept(View view) {
		view.accept(this);
	}

	@Override
	public void drawModel(Graphics g) {
		int y = battleground.getHeight();
		int x = battleground.getWidth();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Tile t = battleground.getTileAt(new Position(j, i));
				drawTile(g, t, i, j);
			}
		}
		
		drawHearts(g);
	}

	private void drawTile(Graphics g, Tile t, int x, int y) {
		SortedSet<Tileable> tileables = t.getTilebles();
		Entity entity = t.getEntity();

		Image bf = assets.getImage(t.getTerrain().getId());
		g.drawImage(bf, x * 50, y * 50, 50, 50, null);

		Iterator<Tileable> tileablesIter = tileables.iterator();
		String tileableToDrawId = tileablesIter.next().getId();
		if (tileableToDrawId.equals("avatar"))
			tileableToDrawId = tileablesIter.next().getId();

		bf = assets.getImage(tileableToDrawId);
		g.drawImage(bf, x * 50, y * 50, 50, 50, null);

		if (entity != null) {
			String id = entity.getId();
			if (entity.isCurrentlyFlying()) {
				id += "_flying";
			} else if (entity.getEntityStatus().getStatus() == EntityStatus.SLEEPING) {
				id += "_sleeping";
			} else if (entity.getEntityStatus().getStatus() == EntityStatus.SMELL) {
				id += "_badSmell";
			} else if (entity.getEntityStatus().getStatus() == EntityStatus.SAD) {
				id += "_sad";
			} else if (entity.getDirection() == Direction.NORTHWEST) {
				id += "_northwest";
			} else if (entity.getDirection() == Direction.NORTH) {
				id += "_north";
			} else if (entity.getDirection() == Direction.NORTHEAST) {
				id += "_northeast";
			} else if (entity.getDirection() == Direction.EAST) {
				id += "_east";
			} else if (entity.getDirection() == Direction.WEST) {
				id += "_west";
			} else if (entity.getDirection() == Direction.SOUTHEAST) {
				id += "_southeast";
			} else if (entity.getDirection() == Direction.SOUTH) {
				id += "_south";
			} else if (entity.getDirection() == Direction.SOUTHWEST) {
				id += "_southwest";
			}

			Image b2 = assets.getImage(id);

			g.drawImage(b2, x * 50, y * 50, 50, 50, null);

		}
	}
	
	private void drawHearts(Graphics g) {
		int[] hearts = battle.getHearts();

		int height = 420, width = 20, next = 0;
		// avatar
		Image heart = assets.getImage("heart_decal");
		for (int i = 0; i < hearts[0]; i++) {
			g.drawImage(heart, width + (20*next++), height, 20, 20, null);
			if (i % 7 == 6) { height += 20; next = 0;}
		}
		
		height = 420; width = 330; next = 0;
		for (int i = 0; i < hearts[1]; i++) {
			g.drawImage(heart, width + (20*next++), height, 20, 20, null);
			if (i % 7 == 6) { height += 20; next = 0;}
		}
	}

}
