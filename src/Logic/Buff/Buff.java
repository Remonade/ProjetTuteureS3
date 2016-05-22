

package Logic.Buff;

import Logic.Data.DataBuff;
import Logic.EntityUnit;

public class Buff {
	protected DataBuff m_dataModel;
	protected int m_remainingStack=1;
	protected float m_remainingDuration=0;
	
	public Buff(DataBuff dataModel) {
		m_dataModel = dataModel;
		m_remainingDuration = getMaxDuration();
	}
	public String getName() {
		return m_dataModel != null ? m_dataModel.getName() : "no name";
	}
	public String getIcone() {
		return m_dataModel != null ? m_dataModel.getIcone() : "default.png";
	}
	public float getMaxDuration() {
		return m_dataModel != null ? m_dataModel.getMaxDuration() : 0;
	}
	public float getRemainingDuration() {
		return m_remainingDuration;
	}
	public float getElapsedDuration() {
		return getMaxDuration() - getRemainingDuration();
	}
	public int getMaxStackCount() {
		return m_dataModel != null ? m_dataModel.getMaxStackCount() : 0;
	}
	public int getRemainingStack() {
		return m_remainingStack;
	}
	public float getActivationDelay() {
		return m_dataModel.getActivationDelay();
	}
	public int getMaxActivationCount() {
		return m_dataModel.getMaxActivationCount();
	}
	public boolean getStackDuration() {
		return m_dataModel.isStackDuration();
	}
	public void onStart(EntityUnit u) {
		
	}
	public void onUpdate(EntityUnit u) {
		
	}
	public void onExpire(EntityUnit u) {
		
	}
	public boolean update(EntityUnit u) {
		if(m_remainingDuration>0.0f)
			m_remainingDuration -= Logic.Logic.DELTA_TIME;
		if(m_remainingDuration<0.0f) {
			m_remainingDuration=0.0f;
			if(getStackDuration()) {
				m_remainingStack--;
				if(m_remainingStack == 0) {
					return true;
				} else
					m_remainingDuration = getMaxDuration();
			} else {
				return true;
			}
		} else onUpdate(u);
		return false;
	}
}
