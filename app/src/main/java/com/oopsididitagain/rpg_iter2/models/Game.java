package com.oopsididitagain.rpg_iter2.models;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.NonAttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.NonTradingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.entities.TradingNPC;
import com.oopsididitagain.rpg_iter2.models.items.ArmorTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EffectTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.WeaponTakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class Game {

	private Avatar avatar;
	private GameMap gameMap;
	
	
	public Game(Avatar avatar){
		this.avatar = avatar;
		gameMap = new GameMap(new MapDatabase(1));
		Position position2 = new Position(3,0);
		Position position3 = new Position(6, 2);
		Position position4 = new Position(6, 5);
		Position position7 = new Position(9, 9);

		StatBlob statBlob1 = new StatBlob(0, 30, 0, 0, 0, 0, 0, 20, 20);
		StatBlob statBlob2 = new StatBlob(0, 0, 30, 0, 0, 0, 0, 20, 20);
		StatBlob statBlob3 = new StatBlob(0, 0, 0, 0, 0, 0, 0, 30, 0);
		WeaponTakeableItem pgo = new WeaponTakeableItem("chainsaw_item", position2, 4.05,statBlob1, 5, WeaponItemType.ONE_HANDED_WEAPON);
		ArmorTakeableItem pgo2 = new ArmorTakeableItem("cape_item", position3, 4.05,statBlob2, 5, ArmorItemType.ARMOR);
		EffectTakeableItem pgo3 = new EffectTakeableItem("potion_item", position4, 4.05, statBlob3);
		gameMap.getTileAt((new Position(0,0))).add(avatar);
		gameMap.getTileAt(position2).add(pgo);
		gameMap.getTileAt(position3).add(pgo2);
		gameMap.getTileAt(position4).add(pgo3);
		Position position = new Position(2,0);
		Position position5 = new Position(4,4);
		Npc buddy = new Npc("buddy",position,  statBlob1);
		buddy.setStoryline(new Storyline(" >> I'm Luigi."));
		buddy.getInventory().add(pgo);
		
		
		NonTradingNPC sheep = new NonTradingNPC("sheep", position5, statBlob1);
		
		TradingNPC shopkeeper = new TradingNPC("shopkeeper", position7, statBlob1);
		sheep.setStoryline(new Storyline(" >> I'm a sheep."));
		sheep.getInventory().add(pgo);
		
		shopkeeper.setStoryline(new Storyline(" >> Hey, I haven't seen you around before... "));
		shopkeeper.getInventory().add(pgo);
		gameMap.getTileAt(position).add(buddy);
		gameMap.getTileAt(position5).add(sheep);
		gameMap.getTileAt(position7).add(shopkeeper);
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
