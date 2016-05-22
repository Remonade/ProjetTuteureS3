/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Spell;

import static Graphic.GraphicMain.getModel;
import Graphic.Model;
import Logic.Buff.Buff;
import Logic.EntityUnit;

public class Spell {
	protected float m_energyCost=0;
	protected float m_baseCooldown=0;
	protected float m_currentCooldown=0;
	protected String m_name="";
	protected Model m_icone=null;
	protected int m_charge=-1;
	
	public Spell(String name, float cost, float cooldown) {
		m_energyCost=cost;
		m_baseCooldown=cooldown;
		m_name=name;
		m_icone=getModel("Iberzerker");
	}
	
	public Spell(String name, float cost, float cooldown, int charge) {
		m_energyCost=cost;
		m_baseCooldown=cooldown;
		m_name=name;
		m_charge=charge;
		m_icone=getModel("Iberzerker");
	}
	public String getName() {
		return m_name;
	}
	public Model getIcone() {
		return m_icone;
	}
	public float getEnergyCost() {
		return m_energyCost;
	}
	public float getBaseCooldown() {
		return m_baseCooldown;
	}
	public float getCurrentCooldown() {
		return m_currentCooldown;
	}
	public int getCharge() {
		return m_charge;
	}
	public void use(EntityUnit u) {
		if(m_charge!=0 && m_currentCooldown==0.0f && u.spendEnergy(m_energyCost)) {
			m_charge--;
			m_currentCooldown=m_baseCooldown;
			script(u);
		}
	}
	public void script(EntityUnit u) {
		u.addBuff(new Buff(null));
	}
	public float getCooldownPercent() {
		return m_currentCooldown/m_baseCooldown;
	}
	public void refreshCooldown() {
		if(m_currentCooldown>0.0f)
			m_currentCooldown-=Logic.Logic.DELTA_TIME;
		if(m_currentCooldown<0.0f)
			m_currentCooldown=0.0f;
	}
}
