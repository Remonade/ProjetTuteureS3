/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import static Logic.EntityData.all;

public class EntityDataMissile extends EntityData {
    private int m_damage=0;
	private float m_speed;
    
    public EntityDataMissile() {
        super();
    }
    public void setDamage(int max) {
        m_damage=max;
    }
    public int getDamage() {
        return m_damage;
    }
    public void setMaxSpeed(float speed) {
        m_speed=speed;
    }
    public float getMaxSpeed() {
        return m_speed;
    }
    
    public static EntityDataMissile create(String key) {
        EntityDataMissile e=new EntityDataMissile();
        all.put(key,e);
        return e;
    }
}
