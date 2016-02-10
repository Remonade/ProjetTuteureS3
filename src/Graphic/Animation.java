/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Maths.Vector2f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

public class Animation {
	protected float m_duration=0;
	protected int[] m_frames=null;
	protected Texture m_texture=null;
	
	public Animation() {
		
	}
	public void setDuration(float duration) {
		m_duration=duration;
	}
	public float getDuration () {
		return m_duration;
	}
	public float getFrameDuration() {
		return m_duration/getFrameCount();
	}
	public void setFrameCount(int count) {
		m_frames=new int[count];
	}
	public int getFrameCount() {
		return m_frames.length;
	}
	public void setFrame(int frame,int clipID) {
		m_frames[frame]=clipID;
	}
	public int getFrame(float time) {
		time=time%m_duration;
		int out=0;
		while((out+1)*getFrameDuration()<time)
			out++;
		if(out<m_frames.length)
			return m_frames[out];
		return 0;
	}
	public void setTexture(Texture texture) {
		m_texture=texture;
	}
	public void sendTexture() {
		m_texture.bind();
	}
	public void stopTexture() {
		m_texture.unbind();
	}
	public void sendAnimationData(int corner, float time) {
		Vector2f tex=getTextureCoordinate(corner,time);
		glTexCoord2f(tex.x,tex.y);
	}
	public Vector2f getTextureCoordinate(int corner,float time) {
		if(m_texture==null)
			return new Vector2f(0,0);
		if(m_texture instanceof TextureCliped)
			return ((TextureCliped)m_texture).getCoordinate(corner,getFrame(time));
		return m_texture.getCoordinate(corner, 0);
	}
}
