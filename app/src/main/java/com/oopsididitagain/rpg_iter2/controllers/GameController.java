package com.oopsididitagain.rpg_iter2.controllers;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.ActionMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.GameOverController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.InventoryController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.PauseMenuController;
import com.oopsididitagain.rpg_iter2.controllers.menu_controllers.SkillPointAllocationController;
import com.oopsididitagain.rpg_iter2.model_view_interaction.GameViewInteraction;
import com.oopsididitagain.rpg_iter2.models.*;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.interaction_classes.EntityMapInteraction;
import com.oopsididitagain.rpg_iter2.utils.Command;
import com.oopsididitagain.rpg_iter2.utils.Direction;

/**
 * In charge of handling input while playing game
 *
 * Created by parango on 3/11/15.
 */
public class GameController extends Controller {
	private static GameController instance;
	private Game game;
	private Avatar avatar;
	private GameMap gameMap;
	private EntityMapInteraction entityMapInteraction;
	private boolean canMove = true;

	private GameController() {

	}

	public void setGame(Game g) {
		game = g;
		this.avatar = g.getAvatar();
		this.gameMap = g.getGameMap();
		createEntityMapInteraction();

	}

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	@Override
	public Controller takeInputAndUpdate(Command command) {
		Controller c = takeStatsAndUpdate(); // this includes things like
												// relocating avatar if dead
		if (c instanceof GameOverController)
			return c; // game over

		c = performSkillCommand(command);
		performPassiveSkills();
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
		case INVENTORY:
            InventoryController ic = InventoryController.getInstance();
            ic.setCurrentTile(gameMap.getTileAt(avatar.getPosition()));
			c = ic;

			break;
		case PAUSE:
			c = PauseMenuController.getInstance();
			break;
		case SKILLALLOCATION:
			c = SkillPointAllocationController.getInstance();
			break;
		case FLIGHT:
            if(avatar.isCurrentlyFlying()){
                if(gameMap.getTileAt(avatar.getPosition()).getTerrain() != Terrain.WATER)
			        avatar.setFlying(true);
            }else{
                avatar.setFlying(false);
            }
			break;
		default:
			break;
		}

		if (targetDirection != null
				&& canMove
				&& avatar.getEntityStatus().getStatus() != EntityStatus.TRAPPED) { // if
																					// we
																					// pressed
																					// a
																					// directional
            avatar.setDirection(targetDirection);																		// button
			// check if we can move in the requested direction
			Position toPosition = avatar.getPosition()
					.createPositionAtDirection(targetDirection);
			boolean successfulMove = entityMapInteraction.move(avatar,
					toPosition);

			if (!successfulMove) {
				// See if we run into a Npc, down cast but all entities we run
				// into are Npc's
				Npc e = (Npc) entityMapInteraction.checkForEntity(avatar,
						toPosition);

				if (e != null) {// if we did run into an npc, tell the
								// actionMenu and switch to its controller

					ActionMenuController amc = ActionMenuController
							.getInstance();
					amc.setNpc(e);
					amc.setAvatar(avatar);

					c = ActionMenuController.getInstance();
				} else {
					moveNpcs();
				}
			} else {
				moveNpcs();
			}

		}

		return c;
	}

	private void moveNpcs() {
		// randomly move npcs
		ArrayList<Npc> listOfNpcs = game.getListOfNpcs();
		for (int i = 0; i < listOfNpcs.size(); i++) {
			Direction d = getRandomDirection();
			if (d != null) {
				Npc npc = listOfNpcs.get(i);
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
		timer.schedule(timertask, 1000 / avatar.getMovementSpeed());
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

	public Controller takeStatsAndUpdate() {
		Controller c = this;
		if (avatar.isDead()) {
			if (avatar.kill()) { // true if avatar has lives left

				Position av = avatar.getPosition(), o = new Position(0, 0);

				avatar.move(gameMap.getTileAt(av), gameMap.getTileAt(o), o);
			} else {
				c = GameOverController.getInstance();
				return c;
			}
		}

		return c;
	}

	private Controller performSkillCommand(Command command) {
		Controller c = this;
		Skill skill = avatar.getActiveSkill(command);
		if (skill != null) {
			entityMapInteraction.applySkill(avatar, skill);
			// Command.SKILLTWO will always point to observe
			if (command == Command.SKILLTWO) {
				c = ObserverController.getInstance();
				((ObserverController) c)
						.setEntityMapInteraction(entityMapInteraction);
				((ObserverController) c).setAvatar(avatar);
				((ObserverController) c).setSkill(skill);
			}
		}
		return c;
	}

	public void performPassiveSkills() {
		ArrayList<Skill> passiveSkill = avatar.getPassiveSkillList();
		for (Skill skill : passiveSkill) {
			entityMapInteraction.applySkill(avatar, skill);
		}
	}

	private void createEntityMapInteraction() {
		entityMapInteraction = new EntityMapInteraction(gameMap);

	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public void setMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}

	// public void toggleFlight(){
	// if(!gameMap.getTileAt(avatar.getPosition()).getTerrain().isWater()) {
	// avatar.setFlying(!avatar.isFlying());
	// }
	// }

	@Override
	public GameViewInteraction populateInteraction() {

		GameViewInteraction gameInteraction = new GameViewInteraction(game);
		return gameInteraction;
	}

	public void remove(Npc npc) {
		Position p = npc.getPosition();
		Tile t = gameMap.getTileAt(p);
		ArrayList<Npc> listOfNpcs = game.getListOfNpcs();
		// remove from list and map tile
		listOfNpcs.remove(npc);
		t.remove(npc);
	}

}
