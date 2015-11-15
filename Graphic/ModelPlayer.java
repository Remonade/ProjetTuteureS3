/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import java.nio.FloatBuffer;

public class ModelPlayer extends Model{
    
    public ModelPlayer() {
        super();

        System.out.println("New ModelPlayer instance");
        m_verticeCount=6;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        m_colors=FloatBuffer.allocate(m_verticeCount*3);

        // init vertice cord
        m_vertice.put(m_size*-0.25f);m_vertice.put(m_size*-0.25f);
        m_vertice.put(m_size*+0.25f);m_vertice.put(m_size*-0.25f);
        m_vertice.put(m_size*+0.25f);m_vertice.put(m_size*+0.25f);
        m_vertice.put(m_size*-0.25f);m_vertice.put(m_size*-0.25f);
        m_vertice.put(m_size*-0.25f);m_vertice.put(m_size*+0.25f);
        m_vertice.put(m_size*+0.25f);m_vertice.put(m_size*+0.25f);

        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(1);m_textures.put(0);
        m_textures.put(1);m_textures.put(1);
        m_textures.put(0);m_textures.put(0);
        m_textures.put(0);m_textures.put(1);
        m_textures.put(1);m_textures.put(1);

        // init colors
        for(int i=0;i<m_verticeCount;i++) {
                m_colors.put(0.0f/(float)i);
                m_colors.put(0.5f/(float)i);
                m_colors.put(1.0f);
        }

        // flipping buffer
        m_vertice.flip();
        m_textures.flip();
        m_colors.flip();
    }
}
