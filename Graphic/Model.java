package Graphic;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Model {
	public Model(int count) {
		if(count>2)
			m_verticeCount=count;
		
		m_vertice=FloatBuffer.allocate(m_verticeCount*2);
		m_textures=FloatBuffer.allocate(m_verticeCount*2);
		m_colors=FloatBuffer.allocate(m_verticeCount*3);
		
		for(int i=0;i<m_verticeCount;i++) {
			m_vertice.put((float)Math.cos((2*Math.PI/(float)m_verticeCount)*(float)i)*m_size);
			m_vertice.put((float)Math.sin((2*Math.PI/(float)m_verticeCount)*(float)i)*m_size);
			m_colors.put(1.0f);
			m_colors.put(1.0f);
			m_colors.put(1.0f);
		}
		m_vertice.flip();
		m_colors.flip();
		System.out.println("Successfully crated model with "+m_verticeCount+" vertice.");
		
		m_verticeVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, m_verticeVBO);
		glBufferData(GL_ARRAY_BUFFER, m_vertice, GL_STATIC_DRAW);
		
		m_colorsVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, m_colorsVBO);
		glBufferData(GL_ARRAY_BUFFER, m_colors, GL_STATIC_DRAW);
		
		m_texturesVBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, m_texturesVBO);
		glBufferData(GL_ARRAY_BUFFER, m_textures, GL_STATIC_DRAW);
	}
	/*
	public void draw(float angle) {
        GL.createCapabilities();
		float[] vertice=m_vertice.array();
		float[] colors=m_colors.array();
		glLoadIdentity();
		glRotated(angle*180/3.14,0,0,1);
		glBegin(GL_LINE_LOOP);
			for(int i=0;i<m_verticeCount;i++) {
				glColor3f(colors[i*3]/angle,colors[i*3+1]/angle,colors[i*3+2]*angle);
				glVertex2f(vertice[i*2],vertice[i*2+1]);
			}
		glEnd();
	}
	*/
	public void draw(Shader shader,FloatBuffer projection,FloatBuffer modelview) {
		// Activation du shader
		glUseProgram(shader.getProgramID());
			int floatSize = 4;
			// Send vertice position
			glBindBuffer(GL_ARRAY_BUFFER, m_verticeVBO);
			int posAttrib = glGetAttribLocation(shader.getProgramID(), "in_vertex");
			glEnableVertexAttribArray(posAttrib);
			glVertexAttribPointer(posAttrib, 2, GL_FLOAT, false, m_verticeCount * 6 * floatSize, 0);
			// Send colors
			glBindBuffer(GL_ARRAY_BUFFER, m_colorsVBO);
			int colAttrib = glGetAttribLocation(shader.getProgramID(), "color");
			glEnableVertexAttribArray(colAttrib);
			glVertexAttribPointer(colAttrib, 3, GL_FLOAT, false, m_verticeCount * 9 * floatSize, 0);
			// Send texture position
			if(m_texture!=null) {
				glBindBuffer(GL_ARRAY_BUFFER, m_texturesVBO);
				int textAttrib = glGetAttribLocation(shader.getProgramID(), "color");
				glEnableVertexAttribArray(textAttrib);
				glVertexAttribPointer(textAttrib, 2, GL_FLOAT, false, m_verticeCount * 6 * floatSize, 0);
			}
			
			// Envoi des matrices
			//glUniformMatrix4fv(glGetUniformLocation(shader.getProgramID(), "projection"), false, projection);
			//System.out.println("projection sent");
			//glUniformMatrix4fv(glGetUniformLocation(shader.getProgramID(), "modelview"), false, modelview);
			//System.out.println("modelview sent");
			
			// Verrouillage de la texture
			if(m_texture!=null)
				glBindTexture(GL_TEXTURE_2D, m_texture.getID());
			// Rendu
			glDrawArrays(GL_LINES, 0, m_verticeCount);
			// Déverrouillage de la texture
			if(m_texture!=null)
				glBindTexture(GL_TEXTURE_2D, 0);

			// Désactivation des tableaux
			glDisableVertexAttribArray(1);
			glDisableVertexAttribArray(0);

		// Désactivation du shader
		glUseProgram(0);
	}
	
	public void destroy() {
		glDeleteBuffers(m_verticeVBO);
		glDeleteBuffers(m_colorsVBO);
		glDeleteBuffers(m_texturesVBO);
	}
	private final FloatBuffer m_vertice;
	private final int m_verticeVBO;
	private final FloatBuffer m_textures;
	private final int m_texturesVBO;
	private final FloatBuffer m_colors;
	private final int m_colorsVBO;
	
	private Texture m_texture=null;
	private int m_verticeCount=2;
	private final float m_size=0.7500f;
}