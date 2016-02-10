/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Logic.Data;

import Logic.Data.EntityData;
import Logic.Type;
import static Logic.Type.*;

import Maths.Vector2f;

public class EntityDataUnit extends EntityData {
    private float m_maxHealth=0;
	private float m_regenHealth=0;
    private float m_maxEnergy=0;
	private float m_regenEnergy=0;
    private float m_maxShield=0;
	private float m_regenShield=0;
    private Vector2f m_maxSpeed=new Vector2f(0.5f,0.5f);
    private Type m_type=GUNNER;
    
    public EntityDataUnit() {
        super();
    }
    public void setMaxHealth(float max) {
        m_maxHealth=max;
    }
    public float getMaxHealth() {
        return m_maxHealth;
    }
    public void setRegenHealth(float regen) {
        m_regenHealth=regen;
    }
    public float getRegenHealth() {
        return m_regenHealth;
    }
    public void setMaxSpeed(Vector2f max) {
        m_maxSpeed=max;
    }
    public Vector2f getMaxSpeed() {
        return m_maxSpeed;
    }
    public void setType(Type type) {
        m_type=type;
    }
    public Type getType(){
        return m_type; 
    }
    public void setMaxShield(float maxShield) {
        m_maxShield=maxShield;
    }
    public float getMaxShield() {
        return m_maxShield;
    }
    public void setRegenShield(float regenShield){
        m_regenShield=regenShield;
    }
    public float getRegenShield() {
        return m_regenShield;
    }
    public void setMaxEnergy(float maxEnergy) {
        m_maxEnergy=maxEnergy;
    }
    public float getMaxEnergy() {
        return m_maxEnergy;
    }
    public void setRegenEnergy(float regenEnergy){
        m_regenEnergy=regenEnergy;
    }
    public float getRegenEnergy() {
        return m_regenEnergy;
    }
    
    public static EntityDataUnit create(String key) {
        EntityDataUnit e=new EntityDataUnit();
        all.put(key,e);
        return e;
    }
}
