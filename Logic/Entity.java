package Logic;


import math.Vector2f;

public class Entity {
	public Entity() {
		this.m_pos=new Vector2f(0,0);
		this.m_size=new Vector2f(0,0);
	}
	public Entity(float h,float w) {
		this.m_pos=new Vector2f(0,0);
		this.m_size=new Vector2f(h,w);
	}
	public Vector2f getPos() {
		return m_pos;
	}
	public void setPos(Vector2f pos) {
		m_pos = pos;
	}
	public void setPos(float x,float y) {
		m_pos.x=x;
		m_pos.y=y;
	}
	public Vector2f getSize() {
		return m_size;
	}
	public void setSize(Vector2f size) {
		m_size = size;
	}
	public void setSize(float x,float y) {
		m_size.x=x;
		m_size.y=y;
	}
	public void delete() {
		m_pos=null;
		m_size=null;
	}
	public void draw() {
		
	}
	protected Vector2f m_pos;
	protected Vector2f m_size;
}
