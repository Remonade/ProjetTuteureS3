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
	
    // base
    public EntityDataMissile() {
        super();
    }
	// damage part
    public void setDamage(int max) {
        m_damage=max;
    }
    public int getDamage() {
        return m_damage;
    }
	// movement part
    public void setMaxSpeed(float speed) {
        m_speed=speed;
    }
    public float getMaxSpeed() {
        return m_speed;
    }
    // fonction de création
    public static EntityDataMissile create(String key) {
        EntityDataMissile e=new EntityDataMissile();
        all.put(key,e);
        return e;
    }
}
