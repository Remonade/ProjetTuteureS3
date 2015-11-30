package Graphic;
/*
* TODO: javadoc
*/
import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class ModelLine extends Model{
    public ModelLine(float r,float g,float b,FloatBuffer vertice) {
        super();
        System.out.println("New ModelQuad instance");
        m_vertice=vertice;
        m_verticeCount=m_vertice.capacity()/2;
        m_textures=null;
        m_colors=FloatBuffer.allocate(3);
        
        // init colors
        m_colors.put(r);
        m_colors.put(g);
        m_colors.put(b);
        
        // flipping buffer
        m_colors.flip();
    }
    @Override
    public void sendVertice(int offset,float[]data) {
        glVertex3f(data[offset*2],data[offset*2+1],0.0f);
    }
    @Override
    public void sendColors(float[]data) {
        glColor3f(data[0],data[1],0.0f);
    }
    @Override
    public void render() {
        glBegin(GL_LINE_STRIP);
        float[] colors=m_colors.array();
        float[] vertice=m_vertice.array();
        sendColors(colors);
        for(int i=0;i<m_verticeCount;i++) {
            sendVertice(i,vertice);
        }
        glEnd();
    }
}
