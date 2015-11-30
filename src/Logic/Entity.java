package Logic;

import Graphic.Model;
import Graphic.ModelAnim;
import Graphic.ModelRepeat;
import java.util.ArrayList;
import Maths.Vector2f;

public class Entity {
	public Entity() {
		this.m_pos=new Vector2f(0,0);
	}
	public void setData(EntityData data) {
		m_data=data;
	}
	public EntityData getData() {
		return m_data;
	}
	public Vector2f getPos() {
		return m_pos;
	}
	public void setPos(Vector2f pos) {
		m_pos.x = pos.x;
		m_pos.y = pos.y;
	}
	public void setPos(float x,float y) {
		m_pos.x=x;
		m_pos.y=y;
	}
	public Vector2f getSize() {
		if(m_data==null)
				return new Vector2f(0.0f,0.0f);
		return m_data.getSize();
	}
	public Model getModel() {
		if(m_data==null)
				return null;
		return m_data.getModel();
	}
	public void draw() {
		if(getModel()!=null) {
			if(getModel() instanceof ModelAnim)
				((ModelAnim)getModel()).draw(m_pos,getSize(),Logic.CURRENT_TIME%((ModelAnim)getModel()).getAnimDuration());
			else if(getModel() instanceof ModelRepeat)
				((ModelRepeat)getModel()).draw(m_pos,getSize());
			else
				getModel().draw(m_pos,getSize());
		}
	}
	public void setCollide(boolean value) {
		m_collide=value;
	}
	public boolean getCollide() {
		return m_collide;
	}
	public boolean isVisible() {
		return m_visible;
	}
	public void setVisible(boolean value) {
		m_visible=value;
	}
	protected EntityData m_data;
	protected Vector2f m_pos;
	protected boolean m_collide=true;
	protected boolean m_visible=true;
	private static ArrayList<Entity> all=new ArrayList();
	
	public static ArrayList getAll() {
		return all;
	}
	public static Entity create() {
		Entity temp=new Entity();
		add(temp);
		return temp;
	}
	public static void add(Entity e) {
		all.add(e);
	}
	public static void remove(Entity e) {
		all.remove(e);
	}
}
