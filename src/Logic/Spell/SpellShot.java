/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import Logic.Data.EntityDataMissile;
import Logic.EntityMissile;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;

public class SpellShot  extends Spell {
	public SpellShot(String name, float cost, float cooldown) {
		super(name,cost,cooldown);
		m_icone="icone/spread.png";
	}
	
	public SpellShot(String name, float cost, float cooldown, int charge) {
		super(name,cost,cooldown,charge);
		m_icone="icone/spread.png";
	}
	@Override
	public void script(EntityUnit u) {
		//u.heal(10*(int)m_energyCost);
		
		float speed=-1;
		if(u.getLookRight())
			speed=1;
		
		EntityMissile mis;
		
		mis=new EntityMissile();
		mis.setDir(new Vector2f(0.75f*speed,0f));
		Realm.getActiveRealm().addEntity(mis);
		mis.setPos(u.getPos());
		mis.setData(EntityDataMissile.get("missile"));
		mis.setOwner(u);
		mis.setTeam(u.getTeam());
	}
	public float getCooldownPercent() {
		return m_currentCooldown/m_baseCooldown;
	}

}
