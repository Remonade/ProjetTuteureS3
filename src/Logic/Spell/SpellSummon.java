/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import Logic.Data.EntityData;
import Logic.EntityUnit;
import Logic.IA.IA;
import Logic.Realm;

public class SpellSummon extends Spell{
	private EntityData m_type;
	public SpellSummon(String name, float cost, float cooldown, EntityData type) {
		super(name,cost,cooldown);
		m_type=type;
		m_icone="icone/summon.png";
	}
	public SpellSummon(String name, float cost, float cooldown, String type) {
		super(name,cost,cooldown);
		m_type=EntityData.get(type);
		m_icone="icone/summon.png";
	}
	public SpellSummon(String name, float cost, float cooldown, int charge, EntityData type) {
		super(name,cost,cooldown,charge);
		m_type=type;
		m_icone="icone/summon.png";
	}
	public SpellSummon(String name, float cost, float cooldown, int charge, String type) {
		super(name,cost,cooldown,charge);
		m_type=EntityData.get(type);
		m_icone="icone/summon.png";
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
