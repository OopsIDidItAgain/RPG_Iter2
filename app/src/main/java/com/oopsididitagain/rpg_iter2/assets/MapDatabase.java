package com.oopsididitagain.rpg_iter2.assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oopsididitagain.rpg_iter2.models.AreaEffect;
import com.oopsididitagain.rpg_iter2.models.Armory;
import com.oopsididitagain.rpg_iter2.models.Decal;
import com.oopsididitagain.rpg_iter2.models.Inventory;
import com.oopsididitagain.rpg_iter2.models.Position;
import com.oopsididitagain.rpg_iter2.models.Terrain;
import com.oopsididitagain.rpg_iter2.models.Tile;
import com.oopsididitagain.rpg_iter2.models.Trap;
import com.oopsididitagain.rpg_iter2.models.entities.AttackingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.Avatar;
import com.oopsididitagain.rpg_iter2.models.entities.Bank;
import com.oopsididitagain.rpg_iter2.models.entities.EntityStatus;
import com.oopsididitagain.rpg_iter2.models.entities.NonTradingNPC;
import com.oopsididitagain.rpg_iter2.models.entities.TradingNPC;
import com.oopsididitagain.rpg_iter2.models.items.ArmorTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EffectTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.EquipableTakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.InteractiveItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryArmorItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUnusableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryUsableItem;
import com.oopsididitagain.rpg_iter2.models.items.InventoryWeaponItem;
import com.oopsididitagain.rpg_iter2.models.items.ObstacleItem;
import com.oopsididitagain.rpg_iter2.models.items.OneShotItem;
import com.oopsididitagain.rpg_iter2.models.items.TakeableItem;
import com.oopsididitagain.rpg_iter2.models.items.WeaponTakeableItem;
import com.oopsididitagain.rpg_iter2.models.occupations.Occupation;
import com.oopsididitagain.rpg_iter2.models.occupations.Smasher;
import com.oopsididitagain.rpg_iter2.models.occupations.Sneak;
import com.oopsididitagain.rpg_iter2.models.occupations.Summoner;
import com.oopsididitagain.rpg_iter2.models.stats.StatBlob;
import com.oopsididitagain.rpg_iter2.utils.AreaEffectType;
import com.oopsididitagain.rpg_iter2.utils.ArmorItemType;
import com.oopsididitagain.rpg_iter2.utils.Direction;
import com.oopsididitagain.rpg_iter2.utils.TiledEntityVisitable;
import com.oopsididitagain.rpg_iter2.utils.TiledProbeVisitable;
import com.oopsididitagain.rpg_iter2.utils.WeaponItemType;

public class MapDatabase {
	private BufferedReader gridReader;
	private InputStream gridFileStream;
	private BufferedReader objectsReader;
	private InputStream objectsFileStream;
	private List<TiledEntityVisitable> tiledEntityVisitables;
	private List<TiledProbeVisitable> tiledProbeVisitables;
	private Tile[][] tiles;

	String[][] mapGrid;
	int mapX;
	int mapY;
	private Avatar avatar;

	public MapDatabase(int level) {
		tiledEntityVisitables = new ArrayList<TiledEntityVisitable>();
		tiledProbeVisitables = new ArrayList<TiledProbeVisitable>();
		gridFileStream = getClass().getResourceAsStream(
				"/levels/level" + level + "/grid.csv");
		objectsFileStream = getClass().getResourceAsStream(
				"/levels/level" + level + "/tileables.csv");

		gridReader = new BufferedReader(new InputStreamReader(gridFileStream));
		objectsReader = new BufferedReader(new InputStreamReader(
				objectsFileStream));

		readTerrain();
		setTiles();
		readObjects();
	}

