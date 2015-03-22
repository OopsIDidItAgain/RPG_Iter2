package com.oopsididitagain.rpg_iter2.models;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Entity;
import com.oopsididitagain.rpg_iter2.utils.Battleable;

public class Battle {

	private HashMap<Battleable, Position> monsters;
	private HashMap<Battleable, Position> party;
	private MiniMap battleground;
	private LinkedList<Battleable> order;
	
	public Battle() {} //use this constructor if you want to set monsters and party using setMonsters() and setParty()
	
	public Battle(HashMap<Battleable, Position> monsters, HashMap<Battleable, Position> party) {
		this.monsters = monsters;
		this.party = party;
		sortEntities();
	}
	
	public void setMonsters(ArrayList<Battleable> creatures, ArrayList<Position> positions) throws Exception {
		if(creatures.size() > positions.size()) {
			throw new Exception();
		}
		
		monsters.clear();
		for(int i = 0; i < creatures.size(); i++) {
			Battleable creature = creatures.get(i);
			Position position = positions.get(i);
			monsters.put(creature, position);
		}
		
		if(party != null) {
			if(!party.isEmpty()) {
				sortEntities();
			}
		}
	}
	
	public void setParty(ArrayList<Battleable> members, ArrayList<Position> positions) throws Exception {
		if(members.size() > positions.size()) {
			throw new Exception();
		}
		
		party.clear();
		for(int i = 0; i < members.size(); i++) {
			Battleable member = members.get(i);
			Position position = positions.get(i);
			party.put(member, position);
		}
		
		if(monsters != null) {
			if(!monsters.isEmpty()) {
				sortEntities();
			}
		}
	}
	
	public void addMonster(Battleable monster, Position position) {
		if(!monsters.containsKey(monster)) {
			monsters.put(monster, position);
		}
	}
	
	public void addPartyMember(Battleable member, Position position) {
		if(!party.containsKey(member)) {
			party.put(member, position);
		}
	}
	
	public void updatePosition(Battleable entity, Position newPosition, boolean partOfParty) {
		if(partOfParty) {
			party.remove(entity);
			party.put(entity, newPosition);
		} else {
			monsters.remove(entity);
			monsters.put(entity, newPosition);
		}
	}
	
	private void sortEntities() {
		LinkedList<Battleable> sortedMonsters = new LinkedList<Battleable>();
		LinkedList<Battleable> sortedParty = new LinkedList<Battleable>();
		
		Iterator<Battleable> monsterIterator = monsters.keySet().iterator();
		while(monsterIterator.hasNext()) {
			Battleable monster = monsterIterator.next();
			
			if(sortedMonsters.isEmpty()) {
				sortedMonsters.add(monster);
			} else {
				for(int i = 0; i < sortedMonsters.size(); i++) {
					if(monster.statBlob().getMovement() > sortedMonsters.get(i).statBlob().getMovement()) {
						sortedMonsters.add(i, monster);
					}
				}
				
				if(!sortedMonsters.contains(monster)) {
					sortedMonsters.add(monster);
				}
			}
			
			monsterIterator.remove();
		}
		
		Iterator<Battleable> partyIterator = party.keySet().iterator();
		while(partyIterator.hasNext()) {
			Battleable member = partyIterator.next();
			
			if(sortedParty.isEmpty()) {
				sortedParty.add(member);
			} else {
				for(int i = 0; i < sortedParty.size(); i++) {
					if(member.statBlob().getMovement() > sortedParty.get(i).statBlob().getMovement()) {
						sortedParty.add(i, member);
					}
				}
				
				if(!sortedParty.contains(member)) {
					sortedParty.add(member);
				}
			}
			
			partyIterator.remove();
		}
		
		int a = 0;
		int b = 0;
		while(a < sortedMonsters.size() && b < sortedParty.size()) {
			Battleable monster = sortedMonsters.get(a);
			Battleable member = sortedParty.get(b);
			
			if(monster.statBlob().getMovement() >= member.statBlob().getMovement()) {
				order.add(monster);
				a++;
			} else {
				order.add(member);
				b++;
			}
		}
		if(a < sortedMonsters.size()) {
			while(a < sortedMonsters.size()) {
				order.add(sortedMonsters.get(a));
				a++;
			}
		} else {
			while(b < sortedParty.size()) {
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
		
	public void visit(Avatar avatar) {
		//TODO: Add Avatar actions
	}
	
	public void visit(AttackingNPC npc) {
		//TODO: Add npc actions
	}
	
	public void start() {
		while(!monsters.isEmpty() && !party.isEmpty()) {
			for(int i = 0; i < order.size(); i++) {
				Battleable entity = order.get(i);
				entity.accept(this);
			}
		}
	}
	
}