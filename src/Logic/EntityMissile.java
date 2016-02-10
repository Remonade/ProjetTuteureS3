/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Logic.Data.EntityDataMissile;
import Graphic.Model;
import Graphic.ModelAnim;
import Logic.Data.Player;
import java.util.ArrayList;
import Maths.Vector2f;

public class EntityMissile extends EntityDynamic {
	public EntityMissile() {
		super();
		m_collide=false;
	}
	
	public void setDir(Vector2f dir) {
		setDir(dir.x,dir.y);
	}
	public void setDir(float x,float y) {
		m_direction.x=x;
		m_direction.y=y;
		m_direction=m_direction.normalize();
		Vector2f norm=m_direction;
		m_angle=(float) (Math.acos(norm.x)*180/Math.PI);
		if(norm.y<0)
			m_angle=180-m_angle;
		
		m_speed=m_direction.scale(getMaxSpeed());
	}
	public Vector2f getDir() {
		return m_direction;
	}
	public EntityDataMissile getMissileData() {
		return (EntityDataMissile)m_data;
	}
	@Override
	public void update() {
		m_pos=m_pos.add(m_speed);
		m_time+=0.05f;
		if(m_time>10) {
			Realm r=Realm.getActiveRealm();
			if(r!=null)
				r.removeEntity(this);
		}
	}
	public void explode(Entity target) {
		if(target!=m_owner) {
			if(target instanceof EntityUnit && getTeam()!=((EntityUnit)target).getTeam()) {
				boolean kill=((EntityUnit)target).damage(getDamage());
				if(kill && m_owner instanceof EntityUnit) {
					Player p=((EntityUnit)m_owner).getOwner();
					if(p!=null)
						p.addXP((int)((EntityUnit)target).getMaxHealth());
				}
			}
			Realm r=Realm.getActiveRealm();
			if(r!=null)
				r.removeEntity(this);
		}
	}

	public float getTime() {
		return m_time;
	}

	public void setTime(float time) {
		this.m_time = time;
	}
	public float getMaxSpeed() {
		if(m_data!=null)
			return getMissileData().getMaxSpeed();
		return 0.05f;
	}
	public int getDamage() {
		if(m_data!=null)
			return getMissileData().getDamage();
		return 1;
	}
	public void setOwner(Entity owner) {
		m_owner=owner;
	}
	@Override
    public void draw() {
		Model model=getModel();
		if(model instanceof ModelAnim)
			((ModelAnim)model).draw(m_pos,getSize(),getColor(),m_angle,Logic.CURRENT_TIME,"NAN");
		else
			model.draw(m_pos,getSize(),getColor(),m_angle);
	}
	
	private Vector2f m_direction=new Vector2f();
	private float m_time;
	private Entity m_owner;
	private float m_angle;
	
	
	
	
	private static ArrayList<EntityMissile> all=new ArrayList();
	public static ArrayList getAll() {
		return all;
	}
	public static EntityMissile create() {
		EntityMissile temp=new EntityMissile();
		temp.setDir(new Vector2f());
		temp.setTime(0.0f);
		//all.add(temp);
		//EntityDynamic.getAll().add(temp);
		//Entity.getAll().add(temp);
		return temp;
	}
	public static void remove(Entity e) {
		EntityDynamic.remove(e);
		all.remove(e);
	}
}
