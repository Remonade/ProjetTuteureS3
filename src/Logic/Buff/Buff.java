

package Logic.Buff;

import Logic.EntityUnit;

public class Buff {
	protected String m_name="";
	protected String m_icone="icone/berzerker.png";
	protected int m_remainingStack=1;
	protected int m_maxStack=1;
	protected float m_remainingDuration=0;
	protected float m_maxDuration=0;
	
	protected boolean m_stackDuration=false;
	
	public Buff(String name, float duration) {
		m_name=name;
		m_remainingDuration=duration;
		m_maxDuration=duration;
	}
	public Buff(String name, float duration, boolean sd) {
		m_name=name;
		m_remainingDuration=duration;
		m_maxDuration=duration;
		m_stackDuration=sd;
	}
	public Buff(String name, float duration,int stack) {
		m_name=name;
		m_remainingDuration=duration;
		m_maxDuration=duration;
		m_remainingStack=stack;
		m_maxStack=stack;
	}
	public Buff(String name, float duration, boolean sd, int stack) {
		m_name=name;
		m_remainingDuration=duration;
		m_maxDuration=duration;
		m_stackDuration=sd;
		m_remainingStack=stack;
		m_maxStack=stack;
	}
	public String getName() {
		return m_name;
	}
	public String getIcone() {
		return m_icone;
	}
	public float getMaxDuration() {
		return m_maxDuration;
	}
	public float getRemainingDuration() {
		return m_remainingDuration;
	}
	public int getMaxStack() {
		return m_maxStack;
	}
	public int getRemainingStack() {
		return m_remainingStack;
	}
	public void onStart(EntityUnit u) {
		
	}
	public void onUpdate(EntityUnit u) {
		
	}
	public void onExpire(EntityUnit u) {
		
	}
	public boolean update(EntityUnit u) {
		if(m_remainingDuration>0.0f)
			m_remainingDuration-=Logic.Logic.DELTA_TIME;
		if(m_remainingDuration<0.0f) {
			m_remainingDuration=0.0f;
			if(m_stackDuration) {
				m_remainingStack--;
				if(m_remainingStack==0) {
					return true;
				} else
					m_remainingDuration=m_maxDuration;
			} else {
				return true;
			}
		} else onUpdate(u);
		return false;
	}
}
