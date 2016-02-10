/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Buff;

import Logic.Data.EntityDataMissile;
import Logic.EntityUnit;
import Logic.IA.IA;
import Maths.Vector2f;

public class BuffAutoAttack extends Buff {
	protected float m_delay;
	protected int m_activation;
	protected EntityDataMissile m_type;
	
	
	public BuffAutoAttack(String name, float duration, int activation, EntityDataMissile data) {
		super(name,duration);
		m_activation=activation-1;
		m_delay=duration/m_activation;
		m_type=data;
		m_icone="icone/spread.png";
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

}