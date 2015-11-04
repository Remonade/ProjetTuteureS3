/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.*;

public class Texture {
	public Texture(String path) {
		IntBuffer texture=IntBuffer.allocate(64);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.put(0x88);texture.put(0x88);texture.put(0x88);texture.put(0x88);
		texture.flip();
		
		m_ID=glGenTextures();
		glBindTexture(GL_TEXTURE_2D,m_ID);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D (
		GL_TEXTURE_2D, 	//Type : texture 2D
		0, 	//Mipmap : aucun
		GL_RGBA, 	//Couleurs : 4
		4, 	//Largeur : 2
		4, 	//Hauteur : 2
		0, 	//Largeur du bord : 0
		GL_RGBA, 	//Format : RGBA
		GL_INT, 	//Type des couleurs
		texture); 	//Addresse de l'image
		glBindTexture(GL_TEXTURE_2D,0);
	}
	public int getID() {
		return m_ID;
	}
	public void destroy() {
		glDeleteTextures(m_ID);
	}
	private final int m_ID;
}
