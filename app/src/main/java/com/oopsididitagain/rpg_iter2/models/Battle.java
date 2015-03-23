package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.PauseMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.SkillPointAllocationController;
import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.entities.NonAttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;

public class Battle {

	private LinkedList<Npc> monsters;
	private LinkedList<Entity> party;
	private Avatar newAvatar;
	private GameMap battleground;
	private LinkedList<Entity> order;
	private Direction lastFacingDirection;
	private Position oldPosition;
	private boolean canMove = true;
	private EntityMapInteraction entityMapInteraction;

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

		entityMapInteraction = new EntityMapInteraction(battleground);

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
			Npc newNpc = npc.cloneNpc();

			Position p = new Position(battleground.getHeight() / 2,
					battleground.getWidth() - 1, Direction.WEST);
			Tile t = battleground.getTileAt(p);
			newNpc.setPosition(p);
			t.add(newNpc);
			monsters.add(newNpc);
		}
	}

	public void addPartyMember(Avatar member) {
		if (!party.contains(member)) {
			Avatar newAvatar = member.cloneAvatar();

			Position p = new Position(battleground.getHeight() / 2, 0,
					Direction.EAST);
			Tile t = battleground.getTileAt(p);
			newAvatar.setPosition(p);
			t.add(newAvatar);
			party.add(newAvatar);
			this.newAvatar = newAvatar;
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

	public void sortEntities() {
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

	public GameMap getGameMap() {
		return battleground;
	}

	public void start() {
		while (!monsters.isEmpty() && !party.isEmpty()) {
			for (int i = 0; i < order.size(); i++) {
				Entity entity = order.get(i);
				// entity.accept(this);
			}
		}
	}

	public void setOldPosition(Position position) {
		this.oldPosition = position;
	}

	public Position getOldPosition() {
		return oldPosition;
	}

	public void move(Command command) {
		Direction targetDirection = null;
		switch (command) {
		case MOVE_SOUTH:
			targetDirection = Direction.SOUTH;
			break;
		case MOVE_NORTH:
			targetDirection = Direction.NORTH;
			break;
		case MOVE_WEST:
			targetDirection = Direction.WEST;
			break;
		case MOVE_EAST:
			targetDirection = Direction.EAST;
			break;
		case MOVE_SOUTHWEST:
			targetDirection = Direction.SOUTHWEST;
			break;
		case MOVE_SOUTHEAST:
			targetDirection = Direction.SOUTHEAST;
			break;
		case MOVE_NORTHWEST:
			targetDirection = Direction.NORTHWEST;
			break;
		case MOVE_NORTHEAST:
			targetDirection = Direction.NORTHEAST;
			break;
		default:
			break;
		}

		if (targetDirection != null
				&& canMove
				&& newAvatar.getEntityStatus().getStatus() != EntityStatus.SLEEPING) {
			// if we pressed a directional button check if we can move in the
			// requested direction
			Position toPosition = newAvatar.getPosition()
					.createPositionAtDirection(targetDirection);
			boolean successfulMove = entityMapInteraction.move(newAvatar,
					toPosition);
			// checks if NPC is there, if it is we bring up actionMenu
			if (!successfulMove) {
				// See if we run into a Npc, down cast but all entities we run
				// into are Npc's
				Npc e = (Npc) entityMapInteraction.checkForEntity(newAvatar,
						toPosition);

				if (e != null) {// if we did run into an npc...
					monsters.remove(e);
				}
			}
			// randomly move npcs
			for (int i = 0; i < monsters.size(); i++) {
				Direction d = getRandomDirection();
				if (d != null) {
					Npc npc = monsters.get(i);
					Position p = npc.getPosition().createPositionAtDirection(d);

					boolean isSuccessful = entityMapInteraction.move(npc, p);
				}
			}
			canMove = false;
			TimerTask timertask = new TimerTask() {
				@Override
				public void run() {
					canMove = true;
				}
			};
			Timer timer = new Timer();
			timer.schedule(timertask, 1000 / newAvatar.getMovementSpeed());
		}

	}

	private Direction getRandomDirection() {
		int randDir = (int) (Math.random() * 16);
		Direction targetDirection = null;
		switch (randDir) {
		case 0:
			targetDirection = Direction.SOUTH;
			break;
		case 1:
			targetDirection = Direction.NORTH;
			break;
		case 2:
			targetDirection = Direction.WEST;
			break;
		case 3:
			targetDirection = Direction.EAST;
			break;
		case 4:
			targetDirection = Direction.SOUTHWEST;
			break;
		case 5:
			targetDirection = Direction.SOUTHEAST;
			break;
		case 6:
			targetDirection = Direction.NORTHWEST;
			break;
		case 7:
			targetDirection = Direction.NORTHEAST;
			break;
		default:
			break;
		}
		return targetDirection;
	}

	public boolean isDone() {
		if (monsters.isEmpty())
			return true;
		return false;
	}
}