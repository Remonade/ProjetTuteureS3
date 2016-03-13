/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Graphic.Model;
import Graphic.ModelAnim;
import Logic.Data.EntityDataParticle;
import Maths.Vector2f;
import Maths.Vector4f;

public class EntityParticle extends Entity {
	public EntityParticle() {
		super();
		m_time=0;
		m_angle=(float)Math.random()*360;
	}
	public EntityParticle(float a) {
		super();
		m_time=0;
		m_angle=a;
	}
	public boolean updateTime() {
			if(m_data!=null) {
			m_time += Logic.DELTA_TIME;

			m_angle+=getData().getRotation()*Logic.DELTA_TIME;

			if(m_time<getDuration())
				return false;
			return true;
		}
		return false;
	}
	@Override
	public void draw() {
		if(m_data!=null) {
			Vector4f color=getColor();
			
				color.w=0.25f+(1-m_time/getDuration())*color.w;
			
			Vector2f mod;
			if(getType()==1)
				mod=new Vector2f((float)Math.cos(m_angle),(float)Math.sin(m_angle)).scale((getDuration()-m_time)*getSpeed());
			else
				mod=new Vector2f((float)Math.cos(m_angle),(float)Math.sin(m_angle)).scale(m_time*getSpeed());
			
			Model model=getData().getModel();
			if(model instanceof ModelAnim)
				((ModelAnim)model).draw(m_pos.subtract(mod),getModelSize(),color,0,m_time,"NAN");
			else
				model.draw(m_pos.subtract(mod),getModelSize(),color,m_angle);
		}
	}
	
	@Override
	public EntityDataParticle getData() {
		return (EntityDataParticle)m_data;
	}
	private float m_time;
	private float m_angle;
	
    public float getRotation() {
		return getData().getRotation();
	}
	public float getSpeed() {
		return getData().getSpeed();
	}
	public float getDuration() {
		return getData().getDuration();
	}
	public int getType() {
		return getData().getType();
	}
}
