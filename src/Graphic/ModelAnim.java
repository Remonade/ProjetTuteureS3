/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Maths.Vector2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

public class ModelAnim extends ModelQuad {
	public ModelAnim(float r, float g, float b) {
		super(r, g, b);
	}
    public void draw(Vector2f pos,Vector2f size,double time) {
		if(m_texture instanceof TextureAnimated) {
			m_spriteWidth=((TextureAnimated)m_texture).getSpriteWidth();
			m_spriteOffset=((TextureAnimated)m_texture).getCurrentSprite(time);
		} else {
			m_spriteWidth=1;
			m_spriteOffset=0;
		}
        startRender(pos,size);
        render();
        endRender();
    }
	@Override
    public void sendTextures(int offset,float[]data) {
        glTexCoord2f((data[offset*2]+m_spriteOffset)*m_spriteWidth,data[offset*2+1]);
    }
	public double getAnimDuration() {
		if(m_texture!=null && m_texture instanceof TextureAnimated)
			return ((TextureAnimated)m_texture).getAnimDuration();
		return 0;
	}
	private float m_spriteWidth=1;
	private int m_spriteOffset=0;
	private double m_time=0;
}
