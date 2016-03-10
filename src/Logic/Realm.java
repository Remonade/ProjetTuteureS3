/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logic;

import GUI.GUI;
import GUI.GUICheckBox;
import Graphic.GraphicMain;
import Graphic.Model;
import Maths.Vector4f;
import Physic.PhysicMain;
import Physic.Vectorial;
import Tests.Main;
import static Tests.Main.STATE_SETTING_MENU;
import java.util.ArrayList;

public class Realm {
	private Realm(String name) {
		m_name=name;
	}
	private String m_name="";
	private String m_music="";
	private ArrayList<Entity> m_entities=new ArrayList<Entity>();
	private ArrayList<EntityUnit> m_units=new ArrayList<EntityUnit>();
	private ArrayList<EntityMissile> m_missiles=new ArrayList<EntityMissile>();
	private ArrayList<EntityWayPoint> m_wayPoints=new ArrayList<EntityWayPoint>();
	private ArrayList<EntityParticle> m_particles=new ArrayList<EntityParticle>();
	
	private Model m_background=null;
	private Vector4f m_cameraBound=null;
	private EntityUnit m_boss=null;
	public void setBackground(Model background) {
		m_background=background;
	}
	public Model getBackground() {
		return m_background;
	}
	public void setCameraBound(float l, float r, float b, float t) {
		if(m_cameraBound==null)
			m_cameraBound=new Vector4f(l,r,b,t);
		else {
			m_cameraBound.x=l;
			m_cameraBound.y=r;
			m_cameraBound.z=b;
			m_cameraBound.w=t;
		}
	}
	public void setCameraBound(Vector4f bound) {
		if(m_cameraBound==null)
			m_cameraBound=new Vector4f(bound.x,bound.y,bound.z,bound.w);
		else {
			m_cameraBound.x=bound.x;
			m_cameraBound.y=bound.y;
			m_cameraBound.z=bound.z;
			m_cameraBound.w=bound.w;
		}
	}
	public Vector4f getCameraBound() {
		if(m_cameraBound==null)
			m_cameraBound=new Vector4f(-100,100,-100,100);
		return m_cameraBound;
	}
	public void setBoss(EntityUnit boss) {
		m_boss=boss;
	}
	public EntityUnit getBoss() {
		return m_boss;
	}
	public void setMusic(String path) {
		m_music=path;
	}
	public String getMusic() {
		return m_music;
	}
	public void addEntity(Entity e) {
		if(e instanceof EntityParticle) {
			GUI gui=Main.getGameState(STATE_SETTING_MENU).getGUI("GUI_CB_PARTICLES");
			if(gui!=null && ((GUICheckBox)gui).getValue()) {
					m_particles.add((EntityParticle)e);
			}
		} else if(e instanceof EntityMissile)
			m_missiles.add((EntityMissile)e);
		else if(e instanceof EntityWayPoint) {
			((EntityWayPoint)e).setID(m_wayPoints.size());
			m_wayPoints.add((EntityWayPoint)e);
		} else if(e instanceof EntityUnit) {
			if(!m_units.contains((EntityUnit)e))
				m_units.add((EntityUnit)e);
		}
		else if(e instanceof Entity)
			m_entities.add(e);
	}
	public void removeEntity(Entity e) {
		if(e!=null) {
			if(e instanceof EntityWayPoint)
				m_wayPoints.remove((EntityWayPoint)e);
			if(e instanceof EntityParticle)
				m_particles.remove((EntityParticle)e);
			else if(e instanceof EntityMissile)
				m_missiles.remove((EntityMissile)e);
			else if(e instanceof EntityUnit)
				m_units.remove((EntityUnit)e);
			else if(e instanceof Entity)
				m_entities.remove(e);
		}
	}
	public void clear() {
		m_particles.clear();
		m_wayPoints.clear();
		m_missiles.clear();
		m_units.clear();
		m_entities.clear();
	}
	public ArrayList<Entity> getEntities() {
		return m_entities;
	}
	public ArrayList<EntityUnit> getUnits() {
		return m_units;
	}
	public ArrayList<EntityMissile> getMissiles() {
		return m_missiles;
	}
	public ArrayList<EntityParticle> getParticles() {
		return m_particles;
	}
	public ArrayList<EntityWayPoint> getWayPoints() {
		return m_wayPoints;
	}
	public void update() {
		// update pos
		updateUnitPosition(); // physic
		
		updateUnitStatistique(); // logic
		
		updateMissile(); // physic+logic
			
		updateParticle(); // graphic
	}
	private void updateUnitPosition() {
		Vectorial.updateRealm(this);
	}
	private void updateUnitStatistique() {
		Logic.updateRealm(this);
	}
	private void updateMissile() {
		for(int i=0;i<m_missiles.size();i++) {
			EntityMissile m=m_missiles.get(i);
			m.update();
			for(int j=0;j<m_entities.size();j++) {
				Entity e=m_entities.get(j);
				if(e.getCollide() && e!=m.getOwner() && PhysicMain.collisionEE(m, e))
					m.explode(e);
			}
			for(int j=0;j<m_units.size();j++) {
				EntityUnit u=m_units.get(j);
				if(u.getCollide() && u!=m.getOwner() && PhysicMain.collisionEE(m, u))
					m.explode(u);
			}
		}
	}
	private void updateParticle() {
        for(int i=0;i<m_particles.size();i++) {
            EntityParticle p=m_particles.get(i);
            if(p.updateTime()) {
                m_particles.remove(i);
			}
        }
	}
	public void draw() {
		GraphicMain.displayRealm(this);
	}
	public String getName() {
		return m_name;
	}
	
