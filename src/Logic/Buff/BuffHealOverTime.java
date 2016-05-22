
package Logic.Buff;

import Logic.Data.DataBuff;
import Logic.Data.EntityDataParticle;
import Logic.EntityParticle;
import Logic.EntityUnit;
import static Logic.Realm.getActiveRealm;

public class BuffHealOverTime extends Buff {
	private int m_remainingActivation;
	public BuffHealOverTime(DataBuff dataModel) {
		super(dataModel);
		m_remainingActivation=getMaxActivationCount()-1;
			System.out.println("heal start:"
			+"\tdelay: "+getActivationDelay());
	}
	@Override
	public void onStart(EntityUnit u) {
		
	}
	@Override
	public void onUpdate(EntityUnit u) {
		EntityParticle temp;
			temp=new EntityParticle();
			temp.setData(EntityDataParticle.get("EDPheal"));
			temp.setPos(u.getPos().x,u.getPos().y);
			getActiveRealm().addEntity(temp);
				
		if(m_remainingActivation>0 && getActivationDelay()*m_remainingActivation >= getRemainingDuration()) {
			System.out.println("heal tick:"
			+"\tneeded: "+(getActivationDelay()*m_remainingActivation)
			+"\tremaining: "+getRemainingDuration());
			m_remainingActivation--;
			u.heal(getAmount());
		}
	}
	@Override
	public void onExpire(EntityUnit u) {
			System.out.println("heal tick:"
			+"\tneeded: "+(getActivationDelay()*m_remainingActivation)
			+"\tremaining: "+getRemainingDuration());
		u.heal(getAmount());
	}
	
	public int getAmount() {
		return m_dataModel.getIntegerProperty("healAmount")/(getMaxActivationCount());
	}
}
