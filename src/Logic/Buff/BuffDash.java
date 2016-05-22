/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Buff;

import Logic.Data.DataBuff;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;
import Physic.PhysicMain;
import java.util.ArrayList;

public class BuffDash extends Buff {
	protected float m_distance=0;
	protected int m_direction=-1;
	protected Vector2f m_speed=new Vector2f(0,0);
	
	protected ArrayList<EntityUnit> m_touch=new ArrayList<>();
	
	public BuffDash(DataBuff dataModel) {
		super(dataModel);
	}
	
	@Override
	public void onStart(EntityUnit u) {
		if(u.getLookRight())
			m_direction=1;
		m_touch.add(u);
	}
	
	@Override
	public void onUpdate(EntityUnit u) {
			
		float distance=(float)(getRange()*(Logic.Logic.DELTA_TIME/getMaxDuration())*m_direction);
		m_speed.x=distance;
		m_distance+=Math.abs(distance);
		
		if(m_distance>getRange()) {
			m_remainingDuration=-1;
			m_speed.x=0;
		}
		
		ArrayList<EntityUnit> all=(ArrayList<EntityUnit>)Realm.getActiveRealm().getUnits().clone();
		
		ArrayList<EntityUnit> touched=new ArrayList<>();
		
		for(EntityUnit t:m_touch) {
			for(EntityUnit e:all) {
				if(e!=t && e!=u && !m_touch.contains(e)) {
					if(PhysicMain.contact(t,e))
						touched.add(e);
				}
			}
		}
		
		m_touch.addAll(touched);
		
		for(EntityUnit t:m_touch) {
				t.setSpeed(m_speed.x,0);
		}
	}
	
	@Override
	public void onExpire(EntityUnit u) {
		for(EntityUnit t:m_touch) {
				if(t!=u) {
					t.damage(50);
			}
		}
	}
	
	public float getRange() {
		return m_dataModel.getFloatProperty("range");
	}
}
