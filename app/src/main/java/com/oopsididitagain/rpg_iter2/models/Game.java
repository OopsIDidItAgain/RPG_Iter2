package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;

public class Game {

	private Avatar avatar;
	private GameMap gameMap;
	
	
	public Game(Avatar avatar){
		this.avatar = avatar;
		gameMap = new GameMap(new MapDatabase(1));
		gameMap.getTileAt((new Position(0,0))).add(avatar);
		Position position = new Position(2,0);
		StatBlob statBlob = new StatBlob(0, 0, 0, 0, 0, 0, 0, 20, 20);
		Npc buddy = new Npc("buddy", position, statBlob);
		gameMap.getTileAt(position).add(buddy);
	}
	public Game( Avatar avatar, GameMap gameMap){
	
		this.avatar = avatar;
		this.gameMap = gameMap;
	}
	
	public Avatar getAvatar(){
		
		return avatar;
	}
	public GameMap getGameMap(){
		
		return gameMap;
	}
	
}
