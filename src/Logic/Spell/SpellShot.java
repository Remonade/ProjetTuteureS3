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

public class SpellShot  extends Spell {
	public SpellShot(DataSpell dataModel) {
		super(dataModel);
	}
	@Override
	public void script(EntityUnit u) {
		float speed=-1;
		if(u.getLookRight())
			speed=1;
		
		EntityDataMissile dataMissile = getEntityDataMissile();
		if(dataMissile != null) {
			EntityMissile mis;
			mis=new EntityMissile();
			mis.setData(dataMissile);
			mis.setDir(new Vector2f(0.75f*speed,0f));
			Realm.getActiveRealm().addEntity(mis);
			mis.setPos(u.getPos());
			mis.setOwner(u);
			mis.setTeam(u.getTeam());
		}
	}
	
	public String getEntityDataMissileName() {
		return m_dataModel != null ? m_dataModel.getStringProperty("missileType") : "";
	}
	
	public EntityDataMissile getEntityDataMissile() {
		String missile = getEntityDataMissileName();
		return !missile.equals("") ? (EntityDataMissile)EntityDataMissile.get(missile) : null;
	}
}
