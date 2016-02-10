/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphic;

import Maths.Vector2f;
import Maths.Vector4f;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

public class ModelAnim extends Model {
    public void draw(Vector2f pos,Vector2f size,Vector4f colors,float angle,double time,String anim) {
        startRender(pos,size);
        glRotatef(angle,0,0,1);
		Animation current=getAnimation(anim);
        render(colors,current,(float)time);
        endRender();
    }
    public void draw(Vector2f pos,Vector2f size,Vector4f colors,double time,String anim) {
        startRender(pos,size);
		
		Animation current=getAnimation(anim);
        render(colors,current,(float)time);
        endRender();
    }
	@Override
    public void startRender(Vector2f pos,Vector2f size) {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(pos.x,pos.y,0);
        glScalef(size.x,size.y,0);
    }
    public void render(Vector4f colors,Animation current,float time) {
		if(current!=null)
			current.sendTexture();
        glBegin(GL_TRIANGLES);
        float[] vertice=m_vertice.array();
        sendColors(colors);
        for(int i=0;i<m_verticeCount;i++) {
			if(current!=null)
				sendTexture(i,current,time);
            sendVertice(i,vertice);
        }
        glEnd();
		if(current!=null)
			current.stopTexture();
    }
	public void sendTexture(int vertex,Animation current,float time) {
		if(vertex==0 ||vertex==3)
			current.sendAnimationData(0,time);
		if(vertex==1)
			current.sendAnimationData(1,time);
		if(vertex==2 || vertex==5)
			current.sendAnimationData(2,time);
		if(vertex==4)
			current.sendAnimationData(3,time);
	}
	public Animation getAnimation(String anim) {
		Animation a=m_animList.get(anim);
		if(a==null)
			a=m_animList.get("NAN");
		return a;
	}
	public void setAnimation(String name,Animation animation) {
		m_animList.put(name,animation);
	}
	protected HashMap<String,Animation> m_animList=new HashMap<String,Animation>();
}
