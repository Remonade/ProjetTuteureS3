
package Logic.Spell;

import static Graphic.GraphicMain.getModel;
import Logic.Buff.BuffDash;
import Logic.Data.DataBuff;
import Logic.EntityUnit;

public class SpellDash extends Spell {
	
	protected float m_duration;
	protected float m_range;
	
	
	public SpellDash(String name, float cost, float cooldown) {
		super(name,cost,cooldown);
		m_energyCost=cost;
		m_baseCooldown=cooldown;
		m_name=name;
		m_icone=getModel("Idash");
	}
	
	public SpellDash(String name, float cost, float cooldown, int charge) {
		super(name,cost,cooldown,charge);
		m_energyCost=cost;
		m_baseCooldown=cooldown;
		m_name=name;
		m_charge=charge;
		m_icone=getModel("Idash");
	}
	
	@Override
	public void script(EntityUnit u) {
		u.addBuff(new BuffDash(DataBuff.getDataBuff("dash")));
	}

}
