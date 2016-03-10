
package Logic.Spell;

import static Graphic.GraphicMain.getModel;
import Logic.Buff.BuffHOT;
import Logic.EntityUnit;

public class SpellHeal extends Spell{
	
	private int m_heal;
	
	public SpellHeal(String name, float cost, float cooldown, int heal) {
		super(name,cost,cooldown);
		m_heal=heal;
		m_icone=getModel("Iheal");
	}
	public SpellHeal(String name, float cost, float cooldown, int charge, int heal) {
		super(name,cost,cooldown,charge);
		m_heal=heal;
		m_icone=getModel("Iheal");
	}
	
	@Override
	public void script(EntityUnit u) {
		//u.heal(m_heal);
		u.addBuff(new BuffHOT("regen",10,20,500));
	}

}
