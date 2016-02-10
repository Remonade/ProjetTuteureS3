package Graphic;

import Maths.Vector4f;
import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;


public class Particle extends Model{
    public Particle() {
        super();
        m_size=0.05f;
        System.out.println("New ModelQuad instance");
        m_verticeCount=6;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        
        // init vertice cord
        m_vertice.put(m_size*-0.5f);m_vertice.put(m_size*-0.5f);
        m_vertice.put(m_size*+0.5f);m_vertice.put(m_size*-0.5f);
        m_vertice.put(m_size*+0.5f);m_vertice.put(m_size*+0.5f);
        m_vertice.put(m_size*-0.5f);m_vertice.put(m_size*-0.5f);
        m_vertice.put(m_size*-0.5f);m_vertice.put(m_size*+0.5f);
        m_vertice.put(m_size*+0.5f);m_vertice.put(m_size*+0.5f);
        
        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(2.0f);m_textures.put(0);
        m_textures.put(2.0f);m_textures.put(2.0f);
        m_textures.put(0);m_textures.put(0);
        m_textures.put(0);m_textures.put(2.0f);
        m_textures.put(2.0f);m_textures.put(2.0f);
        
        // flipping buffer
        m_vertice.flip();
        m_textures.flip();
    }
    public void draw(float x,float y,Vector4f colors,float angle,float time) {
        startRender(x,y,angle,time);
        render(colors);
        endRender();
    }
    public void startRender(float x,float y,float a,float t) {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(x,y,0);
        glRotatef(a,0.0f,0.0f,0.1f);
        glTranslatef(t,(float)Math.sin(t*15.0f)*t/10.f,0.0f);
        float c,m;
        c=0.3f+t*3;
        m=0.3f+3;
        glScalef(m-c,m-c,1);
        if(m_texture!=null)
            m_texture.bind(); // bind texture
    }
}
