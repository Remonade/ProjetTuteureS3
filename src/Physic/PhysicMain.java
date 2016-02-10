package Physic;

import Logic.Entity;
import Logic.EntityDynamic;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;

import java.util.ArrayList;

public class PhysicMain {
    
    private boolean collision;
    public static final float GRAVITY = -0.005f;
	public static final float FALL_SPEED_LIMIT = -0.1f;
    public static final float COLLISION_OFFSET=0.0001f;
	public static final float COLLISION_CONTACT_DISTANCE=0.00015f;
	public static void updateRealm(Realm r) {
		ArrayList<Entity> all=(ArrayList<Entity>)r.getEntities().clone();
		all.addAll(r.getUnits());
		update(r.getUnits(),all);
	}
    public static void update(ArrayList<EntityUnit> units, ArrayList<Entity> all){
        for(int i=0;i<units.size();i++) {
            EntityUnit u=units.get(i);
			u.addSpeed(0f,PhysicMain.GRAVITY);
			for(int j=Entity.CONTACT_UP;j<Entity.CONTACT_RIGHT+1;j++)
				u.setContact(j, false);
			if(u.getCollide())
				adjust(u,all);
            if(u.getContact(Entity.CONTACT_DOWN))
                u.setSpeed(u.getSpeed().x*0.9f,u.getSpeed().y);
            if(u.getContact(Entity.CONTACT_LEFT)||u.getContact(Entity.CONTACT_RIGHT))
                u.setSpeed(u.getSpeed().x,u.getSpeed().y*0.8f);
        }
    }
    
