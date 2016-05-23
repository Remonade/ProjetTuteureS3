
package Logic.Spell;


import Logic.Buff.BuffDash;
import Logic.Data.DataBuff;
import Logic.Data.DataSpell;
import Logic.EntityUnit;

public class SpellDash extends Spell {
	
	protected float m_duration;
	protected float m_range;
	
	
	public SpellDash(DataSpell dataModel) {
		super(dataModel);
	}
	
	@Override
	public void script(EntityUnit u) {
		u.addBuff(new BuffDash(DataBuff.getDataBuff("dash")));
	}

}
