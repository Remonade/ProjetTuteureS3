package Logic;

import Logic.Data.EntityData;
import Graphic.Model;
import Graphic.ModelAnim;
import Graphic.ModelRepeat;
import java.util.ArrayList;
import Maths.Vector2f;
import Maths.Vector4f;

public class Entity {
	public Entity() {
		m_contact = new boolean[4];
		for(boolean i:m_contact)
			i=false;
		this.m_pos=new Vector2f(0,0);
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
	public Vector4f getColor() {
		if(m_data==null)
				return null;
		return m_data.getColor();
	}
	public String getSound(String name) {
		if(m_data==null)
				return "";
		return m_data.getSound(name);
	}
	public String getAnim() {
		if(this instanceof EntityUnit)
			return ((EntityUnit)this).getAnim();
		return "NAN";
	}
	public double getAnimTime() {
		return Logic.CURRENT_TIME;
	}
	public void draw() {
		Model model=getModel();
		if(model!=null) {
			if(model instanceof ModelAnim) {
				((ModelAnim)model).draw(m_pos,getSize(),getColor(),getAnimTime(),getAnim());
			} else if(getModel() instanceof ModelRepeat) {
				((ModelRepeat)model).draw(m_pos,getSize(),getColor());
			} else
				model.draw(m_pos,getSize(),getColor());
		}
		String contact="";
		/*if(m_contact[CONTACT_UP])
			GraphicMain.drawString("u", m_pos.add(new Vector2f(0,getSize().y)), 0.01f, getColor());
		if(m_contact[CONTACT_DOWN])
			GraphicMain.drawString("d", m_pos.add(new Vector2f(0,-getSize().y)), 0.01f, getColor());
		if(m_contact[CONTACT_RIGHT])
			GraphicMain.drawString("r", m_pos.add(new Vector2f(getSize().x,0)), 0.01f, getColor());
		if(m_contact[CONTACT_LEFT])
			GraphicMain.drawString("l", m_pos.add(new Vector2f(-getSize().x,0)), 0.01f, getColor());*/
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
	protected boolean m_contact[];
	private static ArrayList<Entity> all=new ArrayList();
	public static final int CONTACT_UP=0;
	public static final int CONTACT_DOWN=1;
	public static final int CONTACT_LEFT=2;
	public static final int CONTACT_RIGHT=3;
	
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
