/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.IA;

import static Graphic.GraphicMain.camera;
import Logic.Entity;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;
import static Physic.Vectorial.entityCollide;
import java.util.ArrayList;
import java.util.HashMap;

public class IA {
	
	protected int m_actionCount=0;
	protected double m_time=0;
	protected int m_actionLeft=0;
	protected int m_maxAPM; // Attack Per Minute
	
	protected int m_behavior;
	
	public IA(int maxAPM) {
		m_maxAPM=maxAPM;
	}
	
	public IA() {
		m_maxAPM=60;
	}
	public IA copy() {
		return new IA();
	}
	public IA copy(int APM) {
		return new IA(APM);
	}
	public void execute(EntityUnit u) {
		if(m_maxAPM>0) {
			m_time=Logic.Logic.CURRENT_TIME;
			m_actionLeft=(int)(m_maxAPM*m_time/60)-m_actionCount;
		} else m_actionLeft=100;
		EntityUnit target=getTarget(u);
		behavior(u,target);
		move(u,target);
		rotate(u,target);
		if(m_actionLeft>0){
			attack(u,target);
		}
	}
	public void behavior(EntityUnit u, EntityUnit target) {
		if(u.getPercentShield()<0.2f || u.getPercentEnergy()<0.3f)
			m_behavior=2; // flee
		else if(target==null)
			m_behavior=0; // do nothing
		else
				m_behavior=1; // fight
	}
	public void rotate(EntityUnit u, EntityUnit target) {
		if(m_behavior==0) {
			if(u.getCustomValue()==1) 
				u.setLookRight(true);
			else if(u.getCustomValue()==-1)
				u.setLookRight(false);
		} else if(m_behavior==1) {
			if(target.getPos().x>u.getPos().x)
				u.setLookRight(true);
			else
				u.setLookRight(false);
		}
	}
	public void move(EntityUnit u, EntityUnit target) {
		u.resetInput();
		if(m_behavior==0) {
			if(u.getCustomValue()==1) {
				u.setInput(EntityUnit.INPUT_RIGHT,true);
				if(Math.random()<0.05)
					u.setCustomValue(0);
			} else if(u.getCustomValue()==-1) {
				u.setInput(EntityUnit.INPUT_LEFT,true);
				if(Math.random()<0.05)
					u.setCustomValue(0);
			} else {
				if(Math.random()<0.05)
					u.setCustomValue(-1);
				else if(Math.random()<0.05)
					u.setCustomValue(1);
			}
		} else if(m_behavior==1) {
			Vector2f pos=target.getPos();
			Vector2f size=target.getSize();
			if(pos.y > u.getPos().y+size.y)
				u.setInput(EntityUnit.INPUT_JUMP,true);
			if(Math.abs(pos.x-u.getPos().x)>2f) {
				if(target.getPos().x>u.getPos().x) {
					u.setInput(EntityUnit.INPUT_RIGHT,true);
				} else {
					u.setInput(EntityUnit.INPUT_LEFT,true);
				}
			} else if(Math.abs(pos.x-u.getPos().x)<1f) {
				if(target.getPos().x>u.getPos().x) {
					u.setInput(EntityUnit.INPUT_LEFT,true);
				} else {
					u.setInput(EntityUnit.INPUT_RIGHT,true);
				}
			}
		} else if(m_behavior==2) {
			if(u.getCustomValue()==-1) {
				if(u.getContact(Entity.CONTACT_RIGHT)) {
					u.setInput(EntityUnit.INPUT_JUMP,true);
					u.setInput(EntityUnit.INPUT_LEFT,true);
					u.setCustomValue(1);
				} else u.setInput(EntityUnit.INPUT_RIGHT,true);
			} else {
				if(u.getContact(Entity.CONTACT_LEFT)) {
					u.setInput(EntityUnit.INPUT_JUMP,true);
					u.setInput(EntityUnit.INPUT_RIGHT,true);
					u.setCustomValue(-1);
				} else u.setInput(EntityUnit.INPUT_LEFT,true);
			}
		}
	}
	
	public void attack(EntityUnit u, EntityUnit target) {
		if(m_behavior==1 || (m_behavior==2 && target!=null)) {
			Vector2f pos=target.getPos();
			Vector2f size=target.getSize();
			if(lineOfSight(u,target)) {
				if(u.shoot(u.getPos().subtract(pos).negate()))
					m_actionCount++;
			}
		}
	}
	public static EntityUnit getTarget(EntityUnit u) {
		Realm r=Logic.Realm.getActiveRealm();
		for(EntityUnit unit:r.getUnits())
			if(u.getTeam()!=unit.getTeam() && lineOfSight(u,unit))
				return unit;
		return null;
	}
	public static boolean isInScreen(EntityUnit u) {
		return camera.isInScreen(u.getPos());
	}
	public static EntityUnit getNearestEnemy(EntityUnit u) {
		Realm r=Logic.Realm.getActiveRealm();
		EntityUnit closest=null;
		float distance=0;
		for(EntityUnit unit:r.getUnits())
			if(u.getTeam()!=unit.getTeam()) {
				float tempDist=u.getPos().subtract(unit.getPos()).length();
				if(closest==null || distance>tempDist) {
					closest=unit;
					distance=tempDist;
				}
			}
		return closest;
	}
	public static boolean lineOfSight(EntityUnit u1, EntityUnit u2) {
		if(!isInScreen(u1) || !isInScreen(u2))
			return false;
		
		Vector2f LOS=u2.getPos().subtract(u1.getPos());
		
		EntityUnit u=new EntityUnit();
		u.setPos(u1.getPos());
		u.setSpeed(LOS);
		
		Vector2f pos1=u.getPos();
		Vector2f dest=pos1.add(u.getSpeed());
		
		ArrayList<Entity> all=(ArrayList<Entity>) Realm.getActiveRealm().getEntities().clone();
		
		for(Entity e:all)
			if(u1!=e && e.getCollide()) // si pas lui même
				dest=entityCollide(u,dest,e);
		
		Vector2f speed=dest.subtract(pos1);
		
		if(speed.length()+0.01f<LOS.length())
			return false;
		
		return true;
	}
	
	
	/*******************************************************************/
	
	private static HashMap<String,IA> IA_TEMPLATES=new HashMap<>();
	
	public static void initIA_TEMPLATES() {
		IA_TEMPLATES.put("default",new IA());
		IA_TEMPLATES.put("roamer",new IARoamer());
		IA_TEMPLATES.put("tuto",new IATutorial());
	}
	public static IA getIA_TEMPLATE(String key) {
		return IA_TEMPLATES.get(key).copy();
	}
	public static IA getIA_TEMPLATE(String key, int APM) {
		return IA_TEMPLATES.get(key).copy(APM);
	}
}
