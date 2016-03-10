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

public class SpellShotSpread extends SpellShot {
	
	protected int m_missileCount=1;
	public SpellShotSpread(String name, float cost, float cooldown, int count) {
		super(name,cost,cooldown);
		m_missileCount=count;
	}
	
	public SpellShotSpread(String name, float cost, float cooldown, int charge, int count) {
		super(name,cost,cooldown,charge);
		m_missileCount=count;
	}
	
	
	@Override
	public void script(EntityUnit u) {
		if(m_missileCount<2) {
			super.script(u);
		} else {
			EntityMissile mis;

			float speed=-1;
			if(u.getLookRight())
				speed=1;
			
			float spreadValue=0.1f;
			float spreedOffset=spreadValue/m_missileCount*2;
			
			for(int i=0;i<m_missileCount;i++) {
				mis=new EntityMissile();
				mis.setData(EntityDataMissile.get("explosive"));
				mis.setDir(new Vector2f(0.75f*speed,spreadValue-spreedOffset*i));
				Realm.getActiveRealm().addEntity(mis);
				mis.setPos(u.getPos());
				mis.setOwner(u);
				mis.setTeam(u.getTeam());
			}
		}
	}

}
