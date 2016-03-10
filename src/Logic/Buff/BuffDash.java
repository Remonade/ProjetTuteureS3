/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Buff;

import Logic.Data.EntityDataParticle;
import Logic.Entity;
import Logic.EntityParticle;
import Logic.EntityUnit;
import Logic.Realm;
import static Logic.Realm.getActiveRealm;
import Maths.Vector2f;
import Physic.PhysicMain;
import java.util.ArrayList;

public class BuffDash extends Buff {
	protected float m_range;
	protected float m_distance=0;
	protected int m_direction=-1;
	protected Vector2f m_speed=new Vector2f(0,0);
	
	protected ArrayList<Entity> m_touch=new ArrayList<>();
	
	public BuffDash(String name, float duration, float range) {
		super(name,duration);
		m_range=range;
		m_icone="icone/dash.png";
	}
	
	@Override
	public void onStart(EntityUnit u) {
		if(u.getLookRight())
			m_direction=1;
		m_touch.add(u);
	}
	
	@Override
	public void onUpdate(EntityUnit u) {
		EntityParticle temp;
			temp=new EntityParticle(/*0.35f,((float)Math.random()*50)-25+180*/);
			temp.setData(EntityDataParticle.get("EDPheal"));
			temp.setPos(u.getPos().x,u.getPos().y);
			getActiveRealm().addEntity(temp);
			
		float distance=(float)(m_range*(Logic.Logic.DELTA_TIME/m_maxDuration)*m_direction);
		m_speed.x=distance;
		m_distance+=Math.abs(distance);
		
		if(m_distance>m_range)
			m_remainingDuration=-1;
		
		ArrayList<Entity> all=(ArrayList<Entity>)Realm.getActiveRealm().getUnits().clone();
		//all.addAll(Realm.getActiveRealm().getEntities());
		
		ArrayList<Entity> touched=new ArrayList<>();
		
		for(Entity t:m_touch) {
			for(Entity e:all) {
				if(e!=t && e!=u && !m_touch.contains(e)) {
					if(PhysicMain.contact(t,e))
						touched.add(e);
				}
			}
		}
		
		m_touch.addAll(touched);
		
		for(Entity t:m_touch) {
			if(t instanceof EntityUnit) {
				EntityUnit unit=(EntityUnit)t;
				unit.setSpeed(m_speed.x,unit.getSpeed().y);
				//if(unit!=u && !unit.isStun()) {
				//}
			}
		}
		
	}
	
	@Override
	public void onExpire(EntityUnit u) {
		for(Entity t:m_touch) {
			if(t instanceof EntityUnit) {
				EntityUnit unit=(EntityUnit)t;
				if(unit!=u) {
					unit.damage(50);
				}
			}
		}
	}

}
