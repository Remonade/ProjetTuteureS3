/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import java.util.ArrayList;
import Maths.Vector2f;

public class EntityMissile extends EntityDynamic {
	public EntityMissile() {
		super();
		m_collide=false;
	}
	public void setDir(Vector2f dir) {
		m_direction.x=dir.x;
		m_direction.y=dir.y;
	}
	public void setDir(float x,float y) {
		m_direction.x=x;
		m_direction.y=y;
	}
	public Vector2f getDir() {
		return m_direction;
	}

	@Override
	public void update() {
		m_speed=m_direction.divide(100f);
		m_pos=m_pos.add(m_speed);
		m_time+=0.05f;
		if(m_time>10) {
			EntityMissile.getAll().remove(EntityMissile.getAll().indexOf(this));
			EntityDynamic.getAll().remove(EntityDynamic.getAll().indexOf(this));
			Entity.getAll().remove(Entity.getAll().indexOf(this));
		}
	}
	public void explode(Entity target) {
		if(target!=m_owner) {
			if(target instanceof EntityDynamic || target instanceof EntityEnemy) {
				((EntityDynamic)target).damage(m_damage);
			}
			remove(this);
		}
	}

	public float getTime() {
		return m_time;
	}

	public void setTime(float time) {
		this.m_time = time;
	}

	public int getDamage() {
		return m_damage;
	}
	public void setOwner(Entity owner) {
		m_owner=owner;
	}
	public void setDamage(int damage) {
		this.m_damage = damage;
	}
	@Override
    public void draw() {
		if(getModel()!=null)
			getModel().draw(m_pos,getSize());
	}
	
	private Vector2f m_direction=new Vector2f();
	private float m_time;
	private int m_damage;
	private Entity m_owner;
	private static ArrayList<EntityMissile> all=new ArrayList();
	
	public static ArrayList getAll() {
		return all;
	}
	public static EntityMissile create() {
		EntityMissile temp=new EntityMissile();
		temp.setDir(new Vector2f());
		temp.setTime(0.0f);
		temp.setDamage(1);
		all.add(temp);
		EntityDynamic.getAll().add(temp);
		Entity.getAll().add(temp);
		return temp;
	}
	public static void remove(Entity e) {
		EntityDynamic.remove(e);
		all.remove(e);
	}
}
