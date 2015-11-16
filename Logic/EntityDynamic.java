/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import math.Vector2f;

public class EntityDynamic extends Entity {
	public EntityDynamic() {
		super();
		m_contact = new boolean[4];
		for(boolean i:m_contact)
			i=false;
		m_speed=new Vector2f(0,0);
	}
	public EntityDynamic(float h,float w) {
		super(h,w);
		m_contact = new boolean[4];
		for(boolean i:m_contact)
			i=false;
		m_speed=new Vector2f(0,0);
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
	}
	public void addSpeed(float x,float y) {
		m_speed.x+=x;
		m_speed.y+=y;
	}
	@Override
	public void delete() {
		super.delete();
		m_speed=null;
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
	protected Vector2f m_speed;
	protected boolean m_contact[];
	public static final int CONTACT_UP=0;
	public static final int CONTACT_DOWN=1;
	public static final int CONTACT_LEFT=2;
	public static final int CONTACT_RIGHT=3;
}
