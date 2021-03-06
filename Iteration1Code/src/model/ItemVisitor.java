package model;

public interface ItemVisitor {
	public void accept(Armor a);
	public void accept(Weapon w);
	public void accept(TwoHandedWeapon w);
	public void accept(GiantRock g);
	public void accept(HealingOneShotItem h);
	public void accept(DamagingOneShotItem d);
	public void accept(TreasureChest t);
	public void accept(Gloves gloves);
	public void accept(Leggings leggings);
	public void accept(Shield shield);
	public void accept(Helmet helmet);
	public void accept(Boots boots);
	public void accept(Projectile projectile);
}