	private static ArrayList<Realm> realmList=new ArrayList<Realm>();
	private static Realm m_activeRealm=null;
	private static int m_activeRealmID=-1;
	
	public static int createRealm(String name) {
		Realm r=new Realm(name);
		int count=getRealmCount();
		realmList.add(r);
		return count; // résussite, index du nouveau realm.
	}
	public static int getRealmCount() {
		return realmList.size();
	}
	public static Realm getRealm(int id) {
		if(id<getRealmCount())
			return realmList.get(id);
		return null;
	}
	
	public static Realm getActiveRealm() {
		if(m_activeRealm!=null)
			return m_activeRealm;
		return m_activeRealm;
	}
	
	public static int getActiveRealmID() {
		return m_activeRealmID;
	}
	public static void setRealm(int realm) {
		System.out.println("change realm: "+m_activeRealmID+" to "+realm);
		Realm r=getRealm(realm);
		if(r!=null) {
			if(realm<m_activeRealmID) {
				System.out.println("spawn at end");
				r.getWayPoints().get(1).moveToWayPoint(Logic.getPlayer());
			} else {
				System.out.println("spawn at start");
				r.getWayPoints().get(0).moveToWayPoint(Logic.getPlayer());
			}
			m_activeRealm=r;
			m_activeRealm.addEntity(Logic.getPlayer());
			String musicPath=m_activeRealm.getMusic();
			m_activeRealmID=realm;
			if(!musicPath.equals(""))
				Audio.Audio.playMusic(musicPath,true);
		} else {
			System.out.println("Realm: "+m_activeRealmID+" not found");
		}
	}
	public static void changeRealm(int realm) {
		System.out.println("change realm: "+m_activeRealmID+" to "+realm);
		Realm r=getRealm(realm);
		if(r!=null) {
			if(realm<m_activeRealmID) {
				System.out.println("spawn at end");
				r.getWayPoints().get(1).moveToWayPoint(Logic.getPlayer());
			} else {
				System.out.println("spawn at start");
				r.getWayPoints().get(0).moveToWayPoint(Logic.getPlayer());
			}
			m_activeRealm=r;
			m_activeRealm.addEntity(Logic.getPlayer());
			String musicPath=m_activeRealm.getMusic();
			m_activeRealmID=realm;
			if(!musicPath.equals(""))
				Audio.Audio.playMusic(musicPath,true);
		} else {
			System.out.println("Realm: "+m_activeRealmID+" not found");
		}
	}
	public static void clearAll() {
		m_activeRealm=null;
		for(Realm r:realmList)
			r.clear();
		realmList.clear();
	}
}
