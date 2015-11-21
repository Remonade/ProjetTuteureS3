/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Logic;

import Maths.Vector2f;

public class EntityDataUnit extends EntityData {
    private int m_maxHealth=0;
	private int m_regenHealth=0;
    private int m_maxEnergy=0;
	private int m_regenEnergy=0;
    private Vector2f m_maxSpeed=new Vector2f();
    
    public EntityDataUnit() {
        super();
    }
    public void setMaxHealth(int max) {
        m_maxHealth=max;
    }
    public int getMaxHealth() {
        return m_maxHealth;
    }
    public void setRegenHealth(int regen) {
        m_regenHealth=regen;
    }
    public int getRegenHealth() {
        return m_regenHealth;
    }
    public void setMaxSpeed(Vector2f max) {
        m_maxSpeed=max;
    }
    public Vector2f getMaxSpeed() {
        return m_maxSpeed;
    }
    
    public static EntityDataUnit create(String key) {
        EntityDataUnit e=new EntityDataUnit();
        all.put(key,e);
        return e;
    }
}
