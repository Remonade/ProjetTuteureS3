package Graphic;

import java.nio.FloatBuffer;
import math.Vector2f;
import static org.lwjgl.opengl.GL11.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Model {
	public Model() {
		System.out.println("New Model instance");
		createTexture();
	}
	final public void createTexture() {
		m_texture=new Texture("");
	}
	public void draw(Vector2f pos,Vector2f size) {
		startRender(pos,size);
		render();
		endRender();
	}
	public void startRender(Vector2f pos,Vector2f size) {
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(pos.x,pos.y,0);
		glScalef(size.x,size.y,0);
		if(m_texture!=null)
			m_texture.bind(); // bind texture
	}
	public void render() {
		glBegin(GL_TRIANGLES);
			float[] colors=m_colors.array();
			float[] textures=m_textures.array();
			float[] vertice=m_vertice.array();
			for(int i=0;i<m_verticeCount;i++) {
				if(m_texture!=null) // send texture
					sendTextures(i,textures);
				sendColors(i,colors);
				sendVertice(i,vertice);
			}
		glEnd();
	}
	public void endRender() {
		if(m_texture!=null) // unbind texture
			m_texture.unbind();
	}
	public void sendColors(int offset,float[]data) {
		glColor4f(data[offset*3],data[offset*3+1],data[offset*3+2],0.5f);
	}
	public void sendTextures(int offset,float[]data) {
		glTexCoord2f(data[offset*2],data[offset*2+1]);
	}
	public void sendVertice(int offset,float[]data) {
		glVertex3f(data[offset*2],data[offset*2+1],0.0f);
	}
	public void destroy() {
		if(m_texture!=null)
			m_texture.destroy();
	}
	public FloatBuffer m_vertice=null;
	public FloatBuffer m_textures=null;
	public FloatBuffer m_colors=null;
	
	public Texture m_texture=null;
	public int m_verticeCount;
	public float m_size=1.0f;
}
