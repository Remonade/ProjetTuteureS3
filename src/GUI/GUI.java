/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import static Graphic.GraphicMain.HEIGHT;
import static Graphic.GraphicMain.WIDTH;
import static Graphic.GraphicMain.window;
import Graphic.Model;
import Maths.Vector2f;
import Maths.Vector4f;
import Physic.PhysicMain;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class GUI {
	// ref
	protected String m_ref="";
	public String getRef() {
		return m_ref;
	}
	public void setRef(String ref) {
		m_ref=ref;
	}
	// taille et position
	protected Vector2f m_position;
	protected Vector2f m_size;
	// rendu
	protected boolean m_visible;
	// texte
	protected String m_labelText;
	protected float m_labelSize;
	protected Vector4f[] m_labelColor=new Vector4f[2];
	protected float m_padding=10f;
	// model
	protected String m_modelName;
	protected Model m_model;
	protected Vector4f[] m_modelColor=new Vector4f[2];
	// listener
	protected boolean m_hover=false;
	protected float m_hoverTime=0;
	
	public GUI() {
		// taille et position
		m_position=new Vector2f();
		m_size=new Vector2f();
		// rendu
		m_visible=true;
		// texte
		m_labelText="";
		m_labelColor[0]=new Vector4f(0,1,0,1);
		m_labelSize=1;
		// model
		m_modelName="";
		m_modelColor[0]=new Vector4f(0.5f,0.5f,0.5f,1);
	}
	public Vector2f getPos() {
		Vector2f out=new Vector2f();
		if(m_position.x<=1)
			out.x=m_position.x*WIDTH;
		else out.x=m_position.x;
		if(m_position.y<=1)
			out.y=m_position.y*HEIGHT;
		else out.y=m_position.y;
		return out;
	}
	public Vector2f getPosLabel() {
		return m_position.subtract(new Vector2f(getSize().x,0));
	}
	public Vector2f getPosLabel(float direction) {
		return m_position.add(new Vector2f(direction*m_size.x/2,-m_size.y/2));
	}
	public void setPos(Vector2f pos) {
		setPos(pos.x,pos.y);
	}
	public void setPos(float x,float y) {
		m_position=new Vector2f(x,y);
	}
	public Vector2f getSize() {
		Vector2f out=new Vector2f();
		if(m_size.x<=1)
			out.x=m_size.x*WIDTH;
		else out.x=m_size.x;
		if(m_size.y<=1)
			out.y=m_size.y*HEIGHT;
		else out.y=m_size.y;
		return out;
	}
	public void setSize(Vector2f size) {
		setSize(size.x,size.y);
	}
	public void setSize(float x,float y) {
		m_size=new Vector2f(x,y);
	}
	public void setVisible(boolean value) {
		m_visible=value;
	}
	public void setLabelSize(float size) {
		m_labelSize=size;
	}
	public float getLabelSize() {
		if(m_labelSize<=1)
			return m_labelSize*getSize().y;
		return m_labelSize;
	}
	public void setLabelColor(int mode,float r,float g,float b,float a) {
		m_labelColor[mode]=new Vector4f(r,g,b,a);
	}
	public void setLabelColor(int mode,Vector4f color) {
		m_labelColor[mode]=color;
	}
	public Vector4f getLabelColor(int mode) {
		return m_labelColor[mode];
	}
	public void setLabelText(String text) {
		m_labelText=text;
	}
	public String getLabelText() {
		return m_labelText;
	}
	public void setModelColor(int mode,float r,float g,float b,float a) {
		m_modelColor[mode]=new Vector4f(r,g,b,a);
	}
	public void setModelColor(int mode,Vector4f color) {
		m_modelColor[mode]=color;
	}
	public Vector4f getModelColor(int mode) {
		return m_modelColor[mode];
	}
	public void tryClick() {
		if(m_hover)
			onClick();
	}
	public void tryHover() {
		isOver();
	}
	public void onClick() {
	}
	public void onHover() {
	}
	public boolean isOver() {
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		
		glfwGetCursorPos(window, b1, b2);
		
        Vector2f pos1 = getPos();
        Vector2f pos2 = new Vector2f((float)b1.get(0),HEIGHT-(float)b2.get(0));
        Vector2f size1 = getSize();
		
		boolean over=PhysicMain.inSquare(pos2, pos1, size1);
		
		if(over) {
			if(!m_hover)
				onHover();
			m_hoverTime+=Logic.Logic.DELTA_TIME;
		} else m_hoverTime=0.0f;
		
		m_hover=over;
		
		return over;
	}
	public boolean draw() {
		return m_visible;
	}
}
