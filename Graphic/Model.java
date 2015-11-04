package Graphic;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
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
	public void createTexture() {
		IntBuffer texture=IntBuffer.allocate(16);
		texture.put(0);texture.put(0);texture.put(0);texture.put(0);
		texture.put(0xFF);texture.put(0xFF);texture.put(0xFF);texture.put(0xFF);
		texture.put(0xFF);texture.put(0xFF);texture.put(0xFF);texture.put(0xFF);
		texture.put(0);texture.put(0);texture.put(0);texture.put(0);
		texture.flip();
		
		m_textureID=glGenTextures();
		glBindTexture(GL_TEXTURE_2D,m_textureID);
		glTexImage2D (
		GL_TEXTURE_2D, 	//Type : texture 2D
		0, 	//Mipmap : aucun
		4, 	//Couleurs : 4
		2, 	//Largeur : 2
		2, 	//Hauteur : 2
		0, 	//Largeur du bord : 0
		GL_RGBA, 	//Format : RGBA
		GL_UNSIGNED_BYTE, 	//Type des couleurs
		texture); 	//Addresse de l'image
		glBindTexture(GL_TEXTURE_2D,0);
	}
	public void draw(float x,float y) {
		
		//if(m_textures!=null && m_textureID!=0) // bind texture
		//	glBindTexture(GL_TEXTURE_2D,m_textureID);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(x,y,0);
		glBegin(GL_TRIANGLES);
			float[] colors=m_colors.array();
			//float[] textures=m_textures.array();
			float[] vertice=m_vertice.array();
			for(int i=0;i<m_verticeCount;i++) {
				sendColors(i,colors);
				//if(m_textures!=null && m_textureID!=0) // send texture
					//sendTextures(i,textures);
				sendVertice(i,vertice);
			}
		glEnd();
		
		//if(m_textures!=null && m_textureID!=0) // unbind texture
		//	glBindTexture(GL_TEXTURE_2D,0);
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
		glDeleteTextures(m_textureID);
	}
	protected FloatBuffer m_vertice=null;
	protected FloatBuffer m_textures=null;
	protected FloatBuffer m_colors=null;
	
	protected int m_textureID=0;
	protected int m_verticeCount;
	protected float m_size=1.0f;
}
