/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.IA;

import Logic.Entity;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;
import static Physic.Vectorial.entityCollide;
import java.util.ArrayList;

public class IA {
	
	protected int m_actionCount=0;
	protected double m_time=0;
	protected int m_actionLeft=0;
	protected int m_maxAPM; // Attack Per Minute
	
	public IA(int maxAPM) {
		m_maxAPM=maxAPM;
	}
	
	public void execute(EntityUnit u) {
		if(m_maxAPM>0) {
			m_time=Logic.Logic.CURRENT_TIME;
			m_actionLeft=(int)(m_maxAPM*m_time/60)-m_actionCount;
		} else m_actionLeft=100;
		EntityUnit target=getTarget(u);
		move(u,target);
		rotate(u,target);
		if(m_actionLeft>0){
			attack(u,target);
		}
	}
	public void rotate(EntityUnit u, EntityUnit target) {
		if(target!=null) {
			if(target.getPos().x>u.getPos().x)
				u.setLookRight(true);
			else
				u.setLookRight(false);
		}
	}
	public void move(EntityUnit u, EntityUnit target) {
		if(target!=null) {
			Vector2f pos=target.getPos();
			if(pos.y > u.getPos().y+pos.y)
				u.jump();
			if(Math.abs(pos.x-u.getPos().x)>2f) {
				if(target.getPos().x>u.getPos().x) {
					u.addSpeed(0.005f,0);
				} else {
					u.addSpeed(-0.005f,0);
				}
			}
		}
	}
	
	public void attack(EntityUnit u, EntityUnit target) {
		if(target!=null) {
			Vector2f pos=target.getPos();
			Vector2f size=target.getSize();
			if(lineOfSight(u,target))
				if(u.shoot(u.getPos().subtract(pos).negate()))
					m_actionCount++;
		}
	}
	public static EntityUnit getTarget(EntityUnit u) {
		Realm r=Logic.Realm.getActiveRealm();
		for(EntityUnit unit:r.getUnits())
			if(u.getTeam()!=unit.getTeam())
				return unit;
		return null;
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
		Vector2f pos1=u1.getPos();
		Vector2f pos2=u2.getPos();
		
		EntityUnit temp=new EntityUnit();
		temp.setPos(pos1);
		
		Vector2f vect=pos1.subtract(pos2);
		Vector2f dest=pos1.subtract(pos2);
		
		ArrayList<Entity> all=(ArrayList<Entity>) Realm.getActiveRealm().getEntities().clone();
		all.addAll(Realm.getActiveRealm().getUnits());
		
		for(Entity e:all)
			if(u1!=e && u2!=e && e.getCollide())
				dest=entityCollide(temp,dest,e);
		
		if(vect.length()<dest.length()+0.01f)
			return true;
		
		return false;
	}
	
}
