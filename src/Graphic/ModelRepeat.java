/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Maths.Vector2f;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

public class ModelRepeat extends ModelQuad {
	public ModelRepeat(float r, float g, float b) {
		super(r,g,b);
	}
	@Override
    public void draw(Vector2f pos,Vector2f size) {
        startRender(pos,size);
        render(size);
        endRender();
    }
    public void render(Vector2f size) {
        glBegin(GL_TRIANGLES);
        float[] colors=m_colors.array();
        float[] textures=m_textures.array();
        float[] vertice=m_vertice.array();
        sendColors(colors);
        for(int i=0;i<m_verticeCount;i++) {
            if(m_texture!=null) // send texture
                sendTextures(i,textures,size);
            sendVertice(i,vertice);
        }
        glEnd();
    }
    public void sendTextures(int offset,float[]data,Vector2f size) {
        glTexCoord2f(Math.round(data[offset*2]*size.x*2),Math.round(data[offset*2+1]*size.y));
    }
}
