/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import Logic.Data.DataSpell;
import Logic.Data.EntityDataMissile;
import Logic.EntityMissile;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;

public class SpellShotSpread extends SpellShot {
	public SpellShotSpread(DataSpell dataModel) {
		super(dataModel);
	}
	
	
	@Override
	public void script(EntityUnit u) {
		if(getMissileCount()<2) {
			super.script(u);
		} else {
			EntityMissile mis;

			float speed=-1;
			if(u.getLookRight())
				speed=1;
			
			float spreadValue=0.1f;
			float spreedOffset=spreadValue/getMissileCount()*2;
			
			for(int i=0;i<getMissileCount();i++) {
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
	private int getMissileCount() {
		return m_dataModel != null ? m_dataModel.getIntegerProperty("missileCount") : 2;
	}
}
