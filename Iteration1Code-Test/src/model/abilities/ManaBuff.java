package model.abilities;

import utilities.EarthSpellSoundEffect;
import utilities.GloryHealSoundEffect;
import utilities.SoundEffect;
import model.entity.Entity;
import model.entity.EntityEffectHandler;

public class ManaBuff extends BoonSpellSingle{
	private int manaRequired = 0;
	private int manaRestored = 10;

	public ManaBuff(Entity entity) {
		super(entity);
	}

	public void applyBoon(Entity entityToAffect) {
		SoundEffect gogo = new GloryHealSoundEffect();
		EntityEffectHandler.addMP(entityToAffect,this.manaRestored);
	}

	public int getManaRequirement() {
		return this.manaRequired;
	}

	public int getBoon() {
		return this.manaRestored;
	}
}
