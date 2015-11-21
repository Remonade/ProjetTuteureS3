package Logic;

import Graphic.GraphicMain;
import static Logic.Type.*;
import java.util.ArrayList;
import Maths.Vector2f;
import Maths.Vector3f;

public class EntityUnit extends EntityDynamic {
    protected boolean m_lookRight; //True means "look to the right"
	protected int m_health=0;
    protected float m_energy=0f;
        protected float m_custom=0f;

    /**
    * Default constructor
    */
    public EntityUnit() {
        super();
        m_lookRight = true;
            m_name="Batard!";
    }

    @Override
    public void setData(EntityData data) {
	super.setData(data);
	m_health=getMaxHealth();
        m_energy=getMaxEnergy();
    }
    public float getEnergy() {
	return m_energy;
    }
    public float getRegenEnergy() {
        if(m_data==null)
            return 0;
        return ((EntityDataUnit)m_data).getRegenEnergy();
    }
    public float getMaxEnergy() {
	if(m_data==null)
            return 0;
	return ((EntityDataUnit)m_data).getMaxEnergy();
    }
    public int getHealth() {
	return m_health;
    }
    public int getMaxHealth() {
	if(m_data==null)
            return 0;
	return ((EntityDataUnit)m_data).getMaxHealth();
    }
    public boolean getLookRight(){
        return m_lookRight;
    }
    public void setLookRight(boolean lookRight){
        m_lookRight=lookRight;
    }
    @Override
    public void update() {
        super.update();
	//if(m_health<getMaxHealth())
	//m_hp++;
	if(m_health>getMaxHealth())
            m_health=getMaxHealth();
        rotate();
        move();	
        attack();
        regen();
    }
    public void kill() {
	remove(this);
	if(this==Logic.getPlayer())
            Logic.killPlayer();
        /*else {
            Entity temp;
            temp=EntityUnit.create();
            temp.setData(EntityDataUnit.get("enemy"));
            temp.setPos(7,1);*/
    }
    public void shoot(Vector2f target) {
        
	EntityMissile mis=EntityMissile.create();
        if(m_lookRight)
            mis.setDir(new Vector2f(1,0));
        else
            mis.setDir(new Vector2f(-1,0));
	mis.setPos(m_pos.add(mis.getDir().divide(50)));
	mis.setData(EntityDataUnit.get("missile"));
	mis.setOwner(this);
	mis.setTeam(getTeam());
    }
    public void jump() {
    	if(m_contact[CONTACT_DOWN])
            setSpeed(0,0.075f);
    }
    public void damage(int damage) {
	m_health-=damage;
	if(m_health<=0)
            kill();
    }
    
    public void rotate() {
        if(m_pos.x > Logic.getPlayer().m_pos.x && m_lookRight == true) {
            m_lookRight = false;
        }
        else if (m_pos.x < Logic.getPlayer().m_pos.x && m_lookRight == false) {
            m_lookRight = true;
        }
    }
    public void move() {
        switch(((EntityDataUnit)m_data).getType()){
            case GUNNER :
		if(Logic.getPlayer().getPos().y > m_pos.y+Logic.getPlayer().getSize().y)
                    jump();
		if(Math.abs(Logic.getPlayer().getPos().x-m_pos.x)>2f) {
                    if(m_lookRight) {
			setSpeed(0.05f, m_speed.y);
                    }
                    else {
                        setSpeed(-0.05f, m_speed.y);
                    }
		}
                break;
            case SNIPER :
                if (Math.abs(Logic.getPlayer().getPos().x-m_pos.x)>3f) {
                    if(m_lookRight) {
			setSpeed(0.05f, m_speed.y);
                    }
                    else {
                        setSpeed(-0.05f, m_speed.y);
                    }
                }
                else if (Math.abs(Logic.getPlayer().getPos().x-m_pos.x)<2.5f) {
                    if(m_lookRight) {
			setSpeed(-0.05f, m_speed.y);
                    }
                    else {
                        setSpeed(0.05f, m_speed.y);
                    }
                }
                break;
            default :
                break;
        }
    }
    
    public void attack() {
        switch(((EntityDataUnit)m_data).getType()){
            case GUNNER :
                if(getMaxEnergy()==m_energy)
                    m_custom=1f; //the gunner can launch a burst with his weapon
                if(m_custom==1f && m_energy-10>0f) { //the gunner shoot
                    shoot(Logic.getPlayer().getPos()); 
                    m_energy-=10; 
                }
                else
                    m_custom=0f; //the weapon of the gunner is overheated and he needs to wait before launching another burst
                break;
            case SNIPER :
                if(m_energy-50>0f) {
                    shoot(Logic.getPlayer().getPos());
                    m_energy-=50;
                }
                break;
            default :
                break;
        }
    }
    public void regen(){
        if(m_energy+getRegenEnergy()<getMaxEnergy()){
            m_energy+=getRegenEnergy();
        }
        else if (m_energy==getMaxEnergy());
        else 
            m_energy=getMaxEnergy();
    }
    
    @Override
    public void draw() {
	super.draw();
	GraphicMain.drawString(getHealth()+"/"+getMaxHealth(),m_pos.add(getSize()),0.01f,new Vector3f(1,1,1));
    }
    public static EntityUnit create() {
	EntityUnit temp=new EntityUnit();
	add(temp);
	return temp;
    }
    public static void add(EntityUnit e) {
	EntityDynamic.add(e);
	all.add(e);
    }
    public static void remove(Entity e) {
	EntityDynamic.remove(e);
	all.remove(e);
    }
    public static ArrayList getAll() {
	return all;
    }
    private static ArrayList<EntityUnit> all=new ArrayList();
}