	public MapDatabase(int level, File grid, File tileables) {
		tiledEntityVisitables = new ArrayList<TiledEntityVisitable>();
		tiledProbeVisitables = new ArrayList<TiledProbeVisitable>();
		try {
			gridFileStream = new FileInputStream(grid);
			objectsFileStream = new FileInputStream(tileables);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		gridReader = new BufferedReader(new InputStreamReader(gridFileStream));
		objectsReader = new BufferedReader(new InputStreamReader(
				objectsFileStream));

		readTerrain();
		setTiles();
		readObjects();
	}

	public int getMapY() {
		return mapY;
	}

	public int getMapX() {
		return mapX;
	}

	public String getObjectsAtYX(int y, int x) {

		return mapGrid[y][x];
	}

	private void readTerrain() {

		String[] dimensions = readTerrainLine().split(",");
		mapX = Integer.parseInt(dimensions[0]);
		mapY = Integer.parseInt(dimensions[1]);

		mapGrid = new String[mapY][mapX];

		readTerrainLine(); // blank line

		String s;

		for (int i = 0; i < mapY/* y */; i++) {
			s = readTerrainLine();
			System.out.println(s);

			if (s.equals("")) {
				// default behavior if something's wrong with the map
				for (int j = 0; j < mapX; j++) {
					System.out.println("Map did not load properly");
					mapGrid[i][j] = Terrain.GRASS.getId();
				}
				continue;
			}

			String[] array = s.split(",");
			for (int j = 0; j < mapX; j++) {

				switch (array[j]) {
				case "^":
					mapGrid[i][j] = "M";
					break;
				case "-":
					mapGrid[i][j] = "G";
					break;
				case "~":
					mapGrid[i][j] = "W";
					break;
				default:
					System.out
							.println("Error, unknown terrain type when reading map...");
					break;
				}
			}
		}
	}

	public void setTiles() {
		int mapX = getMapX();
		int mapY = getMapY();
		this.tiles = new Tile[mapY][mapX];
		for (int i = 0; i < mapY; i++) {
			for (int j = 0; j < mapX; j++) {
				switch (getTerrainAtYX(i, j)) {
				case "G":
					tiles[i][j] = new Tile(new Position(i, j), Terrain.GRASS);
					break;
				case "M":
					tiles[i][j] = new Tile(new Position(i, j), Terrain.MOUNTAIN);
					break;
				case "W":
					tiles[i][j] = new Tile(new Position(i, j), Terrain.WATER);
					break;
				default:
					tiles[i][j] = new Tile(new Position(i, j), Terrain.GRASS);
					break;
				}
			}
		}
	}

	private String readTerrainLine() {
		return readNextLine(gridReader);
	}

	public String getTerrainAtYX(int y, int x) {
		return mapGrid[y][x];
	}

	public List<TiledProbeVisitable> getProbeVisitables() {
		return tiledProbeVisitables;
	}

	public List<TiledEntityVisitable> getEntityVisitables() {
		return tiledEntityVisitables;
	}

	private void readObjects() {
		String line;
		Map<String, EffectTakeableItem> usableMap = new HashMap<String, EffectTakeableItem>();
		Map<String, TakeableItem> unusableMap = new HashMap<String, TakeableItem>();
		Map<String, EquipableTakeableItem> equipableMap = new HashMap<String, EquipableTakeableItem>();

		while ((line = readObjectsLine()) != null) {
			System.out.println(line);
			String[] tokens = line.trim().split(",");
			String SAVED_TYPE = tokens[0];
			// empty row, but newline char
			if (SAVED_TYPE.length() == 0)
				break;
			boolean isDone = false;
			/* Do switch on typestuff */
			switch (SAVED_TYPE) {
			case "Items":
				/* Do Item Loading */
				isDone = false;
				while (!isDone && (line = readObjectsLine()) != null) {
					System.out.println(line);
					tokens = line.split(",");
					/* check if empty row, break out of this while loop */
					if (tokens.length == 0)
						break;
					String id = tokens[0];
					// empty row, but newline char
					if (tokens.length == 1 && tokens[0].length() == 0)
						break;
					int x = Integer.parseInt(tokens[1]);
					int y = Integer.parseInt(tokens[2]);
					Position position = new Position(y, x);
					String type = tokens[3];
					switch (type) {
					case "EffectTakeableItem":
						EffectTakeableItem eitem = new EffectTakeableItem(id,
								position, parsePrice(tokens[13]),
								parseStatBlob(4, tokens));
						tiledEntityVisitables.add(eitem);
						usableMap.put(id, eitem);
						break;
					case "WeaponTakeableItem":
						WeaponTakeableItem witem = new WeaponTakeableItem(id,
								position, parsePrice(tokens[13]),
								parseStatBlob(4, tokens), parseRank(tokens),
								parseWeaponType(tokens[15]));
						tiledEntityVisitables.add(witem);
						equipableMap.put(id, witem);
						break;
					case "ArmorTakeableItem":
						ArmorTakeableItem aitem = new ArmorTakeableItem(id,
								position, parsePrice(tokens[13]),
								parseStatBlob(4, tokens), parseRank(tokens),
								parseArmorType(tokens[15]));
						tiledEntityVisitables.add(aitem);
						equipableMap.put(id, aitem);
						break;
					case "OneShotItem":
						tiledEntityVisitables.add(new OneShotItem(id, position,
								parseStatBlob(4, tokens)));
						break;
					case "TakeableItem":
						TakeableItem titem = new TakeableItem(id, position,
								parsePrice(tokens[13]));
						tiledEntityVisitables.add(new TakeableItem(id,
								position, parsePrice(tokens[13])));
						unusableMap.put(id, titem);
						break;
					case "ObstacleItem":
						tiledProbeVisitables
								.add(new ObstacleItem(id, position));
						break;
					case "InteractiveItem":
						String requirementId = tokens[7];
						Position targetPosition = new Position(
								Integer.parseInt(tokens[5]),
								Integer.parseInt(tokens[4]));
						if (usableMap.containsKey(requirementId))
							tiledEntityVisitables.add(new InteractiveItem(id,
									position,
									tiles[targetPosition.getY()][targetPosition
											.getX()], parseTerrain(tokens),
									usableMap.get(requirementId)
											.toInventoryItem()));
						else if (equipableMap.containsKey(requirementId))
							tiledEntityVisitables.add(new InteractiveItem(id,
									position,
									tiles[targetPosition.getY()][targetPosition
											.getX()], parseTerrain(tokens),
									equipableMap.get(requirementId)
											.toInventoryItem()));
						else if (unusableMap.containsKey(requirementId))
							tiledEntityVisitables.add(new InteractiveItem(id,
									position,
									tiles[targetPosition.getY()][targetPosition
											.getX()], parseTerrain(tokens),
									(InventoryUnusableItem) unusableMap.get(
											requirementId).toInventoryItem()));
						break;
					case "AreaEffect":
						AreaEffectType aetype = parseAreaEffectType(tokens);
						Decal decal = parseDecal(aetype);
						tiledEntityVisitables.add(new AreaEffect(decal,
								position, aetype));
						break;
					case "Trap":
						Trap trap = new Trap(id, position);
						tiledEntityVisitables.add(trap);
						break;
					}
				}
				break;
			case "Avatar":
				/* Do Entity stuff - line right after Avatar tag */
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				// START BUILDING
				String id = tokens[0];
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				Direction direction = parseDirection(tokens[3]);
				Position position = new Position(y, x, direction);
				EntityStatus entityStatus = parseEntityStatus(tokens[4]);
				boolean isFlying = parseBoolean(tokens[5]);
				Bank bank = parseBank(tokens[6]);
				// STAT BLOB
				StatBlob statBlob = parseStatBlob(7, tokens);
				// NEW LINE Occupation type
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				Occupation occupation = parseOccupation(tokens[0]);
				// NEW LINE skill multipliers
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				parseSkills(occupation, tokens);
				// NEW LINE inventory tag
				line = readObjectsLine();
				// NEW LINE start of inventories
				Inventory inventory = new Inventory();
				Armory armory = new Armory();
				isDone = false;
				while (!isDone && (line = readObjectsLine()) != null) {
					System.out.println(line);
					tokens = line.split(",");
					/*
					 * check if empty row, break out of this while loop end of
					 * inventory
					 */
					if (tokens.length == 0)
						break;
					// empty row, but newline char
					if (tokens.length == 1 && tokens[0].length() == 0)
						break;
					String type = tokens[0];
					switch (type) {
					case "Usable":
						String iid = tokens[1];
						double price = parsePrice(tokens[2]);
						StatBlob sb = parseStatBlob(3, tokens);
						InventoryUsableItem usableItem = new InventoryUsableItem(
								iid, price, sb);
						inventory.add(usableItem);
						break;
					case "Unusable":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						InventoryUnusableItem unusableItem = new InventoryUnusableItem(
								iid, price);
						inventory.add(unusableItem);
						break;
					case "Weapon":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						WeaponItemType weaponType = parseWeaponType(tokens[3]);
						boolean isEquipped = parseBoolean(tokens[4]);

						int rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryWeaponItem weaponItem = new InventoryWeaponItem(
								iid, price, sb, weaponType, rank);
						inventory.add(weaponItem);
						// do logic for Armory
						if (isEquipped) {
							armory.equip(weaponItem);
						}
						break;
					case "Armor":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						ArmorItemType armorType = parseArmorType(tokens[3]);
						isEquipped = parseBoolean(tokens[4]);
						// do logic for Armory
						rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryArmorItem armorItem = new InventoryArmorItem(
								iid, price, sb, rank, armorType);
						inventory.add(armorItem);
						// do logic for Armory
						if (isEquipped) {
							armory.equip(armorItem);
						}
						break;
					}
				}
				avatar = new Avatar(id, position, statBlob, armory);
				avatar.setEntityStatus(entityStatus);
				avatar.setFlying(isFlying);
				avatar.setBank(bank);
				avatar.setOccupation(occupation);
				avatar.setInventory(inventory);
				// tiledProbeVisitables.add(avatar);
				// line is at empty row after last item in inventory
				// end of Avatar load
				break;
			case "AttackingNpc":
				/* Do Entity stuff - line right after tag */
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				// START BUILDING
				id = tokens[0];
				x = Integer.parseInt(tokens[1]);
				y = Integer.parseInt(tokens[2]);
				direction = parseDirection(tokens[3]);
				position = new Position(y, x, direction);
				entityStatus = parseEntityStatus(tokens[4]);
				isFlying = parseBoolean(tokens[5]);
				bank = parseBank(tokens[6]);
				// STAT BLOB
				statBlob = parseStatBlob(7, tokens);
				// NEW LINE inventory tag
				line = readObjectsLine();
				// NEW LINE start of inventories
				inventory = new Inventory();
				isDone = false;
				while (!isDone && (line = readObjectsLine()) != null) {
					System.out.println(line);
					tokens = line.split(",");
					/*
					 * check if empty row, break out of this while loop end of
					 * inventory
					 */
					if (tokens.length == 0)
						break;
					// empty row, but newline char
					if (tokens.length == 1 && tokens[0].length() == 0)
						break;
					String type = tokens[0];
					switch (type) {
					case "Usable":
						String iid = tokens[1];
						double price = parsePrice(tokens[2]);
						StatBlob sb = parseStatBlob(3, tokens);
						InventoryUsableItem usableItem = new InventoryUsableItem(
								iid, price, sb);
						inventory.add(usableItem);
						break;
					case "Unusable":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						InventoryUnusableItem unusableItem = new InventoryUnusableItem(
								iid, price);
						inventory.add(unusableItem);
						break;
					case "Weapon":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						WeaponItemType weaponType = parseWeaponType(tokens[3]);
						boolean isEquipped = parseBoolean(tokens[4]);

						int rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryWeaponItem weaponItem = new InventoryWeaponItem(
								iid, price, sb, weaponType, rank);
						inventory.add(weaponItem);
						break;
					case "Armor":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						ArmorItemType armorType = parseArmorType(tokens[3]);
						isEquipped = parseBoolean(tokens[4]);
						// do logic for Armory
						rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryArmorItem armorItem = new InventoryArmorItem(
								iid, price, sb, rank, armorType);
						inventory.add(armorItem);
						break;
					}
				}
				AttackingNPC attackingNpc = new AttackingNPC(id, position,
						statBlob);
				attackingNpc.setEntityStatus(entityStatus);
				attackingNpc.setFlying(isFlying);
				attackingNpc.setBank(bank);
				attackingNpc.setInventory(inventory);
				tiledProbeVisitables.add(attackingNpc);
				// line is at empty row after last item in inventory
				// end of AttackingNpc load
				break;
			case "NonTradingNpc":/* Do Entity stuff - line right after tag */
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				// START BUILDING
				id = tokens[0];
				x = Integer.parseInt(tokens[1]);
				y = Integer.parseInt(tokens[2]);
				direction = parseDirection(tokens[3]);
				position = new Position(y, x, direction);
				entityStatus = parseEntityStatus(tokens[4]);
				isFlying = parseBoolean(tokens[5]);
				bank = parseBank(tokens[6]);
				// STAT BLOB
				statBlob = parseStatBlob(7, tokens);
				// NEW LINE inventory tag
				line = readObjectsLine();
				// NEW LINE start of inventories
				inventory = new Inventory();
				isDone = false;
				while (!isDone && (line = readObjectsLine()) != null) {
					System.out.println(line);
					tokens = line.split(",");
					/*
					 * check if empty row, break out of this while loop end of
					 * inventory
					 */
					if (tokens.length == 0)
						break;
					// empty row, but newline char
					if (tokens.length == 1 && tokens[0].length() == 0)
						break;
					String type = tokens[0];
					switch (type) {
					case "Usable":
						String iid = tokens[1];
						double price = parsePrice(tokens[2]);
						StatBlob sb = parseStatBlob(3, tokens);
						InventoryUsableItem usableItem = new InventoryUsableItem(
								iid, price, sb);
						inventory.add(usableItem);
						break;
					case "Unusable":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						InventoryUnusableItem unusableItem = new InventoryUnusableItem(
								iid, price);
						inventory.add(unusableItem);
						break;
					case "Weapon":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						WeaponItemType weaponType = parseWeaponType(tokens[3]);
						boolean isEquipped = parseBoolean(tokens[4]);

						int rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryWeaponItem weaponItem = new InventoryWeaponItem(
								iid, price, sb, weaponType, rank);
						inventory.add(weaponItem);
						break;
					case "Armor":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						ArmorItemType armorType = parseArmorType(tokens[3]);
						isEquipped = parseBoolean(tokens[4]);
						// do logic for Armory
						rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryArmorItem armorItem = new InventoryArmorItem(
								iid, price, sb, rank, armorType);
						inventory.add(armorItem);
						break;
					}
				}
				NonTradingNPC nonTradingNPC = new NonTradingNPC(id, position,
						statBlob);
				nonTradingNPC.setEntityStatus(entityStatus);
				nonTradingNPC.setFlying(isFlying);
				nonTradingNPC.setBank(bank);
				nonTradingNPC.setInventory(inventory);
				tiledProbeVisitables.add(nonTradingNPC);
				// line is at empty row after last item in inventory
				// end of nonTradingNPC load
				break;
			case "TradingNpc":
				line = readObjectsLine();
				System.out.println(line);
				tokens = line.split(",");
				// START BUILDING
				id = tokens[0];
				x = Integer.parseInt(tokens[1]);
				y = Integer.parseInt(tokens[2]);
				direction = parseDirection(tokens[3]);
				position = new Position(y, x, direction);
				entityStatus = parseEntityStatus(tokens[4]);
				isFlying = parseBoolean(tokens[5]);
				bank = parseBank(tokens[6]);
				// STAT BLOB
				statBlob = parseStatBlob(7, tokens);
				// NEW LINE inventory tag
				line = readObjectsLine();
				// NEW LINE start of inventories
				inventory = new Inventory();
				isDone = false;
				while (!isDone && (line = readObjectsLine()) != null) {
					System.out.println(line);
					tokens = line.split(",");
					/*
					 * check if empty row, break out of this while loop end of
					 * inventory
					 */
					if (tokens.length == 0)
						break;
					// empty row, but newline char
					if (tokens.length == 1 && tokens[0].length() == 0)
						break;
					String type = tokens[0];
					switch (type) {
					case "Usable":
						String iid = tokens[1];
						double price = parsePrice(tokens[2]);
						StatBlob sb = parseStatBlob(3, tokens);
						InventoryUsableItem usableItem = new InventoryUsableItem(
								iid, price, sb);
						inventory.add(usableItem);
						break;
					case "Unusable":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						InventoryUnusableItem unusableItem = new InventoryUnusableItem(
								iid, price);
						inventory.add(unusableItem);
						break;
					case "Weapon":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						WeaponItemType weaponType = parseWeaponType(tokens[3]);
						boolean isEquipped = parseBoolean(tokens[4]);

						int rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryWeaponItem weaponItem = new InventoryWeaponItem(
								iid, price, sb, weaponType, rank);
						inventory.add(weaponItem);
						break;
					case "Armor":
						iid = tokens[1];
						price = parsePrice(tokens[2]);
						ArmorItemType armorType = parseArmorType(tokens[3]);
						isEquipped = parseBoolean(tokens[4]);
						// do logic for Armory
						rank = Integer.parseInt(tokens[5]);
						sb = parseStatBlob(6, tokens);
						InventoryArmorItem armorItem = new InventoryArmorItem(
								iid, price, sb, rank, armorType);
						inventory.add(armorItem);
						break;
					}
				}
				TradingNPC tradingNPC = new TradingNPC(id, position, statBlob);
				tradingNPC.setEntityStatus(entityStatus);
				tradingNPC.setFlying(isFlying);
				tradingNPC.setBank(bank);
				tradingNPC.setInventory(inventory);
				tiledProbeVisitables.add(tradingNPC);
				// line is at empty row after last item in inventory
				// end of nonTradingNPC load
				break;
			default:
				break;
			}
		}
	}

	private Direction parseDirection(String token) {
		switch (token) {
		case "EAST":
			return Direction.EAST;
		case "NORTH":
			return Direction.NORTH;
		case "NORTHEAST":
			return Direction.NORTHEAST;
		case "NORTHWEST":
			return Direction.NORTHWEST;
		case "SOUTH":
			return Direction.SOUTH;
		case "SOUTHEAST":
			return Direction.SOUTHEAST;
		case "SOUTHWEST":
			return Direction.SOUTHWEST;
		case "WEST":
			return Direction.WEST;
		default:
			return Direction.SOUTH;
		}
	}

	private EntityStatus parseEntityStatus(String token) {
		return new EntityStatus(Integer.parseInt(token));
	}

	private boolean parseBoolean(String token) {
		if (token.equals("TRUE"))
			return true;
		return false;

	}

	private Bank parseBank(String token) {
		return new Bank(Double.parseDouble(token));
	}

	private Occupation parseOccupation(String token) {
		switch (token) {
		case "Sneak":
			return new Sneak();
		case "Summoner":
			return new Summoner();
		case "Smasher":
			return new Smasher();
		default:
			return new Summoner();
		}
	}

	private void parseSkills(Occupation o, String tokens[]) {
		for (int i = 0; i < tokens.length; i++) {
			int multiplier = Integer.parseInt(tokens[i]);
			for (int j = 0; j < multiplier; j++) {
				o.increaseMultiplier(i);
			}
		}
	}

	private Decal parseDecal(AreaEffectType type) {
		switch (type) {
		case HEAL_DAMAGE:
			return new Decal("heal_damage_decal");
		case INSTANT_DEATH:
			return new Decal("instant_death_decal");
		case LEVEL_UP:
			return new Decal("level_up_decal");
		case TAKE_DAMAGE:
			return new Decal("take_damage_decal");
		}
		return new Decal("heart_decal");
	}

	private AreaEffectType parseAreaEffectType(String[] tokens) {
		switch (tokens[4]) {
		case "LEVEL_UP":
			return AreaEffectType.LEVEL_UP;
		case "HEAL_DAMAGE":
			return AreaEffectType.HEAL_DAMAGE;
		case "TAKE_DAMAGE":
			return AreaEffectType.TAKE_DAMAGE;
		case "INSTANT_DEATH":
			return AreaEffectType.INSTANT_DEATH;
		}
		return AreaEffectType.LEVEL_UP;
	}

	private Terrain parseTerrain(String[] tokens) {
		String type = tokens[6];
		switch (type) {
		case "GRASS":
			return Terrain.GRASS;
		case "WATER":
			return Terrain.WATER;
		case "MOUNTAIN":
			return Terrain.MOUNTAIN;
		}
		return Terrain.GRASS;
	}

	private ArmorItemType parseArmorType(String token) {
		switch (token) {
		case "ARMOR":
			return ArmorItemType.ARMOR;
		case "BOOTS":
			return ArmorItemType.BOOTS;
		case "HELMET":
			return ArmorItemType.HELMET;
		}
		return ArmorItemType.ARMOR;
	}

	private int parseRank(String[] tokens) {
		return Integer.parseInt(tokens[14]);
	}

	private WeaponItemType parseWeaponType(String token) {
		switch (token) {
		case "ONE_HANDED_WEAPON":
			return WeaponItemType.ONE_HANDED_WEAPON;
		case "TWO_HANDED_WEAPON":
			return WeaponItemType.TWO_HANDED_WEAPON;
		case "FISTS":
			return WeaponItemType.FISTS;
		case "RANGED_WEAPON":
			return WeaponItemType.RANGED_WEAPON;
		case "STAFF":
			return WeaponItemType.STAFF;
		}
		return WeaponItemType.FISTS;
	}

	private double parsePrice(String token) {
		return Double.parseDouble(token);
	}

	private StatBlob parseStatBlob(int start, String[] tokens) {
		StatBlob statBlob = new StatBlob(Double.parseDouble(tokens[start]),
				Double.parseDouble(tokens[start + 1]),
				Double.parseDouble(tokens[start + 2]),
				Double.parseDouble(tokens[start + 3]),
				Double.parseDouble(tokens[start + 4]),
				Double.parseDouble(tokens[start + 5]),
				Double.parseDouble(tokens[start + 6]),
				Double.parseDouble(tokens[start + 7]),
				Double.parseDouble(tokens[start + 8]));
		return statBlob;
	}

	private String readObjectsLine() {
		return readNextLine(objectsReader);
	}

	private String readNextLine(BufferedReader br) {
		try {
			return br.readLine();
		} catch (IOException e) {
			System.out.println("Reading line didn't work....");
			e.printStackTrace();
			return null;
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public Avatar getAvatar() {
		return avatar;
	}
}
