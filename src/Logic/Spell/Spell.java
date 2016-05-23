/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import Graphic.Model;
import Logic.Buff.Buff;
import Logic.Data.DataSpell;
import Logic.EntityUnit;

public class Spell {
	protected DataSpell m_dataModel;
	protected float m_currentCooldown=0;
	protected int m_remainingCharge=0;
	
	public Spell(DataSpell dataModel) {
		m_dataModel=dataModel;
		if(m_dataModel!=null) {
			m_remainingCharge = getMaxChargeCount();
		}
	}
	public String getName() {
		return m_dataModel != null ? m_dataModel.getName() : "no name";
	}
	public String getIconeName() {
		return m_dataModel != null ? m_dataModel.getIconeName() : "default.png";
	}
	public Model getIconeModel() {
		return m_dataModel != null ? m_dataModel.getIconeModel() : null;
	}
	public float getEnergyCost() {
		return m_dataModel != null ? m_dataModel.getEnergyCost() : 0;
	}
	public float getBaseCooldown() {
		return m_dataModel != null ? m_dataModel.getBaseCooldown() : 0;
	}
	public int getMaxChargeCount() {
		return m_dataModel != null ? m_dataModel.getMaxChargeCount() : -1;
	}
	public float getCurrentCooldown() {
		return m_currentCooldown;
	}
	public int getRemainingCharge() {
		return m_remainingCharge;
	}
	public void use(EntityUnit u) {
		if(m_remainingCharge != 0 && m_currentCooldown==0.0f && u.spendEnergy(getEnergyCost())) {
			m_remainingCharge--;
			m_currentCooldown=getBaseCooldown();
			script(u);
		}
	}
	public void script(EntityUnit u) {
		u.addBuff(new Buff(null));
	}
	public float getCooldownPercent() {
		return getCurrentCooldown()/getBaseCooldown();
	}
	public void refreshCooldown(EntityUnit u) {
		if(m_currentCooldown>0.0f)
			m_currentCooldown-=Logic.Logic.DELTA_TIME;
		if(m_currentCooldown<0.0f)
			m_currentCooldown=0.0f;
	}
}
