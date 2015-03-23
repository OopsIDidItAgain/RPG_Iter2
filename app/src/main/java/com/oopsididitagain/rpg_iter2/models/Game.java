package com.oopsididitagain.rpg_iter2.models;

import java.util.ArrayList;

import com.oopsididitagain.rpg_iter2.assets.MapDatabase;
import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.NonTradingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Npc;
import com.oopsididitagain.rpg_iter2.models.entities.TradingNPC;
import com.oopsididitagain.rpg_iter2.models.items.ArmorTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EffectTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.InteractiveItem;
import com.oopsididitagain.rpg_iter2.models.items.Teleporter;
import com.oopsididitagain.rpg_iter2.models.items.WeaponTakeableItem;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.AreaEffectType;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.IOUtil;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class Game {

	private Avatar avatar;
	private GameMap gameMap;
	private ArrayList<Npc> listOfNpcs = new ArrayList<Npc>();
	int level;
	boolean tutorial;
	
	public Game(Avatar avatar, int level, boolean tutorial) {
		this.avatar = avatar;
		this.level = level;
		this.tutorial = tutorial;

		initialize();
	}
	
	public Game(Avatar avatar, int level) {
		this.avatar = avatar;
		this.level = level;
		
		initialize();
		
	}
	
	public Game( Avatar avatar, GameMap gameMap){
	
		this.avatar = avatar;
		this.gameMap = gameMap;
	}
	
	// constructor for load
	public Game(MapDatabase mapDatabase) {
		this.gameMap = new GameMap(mapDatabase);
		this.avatar = mapDatabase.getAvatar();
		this.gameMap.getTileAt(avatar.getPosition()).add(avatar);
		this.listOfNpcs = gameMap.getListOfNpcs();
	}
	
	public void initialize() {
		if (tutorial && this.level < 100) this.level += 100;

		gameMap = new GameMap(new MapDatabase(level));
		gameMap.getTileAt((new Position(0,0))).add(avatar);
		listOfNpcs = gameMap.getListOfNpcs();
				
		// TELEPORTER!		
		Position p = new Position(gameMap.getHeight()-1,gameMap.getWidth()-1);
		Teleporter door = new Teleporter("teleporter", p);
		gameMap.getTileAt(p).add(door);
	}
	public Avatar getAvatar(){
		
		return avatar;
	}
	public GameMap getGameMap(){
		
		return gameMap;
	}
	public ArrayList<Npc> getListOfNpcs(){
		return listOfNpcs;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setTutorial(boolean tutorial) {
		this.tutorial = tutorial;
	}
	

}
