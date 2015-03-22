package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.WeaponTakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.PositionOutOfBoundsException;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class Game {

	private Avatar avatar;
	private GameMap gameMap;
	
	
	public Game(Avatar avatar){
		this.avatar = avatar;
		gameMap = new GameMap(new MapDatabase(1));
		Position position2 = new Position(3,0);
		StatBlob statBlob = new StatBlob(0, 0, 0, 0, 0, 0, 0, 20, 20);
		WeaponTakeableItem pgo = new WeaponTakeableItem("chainsaw_item", position2, 4.05,statBlob,WeaponItemType.ONE_HANDED_WEAPON);
		gameMap.getTileAt((new Position(0,0))).add(avatar);
		gameMap.getTileAt(position2).add(pgo);
		Position position = new Position(2,0);
		Npc buddy = new Npc("buddy", position, statBlob);
		buddy.getInventory().add(pgo);
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
