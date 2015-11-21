package Graphic;
/*
* TODO: javadoc
*/
import java.nio.FloatBuffer;

public class ModelQuad extends Model{
    public ModelQuad(float r,float g,float b) {
        super();
        System.out.println("New ModelQuad instance");
        m_verticeCount=6;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        m_colors=FloatBuffer.allocate(m_verticeCount*3);
        
        // init vertice cord
        m_vertice.put(-1f);m_vertice.put(-1f);
        m_vertice.put(+1f);m_vertice.put(-1f);
        m_vertice.put(+1f);m_vertice.put(+1f);
        m_vertice.put(-1f);m_vertice.put(-1f);
        m_vertice.put(-1f);m_vertice.put(+1f);
        m_vertice.put(+1f);m_vertice.put(+1f);
        
        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(2.0f);m_textures.put(0);
        m_textures.put(2.0f);m_textures.put(2.0f);
        m_textures.put(0);m_textures.put(0);
        m_textures.put(0);m_textures.put(2.0f);
        m_textures.put(2.0f);m_textures.put(2.0f);
        
        // init colors
        for(int i=0;i<m_verticeCount;i++) {
            m_colors.put(r);
            m_colors.put(g);
            m_colors.put(b);
        }
        
        // flipping buffer
        m_vertice.flip();
        m_textures.flip();
        m_colors.flip();
    }
}
