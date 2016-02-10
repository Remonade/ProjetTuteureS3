
package Logic.Spell;

import Logic.Buff.BuffAutoAttack;
import Logic.Buff.BuffHOT;
import Logic.EntityUnit;

public class SpellHeal extends Spell{
	
	private int m_heal;
	
	public SpellHeal(String name, float cost, float cooldown, int heal) {
		super(name,cost,cooldown);
		m_heal=heal;
		m_icone="icone/heal.png";
	}
	public SpellHeal(String name, float cost, float cooldown, int charge, int heal) {
		super(name,cost,cooldown,charge);
		m_heal=heal;
		m_icone="icone/heal.png";
	}
	
	@Override
	public void script(EntityUnit u) {
		//u.heal(m_heal);
		u.addBuff(new BuffHOT("regen",10,20,500));
		u.addBuff(new BuffAutoAttack("auto",10f,20,null));
	}

}
