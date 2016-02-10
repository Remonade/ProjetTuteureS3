/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Buff;

import Logic.EntityUnit;

public class BuffHOT extends Buff {
	protected float m_delay;
	protected int m_activation;
	protected int m_amount;
	public BuffHOT(String name, float duration, int activation, float amount) {
		super(name,duration);
		m_activation=activation-1;
		m_delay=duration/m_activation;
		m_icone="icone/heal.png";
		m_amount=(int)amount/activation;
	}
	public BuffHOT(String name, float duration, boolean sd, int activation, float amount) {
		super(name,duration,sd);
		m_activation=activation-1;
		m_delay=duration/m_activation;
		m_icone="icone/heal.png";
		m_amount=(int)amount/activation;
	}
	@Override
	public void onStart(EntityUnit u) {
		
	}
	@Override
	public void onUpdate(EntityUnit u) {
		if(m_activation>0 && m_delay*m_activation>=m_remainingDuration) {
			m_activation--;
			u.heal(m_amount);
		}
	}
	@Override
	public void onExpire(EntityUnit u) {
		u.heal(m_amount);
	}

}
