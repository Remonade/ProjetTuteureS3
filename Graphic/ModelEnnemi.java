/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.nio.FloatBuffer;

/**
 *
 * @author Pierrot
 */
public class ModelEnnemi extends Model{
    
    public ModelEnnemi(){
        super();
        m_size = 0.25f;
        
        m_verticeCount=3;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        m_colors=FloatBuffer.allocate(m_verticeCount*3);
        
        // init vertice cord
        m_vertice.put(m_size* 0);m_vertice.put(m_size* -1);
        m_vertice.put(m_size* 0);m_vertice.put(m_size* 1);
        m_vertice.put(m_size* 1);m_vertice.put(m_size* 0);

        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(1);m_textures.put(0);
        m_textures.put(1);m_textures.put(1);
        
        // init colors
        for(int i=0 ; i<3 ; i++){
            m_colors.put(1);
            m_colors.put(0);
            m_colors.put(0);
        }

        // flipping buffer
        m_vertice.flip();
        m_textures.flip();
        m_colors.flip();
    }
    
    public ModelEnnemi(float r, float g, float b){
        super();
        m_size = 0.25f;
        
        m_verticeCount=3;
        m_vertice=FloatBuffer.allocate(m_verticeCount*2);
        m_textures=FloatBuffer.allocate(m_verticeCount*2);
        m_colors=FloatBuffer.allocate(m_verticeCount*3);
        
        // init vertice cord
        m_vertice.put(m_size* 0);m_vertice.put(m_size* -1);
        m_vertice.put(m_size* 0);m_vertice.put(m_size* 1);
        m_vertice.put(m_size* 1);m_vertice.put(m_size* 0);

        // init textures cord
        m_textures.put(0);m_textures.put(0);
        m_textures.put(1);m_textures.put(0);
        m_textures.put(1);m_textures.put(1);
        
        for(int i=0 ; i<3 ; i++){
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