    public static boolean collisionEE(Entity ent1, Entity ent2){
		return (collisionXEE(ent1,ent2) && collisionYEE(ent1,ent2));
    }
    public static boolean collisionXEE(Entity ent1, Entity ent2){
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();
        
        return !(pos1.x-(size1.x) > pos2.x+size2.x || // trop en haut
                pos1.x+(size1.x) < pos2.x-size2.x); // trop en bas
    }
    public static boolean collisionYEE(Entity ent1, Entity ent2){
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();
        
        return !(pos1.y-(size1.y) > pos2.y+size2.y || // trop en haut
                pos1.y+(size1.y) < pos2.y-size2.y); // trop en bas
    }
    public static void contactEE(Entity ent1, Entity ent2) {
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();
		
		Vector2f distance = pos2.subtract(pos1);
		Vector2f diff = new Vector2f(COLLISION_CONTACT_DISTANCE,COLLISION_CONTACT_DISTANCE).add(size1.add(size2));
		
		if(Math.abs(distance.x) <= diff.x && inRow(pos1,size1.y,pos2,size2.y+COLLISION_CONTACT_DISTANCE)) { 
			if(!inColumn(pos1,size1.x,pos2,size2.x)) {
				if (distance.x>0) { // ent1 contact par la droite
					ent1.setContact(Entity.CONTACT_RIGHT,true);
				} else { // ent1 contact par la gauche
					ent1.setContact(Entity.CONTACT_LEFT,true);
				}
			}
		}
		if(Math.abs(distance.y) <= diff.y && inColumn(pos1,size1.x,pos2,size2.x+COLLISION_CONTACT_DISTANCE)) {
			if(!inRow(pos1,size1.y,pos2,size2.y)) { 
				if (distance.y>0) { // ent1 contact par le haut
					ent1.setContact(Entity.CONTACT_UP,true);
				} else { // ent1 contact par le bas
					ent1.setContact(Entity.CONTACT_DOWN,true);
				}
			}
		}
	}
    public static boolean contact(Entity ent1, Entity ent2) {
        Vector2f pos1 = ent1.getPos();
        Vector2f pos2 = ent2.getPos();
        Vector2f size1 = ent1.getSize();
        Vector2f size2 = ent2.getSize();
		
		Vector2f distance = pos2.subtract(pos1);
		Vector2f diff = new Vector2f(COLLISION_CONTACT_DISTANCE,COLLISION_CONTACT_DISTANCE).add(size1.add(size2));
		
		if(Math.abs(distance.x) <= diff.x && inRow(pos1,size1.y,pos2,size2.y+COLLISION_CONTACT_DISTANCE)) { 
			if(!inColumn(pos1,size1.x,pos2,size2.x)) {
				return true;
			}
		}
		if(Math.abs(distance.y) <= diff.y && inColumn(pos1,size1.x,pos2,size2.x+COLLISION_CONTACT_DISTANCE)) {
			if(!inRow(pos1,size1.y,pos2,size2.y)) { 
				return true;
			}
		}
		return false;
	}
	public static void adjustX(EntityDynamic dyn,Entity e) {
		Vector2f pos1 = dyn.getPos();
		Vector2f pos2 = e.getPos();
		Vector2f size1 = dyn.getSize();
		Vector2f size2 = e.getSize();

		float minDistance=size1.x+size2.x+COLLISION_OFFSET;
		float currentDistance=Math.abs(pos2.x-pos1.x);
		float deltaDistance=Math.abs(minDistance-currentDistance);
		
		if(pos1.x>pos2.x) {
			if(e instanceof EntityDynamic) {
				pos1.x+=deltaDistance/2;
				pos2.x-=deltaDistance/2;
			} else
				pos1.x=pos2.x+minDistance;
			dyn.setContact(Entity.CONTACT_LEFT, true);
		} else if(pos1.x<pos2.x) {
			if(e instanceof EntityDynamic) {
				pos1.x-=deltaDistance/2;
				pos2.x+=deltaDistance/2;
			} else
				pos1.x=pos2.x-minDistance;
			dyn.setContact(Entity.CONTACT_RIGHT, true);
		}
		dyn.setSpeed(0,dyn.getSpeed().y);

		dyn.setPos(pos1);
		e.setPos(pos2);
	}
	public static void adjustY(EntityDynamic dyn,Entity e) {
		Vector2f pos1 = dyn.getPos();
		Vector2f pos2 = e.getPos();
		Vector2f size1 = dyn.getSize();
		Vector2f size2 = e.getSize();

		float minDistance=size1.y+size2.y+COLLISION_OFFSET;
		float currentDistance=Math.abs(pos2.y-pos1.y);
		float deltaDistance=Math.abs(minDistance-currentDistance);
		
		if(pos1.y>pos2.y) {
			if(e instanceof EntityDynamic) {
				pos1.y+=deltaDistance/2;
				pos2.y-=deltaDistance/2;
			} else
				pos1.y=pos2.y+minDistance;
			dyn.setContact(Entity.CONTACT_DOWN, true);
		} else if(pos1.y<pos2.y) {
			if(e instanceof EntityDynamic) {
				pos1.y-=deltaDistance/2;
				pos2.y+=deltaDistance/2;
			} else
				pos1.y=pos2.y-minDistance;
			dyn.setContact(Entity.CONTACT_UP, true);
		}
		dyn.setSpeed(dyn.getSpeed().x,0);

		dyn.setPos(pos1);
		e.setPos(pos2);
	}
    public static void adjust(EntityDynamic dyn,ArrayList<Entity> entities) {
        EntityDynamic futur=new EntityDynamic();
        futur.setData(dyn.getData());
		futur.setSpeed(dyn.getSpeed());
        futur.setPos(dyn.getPos().add(dyn.getSpeed()));
        
        int j=entities.indexOf(dyn);
		
        for(int i=0;i<entities.size();i++){
			if(i!=j) {
				Entity temp=entities.get(i);
				if(temp.getCollide())
					if(collisionEE(futur,temp)) {
						Vector2f inter=futur.getPos().subtract(temp.getPos());
						float sizex=futur.getSize().x+temp.getSize().x;
						float sizey=futur.getSize().y+temp.getSize().y;
						float x=Math.abs(inter.x/sizex);
						float y=Math.abs(inter.y/sizey);
						if(x<1 && x>y) {
							adjustX(futur,temp);
						} else if(y<1 && x<y){
							adjustY(futur,temp);
						}
					}
			}
        }
		
		dyn.setPos(futur.getPos());
		dyn.setSpeed(futur.getSpeed());
		
        for(int i=0;i<entities.size();i++){
			Entity temp=entities.get(i);
			if(i!=j) {
				contactEE(dyn,temp);
			}
        }
		
    }
	public static boolean inSquare(Vector2f pos, Vector2f center, Vector2f size){
		if(pos.y<center.y-size.y || pos.y>center.y+size.y ||
			pos.x<center.x-size.x || pos.x>center.x+size.x)
			return false;
		return true;
	}
	public static boolean inRow(Vector2f pos, float h, Vector2f center, float H) {
		if(pos.y+h<center.y-H || pos.y-h>center.y+H)
			return false;
		return true;
	}
	public static boolean inColumn(Vector2f pos, float w, Vector2f center, float W) {
		if(pos.x+w<center.x-W || pos.x-w>center.x+W)
			return false;
		return true;
	}
}
