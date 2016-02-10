/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Logic.Data.EntityData;
import java.util.ArrayList;
import Maths.Vector2f;

public class EntityDynamic extends Entity {
	public EntityDynamic() {
		super();
		m_speed=new Vector2f();
	}
	@Override
	public void setData(EntityData data) {
		super.setData(data);
	}
	public void setTeam(int team) {
		m_team=team;
	}
	public int getTeam() {
		return m_team;
	}
	public Vector2f getSpeed() {
		return m_speed;
	}
	public void setSpeed(Vector2f speed) {
		setSpeed(speed.x,speed.y);
	}
	public void setSpeed(float x,float y) {
		m_speed.x=x;
		m_speed.y=y;
		
		if(m_speed.y<-0.1f)
			m_speed.y=-0.1f;
	}
	public void addSpeed(Vector2f speed) {
		addSpeed(speed.x,speed.y);
	}
	public void addSpeed(float x,float y) {
		m_speed.x+=x;
		m_speed.y+=y;
		
		if(m_speed.y<-0.1f)
			m_speed.y=-0.1f;
	}
	public void update() {
	}
	@Override
    public void draw() {
		super.draw();
	}
	public boolean isActive() {
		return m_active;
	}
	public void setActive(boolean value) {
		m_active=value;
	}
	private int m_team=0;
	protected String m_name="Entity";
	protected Vector2f m_speed;
	protected float maxspeed=0.5f;
	protected boolean m_active=true;
	
	private static ArrayList<EntityDynamic> all=new ArrayList();
	
	public static ArrayList getAll() {
		return all;
	}
	public static EntityDynamic create() {
		EntityDynamic temp=new EntityDynamic();
		add(temp);
		return temp;
	}
	public static void add(EntityDynamic e) {
		Entity.add(e);
		all.add(e);
	}
	public static void remove(Entity e) {
		Entity.remove(e);
		all.remove((EntityDynamic)e);
	}
}