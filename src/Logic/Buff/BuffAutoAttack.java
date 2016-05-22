/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Buff;

import Logic.Data.DataBuff;
import Logic.Data.EntityDataMissile;
import Logic.EntityUnit;
import Logic.IA.IA;
import Maths.Vector2f;

public class BuffAutoAttack extends Buff {
	protected float m_delay;
	protected int m_activation;
	protected EntityDataMissile m_type;
	
	public BuffAutoAttack(DataBuff dataModel) {
		super(dataModel);
		m_activation=getActivationCount()-1;
		m_delay=m_dataModel.getMaxDuration()/m_activation;
		m_type=(EntityDataMissile)EntityDataMissile.get(getMissileTypeName());
	}
	@Override
	public void onStart(EntityUnit u) {
		
	}
	@Override
	public void onUpdate(EntityUnit u) {
		if(m_activation>0 && m_delay*m_activation>=m_remainingDuration) {
			m_activation--;
			EntityUnit target=IA.getNearestEnemy(u);
			if(target!=null) {
				Vector2f dir=target.getPos().subtract(u.getPos());
				u.shoot(dir);
			}
		}
	}
	@Override
	public void onExpire(EntityUnit u) {
		EntityUnit target=IA.getNearestEnemy(u);
		if(target!=null) {
			Vector2f dir=target.getPos().subtract(u.getPos());
			u.shoot(dir);
		}
	}
	private int getActivationCount() {
		return m_dataModel.getIntegerProperty("activationCount");
	}
	private String getMissileTypeName() {
		return m_dataModel.getStringProperty("missileTypeName");
	}
}