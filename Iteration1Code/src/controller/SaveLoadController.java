package controller;

import java.util.*;
import java.io.*;
import model.*;

public class SaveLoadController {
	
	public static void save(Game game) throws IOException {
		// create the PrintWriter that will write to the file
		PrintWriter out = new PrintWriter(new File("savedGame.txt"));
		
		// write everything about the avatar
		Avatar avatar = game.getAvatar();
		out.println(avatar);
		
		// write everything about the map
		GameMap map = game.getMap();
		out.println(map);		
		
		out.close();
		System.out.println("GAME SAVED\n---------------");
	}
	
	public static Game load() throws IOException {
		System.out.println("LOADING GAME");
		Scanner in = new Scanner(new File("savedGame.txt"));
		Game game = new Game();
		
		// load the Avatar information
		Avatar avatar = null;
		String[] avatarName = in.next().split(":");
		String name = avatarName[1];
		
		String[] avatarOccupation = in.next().split(":");
		String occupation = avatarOccupation[1];
		
		// fix this later
		if (occupation.equals("Terminator")) avatar = new Avatar(new Terminator());
		if (occupation.equals("Alchemist")) avatar = new Avatar(new Alchemist());
		if (occupation.equals("Hunter")) avatar = new Avatar(new Hunter());
		
		for (int i = 0; i < 9; i++) {
			String[] primaryStat = in.next().split(":");
			String key = primaryStat[0];
			int value = Integer.parseInt(primaryStat[1]);
			avatar.setStatValue(key, value);
		}		
		
		String[] avatarDirection = in.next().split(":");
		int direction = Integer.parseInt(avatarDirection[1]);

		avatar.setName(name);
		avatar.setDirection(direction);
		
		Inventory inventory = new Inventory();
		String[] avatarInventory = in.next().split(":");
		int inventorySize = Integer.parseInt(avatarInventory[1]);
		
		// query through all items in the inventory
		for (int j = 0; j < inventorySize; j++) {
			String[] inventoryItem = in.next().split(":");
			String itemType = inventoryItem[1];
			int itemValue = Integer.parseInt(inventoryItem[2]);
			// fix this later too
			if (itemType.equals("Armor")) inventory.findAndEquip(new Armor(itemValue));
			if (itemType.equals("Weapon")) inventory.findAndEquip(new Weapon(itemValue));
		}
		
		Equipment equipment = new Equipment();
		
		String[] equipmentArmor = in.next().split(":");
		int armorValue = Integer.parseInt(equipmentArmor[2]);
		if (armorValue != -1) equipment.equipSlot(Equipment.ARMOR_SLOT,new Armor(armorValue));
		
		String[] equipmentWeapon = in.next().split(":");
		int weaponValue = Integer.parseInt(equipmentWeapon[2]);
		if (weaponValue != -1) equipment.equipSlot(Equipment.WEAPON_SLOT,new Weapon(weaponValue));
		
		String[] avatarLevels = in.next().split(":");
		int levels = Integer.parseInt(avatarLevels[1]);
		avatar.setLevels(levels);
		
		avatar.setInventory(inventory);
		avatar.setEquipment(equipment);
		game.setAvatar(avatar);
		
		// load the GameMap information
		GameMap map = new GameMap();
		map.setAvatar(avatar);
		String[] mapSize = in.next().split(":");
		String[] size = mapSize[1].split(",");
		int height = Integer.parseInt(size[0]);
		int width = Integer.parseInt(size[1]);
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Terrain terrain = null;
				Decal decal = null;
				String[] tileLocation = in.next().split(":");
				
				String[] tileTerrain = in.next().split(":");
				if (tileTerrain[0].equals("Terrain")) {
					String terrainType = tileTerrain[1];
					if (terrainType.equals("M")) terrain = new MountainTerrain();
					if (terrainType.equals("R")) terrain = new RadioactiveWasteTerrain();
					if (terrainType.equals("D")) terrain = new DesertTerrain();
				}
				
				String[] tileDecal = in.next().split(":");
				if (tileDecal[0].equals("Decal")) {
					String decalID = tileDecal[1];
					if (decalID.equals("+")) decal = new RedCrossDecal();
					if (decalID.equals("X")){
						AreaEffect effect = null;
						String[] tileEffect = in.next().split(":");
						String effectType = tileEffect[0];
						if (effectType.equals("DeathAreaEffect")) effect = new DeathAreaEffect();
						if (effectType.equals("DamageAreaEffect")) {
							double value = Double.parseDouble(tileEffect[1]);
							effect = new DamageAreaEffect(value);
						}
						decal = new SkullAndCrossbonesDecal(effect);
					}
					if (decalID.equals("*")) decal = new GoldStarDecal();
				}
				Tile tile = new Tile(terrain, decal, row, col);
				
				Item item = null;
				String[] tileItem = in.next().split(":");
				if (tileItem[0].equals("Item")) {
					String itemType = tileItem[1];
					if (itemType.equals("GiantRock")) item = new GiantRock();
					else if (itemType.equals("TreasureChest")) {
						item = new TreasureChest();
						String actionDone = tileItem[2];
						if (actionDone.equals("true")) ((TreasureChest) item).setActionDone();
					} else {
						double value = Double.parseDouble(tileItem[2]);
						if (itemType.equals("DamagingOneShotItem")) item = new DamagingOneShotItem(value);
						if (itemType.equals("HealingOneShotItem")) item = new HealingOneShotItem(value);
						if (itemType.equals("Armor")) item = new Armor((int)value);
						if (itemType.equals("Weapon")) item = new Weapon((int)value);
					}
				}
				tile.setItem(item);	
				map.setTile(tile);
			}
		}
		
		int entities = Integer.parseInt(in.next());
		for (int k = 0; k < entities; k++) {
			String[] entityLocation = in.next().split(":");
			String entityName = entityLocation[0];
			String[] location = entityLocation[1].split(",");
			int x = Integer.parseInt(location[0]);
			int y = Integer.parseInt(location[1]);
			// will fix this later to include all entities
			map.updateEntityLocation(avatar, new Location(x, y));
		}
		
		game.setMap(map);
		System.out.println("GAME LOADED\n---------------");
		return game;
	}
}
