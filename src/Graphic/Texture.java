package Graphic;

import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture {
	public Texture(String path) {
		ByteBuffer texture=ByteBuffer.allocate(64);
		for(int i=0;i<64;i++)
			texture.put((byte)0x88);
		texture.flip();
		
		m_ID=glGenTextures();
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D,m_ID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D (
		GL_TEXTURE_2D, 	//Type : texture 2D
		0, 	//Mipmap : aucun
		GL_RGBA, 	//Couleurs : 4
		4, 	//Largeur : 4
		4, 	//Hauteur : 4
		0, 	//Largeur du bord : 0
		GL_RGBA, 	//Format : RGBA
		GL_UNSIGNED_BYTE, 	//Type des couleurs
		texture); 	//Addresse de l'image
		glBindTexture(GL_TEXTURE_2D,0);
	}
	public int getID() {
		return m_ID;
	}
	public void bind() {
		glEnable(GL_TEXTURE);
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D,m_ID);
	}
	public void unbind() {
		glBindTexture(GL_TEXTURE_2D,0);
		glDisable(GL_TEXTURE);
	}
	public void destroy() {
		glDeleteTextures(m_ID);
	}
	private final int m_ID;
}
