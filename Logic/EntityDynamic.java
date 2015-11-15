/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import math.Vector2f;

public class EntityDynamic extends Entity {
	public Vector2f getSpeed() {
		return m_speed;
	}
	public void setSpeed(Vector2f speed) {
		m_speed = speed;
	}
	public void setSpeed(float x,float y) {
		m_speed.x=x;
		m_speed.y=y;
	}
	public void addSpeed(Vector2f speed) {
		m_speed.add(speed);
	}
	public void addSpeed(float x,float y) {
		m_speed.x+=x;
		m_speed.y+=y;
	}
	@Override
	public void delete() {
		super.delete();
		m_speed=null;
	}
	
	protected Vector2f m_speed;
}
