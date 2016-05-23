
package Logic.Spell;

import Logic.Data.DataBuff;
import Logic.Buff.BuffHealOverTime;
import Logic.Data.DataSpell;
import Logic.EntityUnit;

public class SpellHeal extends Spell{
	
	private int m_heal;
	
	public SpellHeal(DataSpell dataModel) {
		super(dataModel);
	}
	
	@Override
	public void script(EntityUnit u) {
		//u.heal(m_heal);
		u.addBuff(new BuffHealOverTime(DataBuff.getDataBuff("regen")));
	}

}
