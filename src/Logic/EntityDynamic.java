/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Graphic.GraphicMain;
import Physic.PhysicMain;
import java.util.ArrayList;
import Maths.Vector2f;
import Maths.Vector3f;

public class EntityDynamic extends Entity {
	public EntityDynamic() {
		super();
		m_contact = new boolean[4];
		for(boolean i:m_contact)
			i=false;
		m_speed=new Vector2f();
		m_team=1;
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
		m_speed = speed;
	}
	public void setSpeed(float x,float y) {
		m_speed.x=x;
		m_speed.y=y;
	}
	public void addSpeed(Vector2f speed) {
		m_speed.add(speed);
		if(Math.abs(m_speed.x)>maxspeed)
			m_speed.x=maxspeed*Math.signum(m_speed.x)*0.25f;
		if(Math.abs(m_speed.y)>maxspeed)
			m_speed.y=maxspeed*Math.signum(m_speed.y);
	}
	public void addSpeed(float x,float y) {
		m_speed.x+=x;
		m_speed.y+=y;
		if(Math.abs(m_speed.x)>maxspeed)
			m_speed.x=maxspeed*Math.signum(m_speed.x)*0.25f;
		if(Math.abs(m_speed.y)>maxspeed)
			m_speed.y=maxspeed*Math.signum(m_speed.y);
	}
	public boolean getContact(int contact) {
		if(contact>-1 && contact<4)
			return m_contact[contact];
		return false;
	}
	public void setContact(int contact, boolean value) {
		if(contact>-1 && contact<4)
			m_contact[contact]=value;
	}
	public void update() {
		addSpeed(0f,PhysicMain.GRAVITY);
	}
	@Override
    public void draw() {
		super.draw();
		GraphicMain.drawString(""+m_team,m_pos,0.01f,new Vector3f(1,1,1));
	}
	public boolean isActive() {
		return m_active;
	}
	public void setActive(boolean value) {
		m_active=value;
	}
	private int m_team=1;
	protected String m_name="Entity";
	protected Vector2f m_speed;
	protected boolean m_contact[];
	protected float maxspeed=0.5f;
	protected boolean m_active=true;
	
	private static ArrayList<EntityDynamic> all=new ArrayList();
	public static final int CONTACT_UP=0;
	public static final int CONTACT_DOWN=1;
	public static final int CONTACT_LEFT=2;
	public static final int CONTACT_RIGHT=3;
	
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
		all.remove(e);
	}
}
