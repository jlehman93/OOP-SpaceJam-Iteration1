package model;

import java.awt.image.BufferedImage;

import model.items.Equipable;
import model.items.TakeableItem;
import model.occupation.Occupation;
import model.slots.Equipment;
import model.slots.Inventory;
import utilities.*;

public class Avatar extends Entity {
	private int levels = 0;
	private Inventory inventory = new Inventory();
	private BufferedImage image;
	private BufferedImage[] spriteSheet;
	//deprecated all entities need a equipment now
//	private Equipment equipment = new Equipment();
	//private final String AVATAR_IMAGE = "src/res/img/sprite.jpg";
	private final static String[] primaryStats = {"Agility", "Experience", "Hardiness",
											"HP", "Intellect", "Lives", "MP",
											"Movement", "Strength"};
	
	public Avatar(Occupation occupation) {
		super(occupation);
		String occString = occupation.getName().toUpperCase();
		SpriteSheetUtility util = new SpriteSheetUtility(occString);
		spriteSheet = (util.getSpriteArray());
		//this.location = new Location(INITIAL_X_LIE, INITIAL_Y_LIE);
	}
	
	public String getStat(String s){
		return Integer.toString(stats.get(s).getValue());
	}
	
	/*
	public void move(int x,int y){ //this is not needed either -Juan
		location.add(x,y);
		//System.out.println(location);
	}
	
	public void loadImage(Graphics g)
	{	
		image= ImageProcessing.scaleImage(Scaling.AVATAR_WIDTH, Scaling.AVATAR_HEIGHT,AVATAR_IMAGE);
		int x= location.getX();
		int y= location.getY();
		g.drawImage(image,Scaling.AVATAR_WIDTH*x,Scaling.AVATAR_HEIGHT*y,null);
	}
	*/
	
	public BufferedImage getImage(){
		BufferedImage imageToDisplay = spriteSheet[direction];
		image= ImageProcessing.scaleImage(Scaling.AVATAR_WIDTH, Scaling.AVATAR_HEIGHT, imageToDisplay);
		return image;
	}
	
	public Inventory getInventory(){
		return inventory;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Equipment getEquipment() {
		return this.equipment;
	}
	
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}	
	
	// used for writing to the save file
	// name, occupation, stats, direction, inventory, equipment
	public String toString() {
		String result = "";
		result += "Avatar:" + this.name;
		result += "\n" + this.occupation;
		for (String key : primaryStats) result += "\n" + this.stats.get(key);
		result += "\nDirection:" + this.direction;
		result += "\n" + this.inventory;
		result += "\n" + this.equipment;
		result += "\nLevels:" + this.levels;
		return result;
	}

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
	}
	
	public TakeableItem unequipSlot(Point point){
		return this.equipment.unequipSlot(point);
	}
	
	public boolean equipSlot(Equipable equipItem){
		TakeableItem item = equipItem.equipSlot(equipment);
		boolean notEmpty = (item != null);
		if (notEmpty){
			inventory.findAndEquip(item);
			return true;
		}
		return false;
	}
	
	public boolean equipInventory(TakeableItem item){
		return this.inventory.findAndEquip(item);
	}
	
	public TakeableItem unequipInventorySlot(Point slotPoint){
		return this.inventory.unequip(slotPoint);
	}
	/*
	//private void attack() {
	//	
	//}
	//may not need to override
	 */
	
	/*
	//private void useAbility() {
	//	
	//}
	//may not need to override
	 */
	
	
}