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
	public void shoot(Vector2f target) {
		EntityMissile mis=EntityMissile.create();
		mis.setDir(target.subtract(m_pos).normalize());
		mis.setPos(m_pos.add(mis.getDir().divide(50)));
		mis.setData(EntityDataDynamic.get("missile"));
		mis.setOwner(this);
	}
	public void jump() {
		if(m_contact[CONTACT_DOWN])
			setSpeed(0,0.075f);
	}
	public void damage(int damage) {
		m_hp-=damage;
	}
	public int getHP() {
		return m_hp;
	}
	public int getMaxHP() {
		if(m_data==null)
			return 0;
		return ((EntityDataDynamic)m_data).getMaxHP();
	}
	public void update() {
		addSpeed(0f,PhysicMain.GRAVITY);
		if(m_hp<getMaxHP())
			m_hp++;
	}
	@Override
    public void draw() {
		super.draw();
		//GraphicMain.drawString(m_name,m_pos.add(m_size),0.01f,new Vector3f(1,1,1));
		GraphicMain.drawString(getHP()+"/"+getMaxHP(),m_pos,0.01f,new Vector3f(1,1,1));
	}
	public boolean isActive() {
		return m_active;
	}
	public void setActive(boolean value) {
		m_active=value;
	}
	protected String m_name="Entity";
	protected int m_hp=100;
	protected Vector2f m_speed;
	protected boolean m_contact[];
	protected float maxspeed=0.1f;
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
		all.add(temp);
		Entity.getAll().add(temp);
		return temp;
	}
}
