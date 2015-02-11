package model;

public class WeaponSlot extends BufferSlot<Weapon>{
	WeaponSlot(DerivedStat offensiveRating){
		addObserver(offensiveRating);
	}
	WeaponSlot(){}

    public static void main(String[] args){
        WeaponSlot slot = new WeaponSlot();
        OffensiveRating stat = new OffensiveRating(0,slot);
    	slot.addObserver(stat);
        Weapon weapon = new Weapon(15);
        //slot.equip(item);
        slot.equip(weapon);
        System.out.println(stat);	//Should print out 15
        slot.unequip();
        System.out.println(stat);	//Should print out 0
    }
}
