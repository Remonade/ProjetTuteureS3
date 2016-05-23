/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import Logic.Data.DataSpell;
import Logic.Data.EntityData;
import Logic.EntityUnit;
import Logic.IA.IA;
import Logic.Realm;

public class SpellSummon extends Spell{
	private EntityData m_type;
	public SpellSummon(DataSpell dataModel) {
		super(dataModel);
	}
	
	@Override
	public void script(EntityUnit u) {
		EntityUnit s=new EntityUnit();
		s.setData(m_type);
		s.setPos(u.getPos());
		s.setTeam(u.getTeam());
		s.setIA(new IA(360));
		Realm.getActiveRealm().addEntity(s);
	}

}
