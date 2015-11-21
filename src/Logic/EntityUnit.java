package Logic;

import Graphic.GraphicMain;
import static Logic.Type.MELEE;
import java.util.ArrayList;
import Maths.Vector2f;
import Maths.Vector3f;

public class EntityUnit extends EntityDynamic {
    public Type m_type;
    public boolean m_orientation; //True means "look to the right"
	protected int m_health=0;

    /**
    * Default constructor
    */
    public EntityUnit() {
        super();
        m_type = MELEE;
        m_orientation = true;
		m_name="Batard!";
    }

    /**
    * Complete constructor
    */
    public EntityUnit(Type m_type, Vector2f m_size, boolean m_orientation) {
        super();
        this.m_type= m_type;
        this.m_orientation = m_orientation;
    }
	@Override
	public void setData(EntityData data) {
		super.setData(data);
		m_health=getMaxHealth();
	}
	public int getHealth() {
		return m_health;
	}
	public int getMaxHealth() {
		if(m_data==null)
			return 0;
		return ((EntityDataUnit)m_data).getMaxHealth();
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
		
    }
	public void kill() {
		remove(this);
		
		if(this==Logic.getPlayer())
			Logic.killPlayer();
		else {
			Entity temp;
			temp=EntityUnit.create();
			temp.setData(EntityDataUnit.get("enemy"));
			temp.setPos(7,1);
		}
	}
	public void shoot(Vector2f target) {
		EntityMissile mis=EntityMissile.create();
		mis.setDir(target.subtract(m_pos).normalize());
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
        if(m_pos.x > Logic.getPlayer().m_pos.x && m_orientation == true) {
            m_orientation = false;
        }
        else if (m_pos.x < Logic.getPlayer().m_pos.x && m_orientation == false) {
            m_orientation = true;
        }
    }
    public void move() {
        switch(m_type){
            case MELEE :
				if(Logic.getPlayer().getPos().y > m_pos.y+Logic.getPlayer().getSize().y)
					jump();
				if(Math.abs(Logic.getPlayer().getPos().x-m_pos.x)>2f) {
					if(m_orientation) {
						setSpeed(0.05f, m_speed.y);
					}
					else {
						setSpeed(-0.05f, m_speed.y);
					}
				}
				if(Math.random()<0.1)
					shoot(Logic.getPlayer().getPos());
				break;
            case RANGED :
				shoot(Logic.getPlayer().getPos());
                break;
            default :
                break;
        }
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
