/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import Logic.Data.EntityDataMissile;
import Graphic.Model;
import Graphic.ModelAnim;
import Logic.Data.EntityDataParticle;
import Logic.Data.Player;
import static Logic.Realm.getActiveRealm;
import java.util.ArrayList;
import Maths.Vector2f;

public class EntityMissile extends EntityDynamic {
	public EntityMissile() {
		super();
		m_collide=false;
	}
	
	public void setDir(Vector2f dir) {
		setDir(dir.x,dir.y);
	}
	public void setDir(float x,float y) {
		m_direction.x=x;
		m_direction.y=y;
		m_direction=m_direction.normalize();
		Vector2f norm=m_direction;
		m_angle=(float) (Math.acos(norm.x)*180/Math.PI);
		if(norm.y<0)
			m_angle=180-m_angle;
		
		m_speed=m_direction.scale(getMaxSpeed());
	}
	public Vector2f getDir() {
		return m_direction;
	}
	public EntityDataMissile getMissileData() {
		return (EntityDataMissile)m_data;
	}
	@Override
	public void update() {
		if(m_distance>getRange()) {
			Realm r=Realm.getActiveRealm();
			if(r!=null)
				r.removeEntity(this);
			Audio.Audio.playSound(getSound("fade"));
		} else {
			m_pos=m_pos.add(m_speed.scale((float)Logic.DELTA_TIME));
			m_distance+=Logic.DELTA_TIME*getMaxSpeed();
		}
	}
	public void explode(Entity target) {
		Realm r=Realm.getActiveRealm();
		if(r!=null)
			r.removeEntity(this);
		
		Audio.Audio.playSound(getSound("explode"));
		
		ArrayList<EntityUnit> targets=new ArrayList<>();
		
		if(getRadius()>0) {
			for(int i=0;i<30;i++) {
				EntityParticle temp;
					temp=new EntityParticle(/*0.35f,((float)Math.random()*50)-25+180*/);
					temp.setData(EntityDataParticle.get("EDPexplosion"));
					temp.setPos(getPos());
					getActiveRealm().addEntity(temp);
			}
			if(r!=null) {
				ArrayList<EntityUnit> units=r.getUnits();
				for(EntityUnit u:units) {
					if(u!=m_owner && getTeam()!=u.getTeam()) {
						float distance=u.getPos().subtract(getPos()).length()+u.getSize().length();
						if(distance<=getRadius())
							targets.add(u);
					}
				}
			}
		}
		if(target!=null && target instanceof EntityUnit && (EntityUnit)target!=m_owner && getTeam()!=((EntityUnit)target).getTeam())
			targets.add((EntityUnit)target);

		for(EntityUnit u:targets) {
			boolean kill=u.damage(getDamage());
			if(kill && m_owner instanceof EntityUnit) {
				Player p=((EntityUnit)m_owner).getOwner();
				if(p!=null)
					p.addXP((int)u.getMaxHealth());
			}
		}
	}

	public float getDistance() {
		return m_distance;
	}
	public float getMaxSpeed() {
		if(m_data!=null)
			return getMissileData().getMaxSpeed();
		return 0.05f;
	}
	public float getRange() {
		if(m_data!=null)
			return getMissileData().getRange();
		return 0.00f;
	}
	public float getRadius() {
		if(m_data!=null)
			return getMissileData().getRadius();
		return 0;
	}
	public int getDamage() {
		if(m_data!=null)
			return getMissileData().getDamage();
		return 1;
	}
	public void setOwner(Entity owner) {
		m_owner=owner;
	}
	public Entity getOwner() {
		return m_owner;
	}
	@Override
    public void draw() {
		Model model=getModel();
		if(model!=null) {
			if(model instanceof ModelAnim)
				((ModelAnim)model).draw(m_pos,getModelSize(),getColor(),m_angle,Logic.CURRENT_TIME,"NAN");
			else
				model.draw(m_pos,getModelSize(),getColor(),m_angle);
		}
	}
	
	private Vector2f m_direction=new Vector2f();
	private float m_distance=0;
	private Entity m_owner=null;
	private float m_angle;
	
	
	
	
	private static ArrayList<EntityMissile> all=new ArrayList();
	public static ArrayList getAll() {
		return all;
	}
	public static EntityMissile create() {
		EntityMissile temp=new EntityMissile();
		temp.setDir(new Vector2f());
		return temp;
	}
	public static void remove(Entity e) {
		EntityDynamic.remove(e);
		all.remove(e);
	}
}
