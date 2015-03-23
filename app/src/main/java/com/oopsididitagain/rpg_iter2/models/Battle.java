package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.assets.SoundAssets;
import com.oopsididitagain.rpg_iter2.controllers.BattleController;
import com.oopsididitagain.rpg_iter2.controllers.Controller;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.GameOverController;
import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.entities.NonAttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.AvatarEntityInteraction;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
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
	// private SoundAssets sa = new SoundAssets();

	private LinkedList<Projectile> projectiles;

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
		projectiles = new LinkedList<Projectile>();
		// sa.playClip("battle");

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

	public Controller move(Command command) {
		Controller controller = BattleController.getInstance();

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
					AvatarEntityInteraction.avatarAttack(newAvatar, e);
					AvatarEntityInteraction.entityAttack(newAvatar, e);
					if (newAvatar.isDead())
						controller = GameOverController.getInstance();
				}
			}
			// randomly move npcs
			for (int i = 0; i < monsters.size(); i++) {
				Npc npc = monsters.get(i);
				if (npc.getTurnsCannotMove() > 0) {
					npc.decrementTurnsCannotMove(0);
				} else {
					Direction d = getDirectionToAvatar(npc);
					Position p = npc.getPosition().createPositionAtDirection(d);
					boolean isSuccessful = entityMapInteraction.move(npc, p);
					if (!isSuccessful) {
						// See if we run into the avatar
						Avatar a = (Avatar) entityMapInteraction
								.checkForEntity(npc, p);

						if (a != null) {// if we did run into the avatar...
							AvatarEntityInteraction
									.entityAttack(newAvatar, npc);
							if (newAvatar.isDead())
								controller = GameOverController.getInstance();
						}
					}
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

		return controller;
	}

	private Direction getDirectionToAvatar(Npc npc) {
		Position a = newAvatar.getPosition();
		Position n = npc.getPosition();
		if (n.getX() == a.getX()) {
			// north or south
			if (n.getY() > a.getY()) {
				return Direction.NORTH;
			} else {
				return Direction.SOUTH;
			}
		}
		if (n.getY() == a.getY()) {
			// east or west
			if (n.getX() > a.getX()) {
				return Direction.WEST;
			} else {
				return Direction.EAST;
			}
		}
		if (n.getX() > a.getX()) {
			// west
			if (n.getY() > a.getY()) {
				return Direction.NORTHWEST;
			} else {
				return Direction.SOUTHWEST;
			}
		} else {
			// east
			if (n.getY() > a.getY()) {
				return Direction.NORTHEAST;
			} else {
				return Direction.SOUTHEAST;
			}
		}
	}

	public boolean isDone() {
		LinkedList<Npc> toRemove = new LinkedList<Npc>();
		for (Npc npc : monsters) {
			if (npc.statBlob().getLifeAmount() <= 0)
				toRemove.add(npc);
		}
		for (Npc npc : toRemove) {
			monsters.remove(npc);
		}
		if (monsters.isEmpty()) {
			return true;
		}
		return false;
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectiles;
	}

	public Controller use() {
		// FOR DEBUG of PROJECTILE
		Position pos = newAvatar.getPosition();
		Projectile p = new Projectile(pos);
		projectiles.add(p);
		do {
			if (battleground.tileInbounds(p.getPosition())) {
				Tile t = battleground.getTileAt(p.getPosition());
				Npc e = (Npc) t.getEntity();
				if (e != null) {
					e.statBlob().merge(p.getStatBlob());
					//e.setCantMove(1);
					break;
				}
				p.doLogic();
			} else {
				break;
			}
		} while (battleground.tileInbounds(p.getPosition()));
		// FOR DEBUG OF PROJECTILE
		if (newAvatar.getWeaponType() != null) {

			switch (newAvatar.getWeaponType()) {
			case FISTS:
				break;
			case ONE_HANDED_WEAPON:
				break;
			case RANGED_WEAPON:
				break;
			case STAFF:
				break;
			case TWO_HANDED_WEAPON:
				break;
			default:
				break;
			}
		}
		return BattleController.getInstance();
	}
	public Controller useBomb(){
		Position pos = newAvatar.getPosition();
		Bomb b = new Bomb(pos);
		
		return BattleController.getInstance();
	}
	

	public int[] getHearts() {
		// String heartcount = "";
		int[] hearts = new int[2];

		Iterator<Entity> entityIterator = party.iterator();
		while (entityIterator.hasNext()) {
			Entity entity = entityIterator.next();
			hearts[0] = (int) entity.statBlob().getLifeAmount();
		}

		Iterator<Npc> monsterIterator = monsters.iterator();
		while (monsterIterator.hasNext()) {
			Npc monster = monsterIterator.next();
			hearts[1] = (int) monster.statBlob().getLifeAmount();
		}

		return hearts;
	}

	public void removeProjectile(Projectile p) {
		projectiles.remove(p);
	}
}