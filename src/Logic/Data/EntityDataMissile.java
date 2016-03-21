/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic.Data;

import Logic.Data.EntityData;
import static Logic.Data.EntityData.all;

public class EntityDataMissile extends EntityData {
	// champs
    private int m_damage=0;
	private float m_speed=0.05f;
	private float m_radius=0f;
	private float m_range=0f;
	
    // base
    public EntityDataMissile() {
        super();
    }
	// damage part
    public void setDamage(int damage) {
        m_damage=damage;
    }
    public int getDamage() {
        return m_damage;
    }
    public void setRadius(float radius) {
        m_radius=radius;
    }
    public float getRadius() {
        return m_radius;
    }
	// movement part
    public void setMaxSpeed(float speed) {
        m_speed=speed;
    }
    public float getMaxSpeed() {
        return m_speed;
    }
    public void setRange(float range) {
        m_range=range;
    }
    public float getRange() {
        return m_range;
    }
    // fonction de création
    public static EntityDataMissile create(String key) {
        EntityDataMissile e=new EntityDataMissile();
        all.put(key,e);
        return e;
    }
}
