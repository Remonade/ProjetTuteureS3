/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package Logic;

import Maths.Vector2f;

public class EntityDataDynamic extends EntityData {
    private int m_maxHP=0;
    private Vector2f m_maxSpeed=new Vector2f();
    
    public EntityDataDynamic() {
        super();
    }
    public void setMaxHP(int max) {
        m_maxHP=max;
    }
    public int getMaxHP() {
        return m_maxHP;
    }
    public void setMaxSpeed(Vector2f max) {
        m_maxSpeed=max;
    }
    public Vector2f getMaxSpeed() {
        return m_maxSpeed;
    }
    
    public static EntityDataDynamic create(String key) {
        EntityDataDynamic e=new EntityDataDynamic();
        all.put(key,e);
        return e;
    }
}
