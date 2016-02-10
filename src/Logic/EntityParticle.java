/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Graphic.Particle;

public class EntityParticle extends Entity {
	public EntityParticle() {
		super();
		m_time=0.0f;
		m_lifetime=1.0f;
		m_angle=0.0f;
	}
	public EntityParticle(float lifetime,float a) {
		super();
		m_time=0.0f;
		m_lifetime=lifetime;
		m_angle=a;
	}
	public boolean updateTime(float time) {
		m_time += time;
		if(m_time<m_lifetime)
			return false;
		return true;
	}
	@Override
	public void draw() {
		Particle model=(Particle)getModel();
		if(model!=null)
			model.draw(m_pos.x, m_pos.y, getColor(), m_angle, m_time);
	}
	private float m_time;
	private float m_lifetime;
	private float m_angle;
}
