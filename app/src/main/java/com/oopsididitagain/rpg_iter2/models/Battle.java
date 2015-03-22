package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.NonAttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.utils.Battleable;

public class Battle {

	private LinkedList<Npc> monsters;
	private LinkedList<Entity> party;
	private GameMap battleground;
	private LinkedList<Entity> order;

	public Battle() {
		monsters = new LinkedList<Npc>();
		party = new LinkedList<Entity>();
		Tile[][] tiles = new Tile[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				tiles[i][j] = new Tile(new Position(i, j), Terrain.GRASS);
			}
		}
		battleground = new GameMap(tiles);
	} // use this constructor if you want to set monsters and party using
		// setMonsters() and setParty()

	public Battle(LinkedList<Npc> monsters, LinkedList<Entity> party) {
		this.monsters = monsters;
		this.party = party;
		sortEntities();
	}

	public void setMonsters(ArrayList<Npc> creatures,
			ArrayList<Position> positions) throws Exception {
		if (creatures.size() > positions.size()) {
			throw new Exception();
		}

		monsters.clear();
		for (int i = 0; i < creatures.size(); i++) {
			Npc creature = creatures.get(i);
			// Position position = positions.get(i);
			monsters.add(creature);
		}

		if (party != null) {
			if (!party.isEmpty()) {
				sortEntities();
			}
		}
	}

	public void setParty(ArrayList<Npc> members, ArrayList<Position> positions)
			throws Exception {
		if (members.size() > positions.size()) {
			throw new Exception();
		}

		party.clear();
		for (int i = 0; i < members.size(); i++) {
			Npc member = members.get(i);
			// Position position = positions.get(i);
			party.add(member);
		}

		if (monsters != null) {
			if (!monsters.isEmpty()) {
				sortEntities();
			}
		}
	}

	public void addMonster(Npc npc) {
		if (!monsters.contains(npc)) {
			Tile t = battleground.getTileAt(new Position(battleground
					.getHeight() / 10, battleground.getWidth()-1));
			t.add(npc);
			monsters.add(npc);
		}
	}

	public void addPartyMember(Entity member) {
		if (!party.contains(member)) {
			Tile t = battleground.getTileAt(new Position(battleground
					.getHeight() / 10, 0));
			t.add(member);
			party.add(member);
		}
	}

	// public void updatePosition(Battleable entity, Position newPosition,
	// boolean partOfParty) {
	// if (partOfParty) {
	// party.remove(entity);
	// party.add(entity);
	// } else {
	// monsters.remove(entity);
	// monsters.add(entity);
	// }
	// }

	private void sortEntities() {
		LinkedList<Npc> sortedMonsters = new LinkedList<Npc>();
		LinkedList<Entity> sortedParty = new LinkedList<Entity>();

		Iterator<Npc> monsterIterator = monsters.iterator();
		while (monsterIterator.hasNext()) {
			Npc monster = monsterIterator.next();

			if (sortedMonsters.isEmpty()) {
				sortedMonsters.add(monster);
			} else {
				for (int i = 0; i < sortedMonsters.size(); i++) {
					if (monster.statBlob().getMovement() > sortedMonsters
							.get(i).statBlob().getMovement()) {
						sortedMonsters.add(i, monster);
					}
				}

				if (!sortedMonsters.contains(monster)) {
					sortedMonsters.add(monster);
				}
			}

			monsterIterator.remove();
		}

		Iterator<Entity> partyIterator = party.iterator();
		while (partyIterator.hasNext()) {
			Entity member = partyIterator.next();

			if (sortedParty.isEmpty()) {
				sortedParty.add(member);
			} else {
				for (int i = 0; i < sortedParty.size(); i++) {
					if (member.statBlob().getMovement() > sortedParty.get(i)
							.statBlob().getMovement()) {
						sortedParty.add(i, member);
					}
				}

				if (!sortedParty.contains(member)) {
					sortedParty.add(member);
				}
			}

			partyIterator.remove();
		}

		int a = 0;
		int b = 0;
		while (a < sortedMonsters.size() && b < sortedParty.size()) {
			Npc monster = sortedMonsters.get(a);
			Entity member = sortedParty.get(b);

			if (monster.statBlob().getMovement() >= member.statBlob()
					.getMovement()) {
				order.add(monster);
				a++;
			} else {
				order.add(member);
				b++;
			}
		}
		if (a < sortedMonsters.size()) {
			while (a < sortedMonsters.size()) {
				order.add(sortedMonsters.get(a));
				a++;
			}
		} else {
			while (b < sortedParty.size()) {
				order.add(sortedParty.get(b));
				b++;
			}
		}
	}

	public void onMonsterDeath(Entity monster) {
		monsters.remove(monster);
		order.remove(monster);
	}

	public void onPartyMemberDeath(Entity member) {
		party.remove(member);
		order.remove(member);
	}

	// public boolean visit(Avatar avatar) {
	// //TODO: Add Avatar actions
	// }

	public boolean visit(AttackingNPC npc) {
		return true;
	}

	public boolean visit(NonAttackingNPC nonAttackingNPC) {
		return false;

	}

	public void start() {
		while (!monsters.isEmpty() && !party.isEmpty()) {
			for (int i = 0; i < order.size(); i++) {
				Entity entity = order.get(i);
				// entity.accept(this);
			}
		}
	}

}