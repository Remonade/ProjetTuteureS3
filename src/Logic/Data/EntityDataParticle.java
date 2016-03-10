/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Data;
public class EntityDataParticle extends EntityData {
	private float m_speed;
	private float m_rotation;
	private float m_duration;
	private int m_type; // 0-> to out; 1-> to in
	
	public EntityDataParticle() {
		m_speed=0.5f;
		m_rotation=0;
		m_duration=0.5f;
		m_type=0;
	}
	// setters
	public void setSpeed(float speed) {
		this.m_speed = speed;
	}

	public void setRotation(float rotation) {
		this.m_rotation = rotation;
	}

	public void setDuration(float duration) {
		this.m_duration = duration;
	}
	public void setType(int type) {
		m_type=type;
	}
	// getters
    public float getRotation() {
		return m_rotation;
	}
	public float getSpeed() {
		return m_speed;
	}
	public float getDuration() {
		return m_duration;
	}
	public int getType() {
		return m_type;
	}
    public static EntityDataParticle create(String key) {
        EntityDataParticle e=new EntityDataParticle();
        all.put(key,e);
        return e;
    }
    public static EntityDataParticle get(String key) {
        EntityDataParticle temp=(EntityDataParticle)all.get(key);
        return temp;
    }
}
