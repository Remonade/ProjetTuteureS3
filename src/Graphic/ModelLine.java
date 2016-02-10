package Graphic;
/*
* TODO: javadoc
*/
import Maths.Vector4f;
import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

public class ModelLine extends Model{
    public ModelLine(FloatBuffer vertice) {
        super();
        System.out.println("New ModelQuad instance");
        m_vertice=vertice;
        m_verticeCount=m_vertice.capacity()/2;
        m_textures=null;
    }
    @Override
    public void sendVertice(int offset,float[]data) {
        glVertex3f(data[offset*2],data[offset*2+1],0.0f);
    }
    @Override
    public void render(Vector4f colors) {
        glBegin(GL_LINE_STRIP);
        float[] vertice=m_vertice.array();
        sendColors(colors);
        for(int i=0;i<m_verticeCount;i++) {
            sendVertice(i,vertice);
        }
        glEnd();
    }
}
