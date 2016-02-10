package Graphic;

import java.nio.FloatBuffer;
import Maths.Vector2f;
import Maths.Vector4f;
import static org.lwjgl.opengl.GL11.*;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

public class Model {
    public Model() {
        m_verticeCount=6;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        
        // init vertice cord
        m_vertice.put(-1f);m_vertice.put(-1f);
        m_vertice.put(+1f);m_vertice.put(-1f);
        m_vertice.put(+1f);m_vertice.put(+1f);
        m_vertice.put(-1f);m_vertice.put(-1f);
        m_vertice.put(-1f);m_vertice.put(+1f);
        m_vertice.put(+1f);m_vertice.put(+1f);
        
        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(1.0f);m_textures.put(0);
        m_textures.put(1.0f);m_textures.put(1.0f);
        m_textures.put(0);m_textures.put(0);
        m_textures.put(0);m_textures.put(1.0f);
        m_textures.put(1.0f);m_textures.put(1.0f);
        
        // flipping buffer
        m_vertice.flip();
        m_textures.flip();
    }
	public void setTexture(Texture texture) {
		m_texture=texture;
	}
	public Texture getTexture() {
		return m_texture;
	}
	public void draw(Vector2f pos,Vector2f size, Vector4f colors, float angle) {
        startRender(pos,size);
        glRotatef(angle,0,0,1);
        render(colors);
        endRender();
	}
    public void draw(Vector2f pos,Vector2f size, Vector4f colors) {
        startRender(pos,size);
        render(colors);
        endRender();
    }
    public void startRender(Vector2f pos,Vector2f size) {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
		if(pos==null)
			glTranslatef(0,0,0);
		else
			glTranslatef(pos.x,pos.y,0);
		if(size==null)
			glScalef(1,1,0);
		else
			glScalef(size.x,size.y,0);
		
        if(getTexture()!=null)
            getTexture().bind(); // bind texture
    }
    public void render(Vector4f colors) {
        glBegin(GL_TRIANGLES);
        float[] textures=m_textures.array();
        float[] vertice=m_vertice.array();
        sendColors(colors);
        for(int i=0;i<m_verticeCount;i++) {
            if(m_texture!=null) // send texture
                sendTextures(i,textures);
            sendVertice(i,vertice);
        }
        glEnd();
    }
    public void endRender() {
        if(m_texture!=null) // unbind texture
            m_texture.unbind();
    }
    public void sendColors(Vector4f colors) {
		if(colors==null)
			glColor4f(1f,1f,1f,1);
		else
			glColor4f(colors.x,colors.y,colors.z,colors.w);
    }
    public void sendTextures(int offset,float[]data) {
        glTexCoord2f(data[offset*2]*GraphicMain.DIRECTION,data[offset*2+1]);
    }
    public void sendVertice(int offset,float[]data) {
        glVertex3f(data[offset*2],data[offset*2+1],GraphicMain.LAYER);
    }
    public void destroy() {
        if(m_texture!=null)
            m_texture.destroy();
    }
    protected FloatBuffer m_vertice=null;
    protected FloatBuffer m_textures=null;
    
    protected Texture m_texture=null;
    protected int m_verticeCount;
    protected float m_size=1.0f;
	
	public static void renderTexture(String texture, Vector2f pos, Vector2f size, Vector4f color) {
		Model m=new Model();
		if(!texture.equals("")) {
			Texture t=GraphicMain.textures.get(texture);
			if(t==null) {
				t=Texture.loadTexture(texture);
				GraphicMain.textures.put(texture, t);
			}
			m.setTexture(t);
		}
		
		m.draw(pos, size, color);
	}
}
