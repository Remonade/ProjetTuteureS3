/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Physic;

import Logic.Entity;
import Logic.EntityDynamic;
import Logic.EntityUnit;
import Logic.Realm;
import Maths.Vector2f;
import static Physic.PhysicMain.COLLISION_OFFSET;
import static Physic.PhysicMain.contactEE;
import java.util.ArrayList;

public class Vectorial {
	
	public static void updateRealm(Realm r) {
		ArrayList<Entity> all=(ArrayList<Entity>)r.getEntities().clone();
		all.addAll(r.getUnits());
		update(r.getUnits(),all);
	}
    public static void update(ArrayList<EntityUnit> units, ArrayList<Entity> all) {
		for (EntityUnit u : units) {
			
			u.addSpeed(0f,PhysicMain.GRAVITY); // accélération de la gravité
			
			for(int j=Entity.CONTACT_UP;j<Entity.CONTACT_RIGHT+1;j++)
				u.setContact(j, false); // préparation des nouveaux contacts
			
			if(u.getCollide()) // si l'entité peut avoir des collisions
				adjust(u,all); // détection et ajustement
			
			for(Entity e:all)
				contactEE(u,e);
		
			if(u.getContact(Entity.CONTACT_DOWN)) { // ralentissement au sol
				u.setSpeed(u.getSpeed().x*0.9f,u.getSpeed().y);
			}
			if(u.getContact(Entity.CONTACT_UP)) { // bloquage plafond
				u.setSpeed(u.getSpeed().x,0);
			}
			if(u.getContact(Entity.CONTACT_LEFT) || u.getContact(Entity.CONTACT_RIGHT)) {
				u.setSpeed(u.getSpeed().x,u.getSpeed().y*0.9f);  // ralentissement sur mur
			}
		}
    }
	public static boolean adjust(Entity a,ArrayList<Entity> all) {
		EntityDynamic u=null;
		if(a instanceof EntityDynamic)
			u=(EntityDynamic)a;
		if(u==null)
			return false;
		
		Vector2f pos1=u.getPos();
		Vector2f dest=pos1.add(u.getSpeed());
		
		for(Entity e:all)
			if(u!=e && e.getCollide()) // si pas lui même
				dest=entityCollide(u,dest,e);
		
		Vector2f speed=dest.subtract(pos1);
		u.setPos(dest);
		u.setSpeed(speed);
		return true;
	}
	public static Vector2f entityCollide(EntityDynamic a, Vector2f dest, Entity b) {
		if(a==null || b==null)
			return dest;
		
		Vector2f pos1=a.getPos();
		Vector2f speed=dest.subtract(pos1);
		
		if(speed.x==0.0f && speed.y==0.0f)
			return dest;
		
		return vectorCollide(a,dest,b);
	}
	
	public static Vector2f vectorCollide(EntityDynamic u,Vector2f dest, Entity e) {
		Vector2f pos1=u.getPos();
		Vector2f speed=dest.subtract(pos1);
		Vector2f pos2=e.getPos();
		Vector2f size=e.getSize().add(u.getSize());
		
		float left,right,top,bottom;
		left=pos2.x-size.x;
		right=pos2.x+size.x;
		top=pos2.y+size.y;
		bottom=pos2.y-size.y;
		
		if(speed.y<0.0f) { // si deplacement vers le bas
			if(pos1.y < bottom) // s'éloigne verticalement
				return dest;
			if(dest.y > top) // ne s'approche pas assez
				return dest;
			
			if(pos1.x > right) { // initalement à droite
				if(dest.x > right) // approche horiontale insufisante
					return dest;
				
				float distanceX=Math.abs(pos1.x-right);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact
					float distanceY=Math.abs(pos1.x-top);
					float ratioY=distanceY/speed.y;
					float offsetY=speed.x*Math.abs(ratioY);
					if(pos1.x+offsetY>right || pos1.x+offsetY<left) // pas de point de contact
						return dest;
					else {
						dest.y=top+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
						return dest;
					}
				} else {
					dest.x=right+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			} else if(pos1.x < left) { // initialement à gauche
				if(dest.x < left) // approche horizontale insufisante
					return dest;
				
				float distanceX=Math.abs(left-pos1.x);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact
					float distanceY=Math.abs(top-pos1.y);
					float ratioY=distanceY/speed.y;
					float offsetY=speed.x*Math.abs(ratioY);
					if(pos1.x+offsetY>right || pos1.x+offsetY<left) { // pas de point de contact
						return dest;
					} else {
						dest.y=top+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
						return dest;
					}
				} else {
					dest.x=left-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			}
			
			
			float distance=Math.abs(top-pos1.y);
			float ratio=distance/speed.y;
			float offset=speed.x*Math.abs(ratio);
			if(pos1.x+offset>right || pos1.x+offset<left) // pas de point de contact
				return dest;
			else {
				dest.y=top+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
				return dest;
			}
		} else if(speed.y>0.0f) { // sinon, deplacement vers le haut
			if(pos1.y > top) // s'éloigne verticalement
				return dest;
			if(dest.y < bottom) // ne s'approche pas assez
				return dest;
			
			if(pos1.x > right) { // initalement à droite
				if(dest.x > right) // approche horiontale insufisante
					return dest;
				
				float distanceX=Math.abs(pos1.x-right);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact par la gauche
					float distanceY=Math.abs(pos1.x-bottom);
					float ratioY=distanceY/speed.y;
					float offsetY=speed.x*Math.abs(ratioY);
					if(pos1.x+offsetY>right || pos1.x+offsetY<left) // pas de point de contact par le haut
						return dest;
					else {
						dest.y=bottom-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
						return dest;
					}
				} else {
					dest.x=right+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			} else if(pos1.x < left) { // initialement à gauche
				if(dest.x < left) // approche horizontale insufisante
					return dest;
				
				float distanceX=Math.abs(left-pos1.x);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact
					float distanceY=Math.abs(bottom-pos1.y);
					float ratioY=distanceY/speed.y;
					float offsetY=speed.x*Math.abs(ratioY);
					if(pos1.x+offsetY>right || pos1.x+offsetY<top) // pas de point de contact
						return dest;
					else {
						dest.y=bottom-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
						return dest;
					}
				} else {
					dest.x=left-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			}
			
			
			float distance=Math.abs(pos1.y-bottom);
			float ratio=distance/speed.y;
			float offset=speed.x*Math.abs(ratio);
			if(pos1.x+offset>right || pos1.x+offset<left) // pas de point de contact
				return dest;
			else {
				dest.y=bottom-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
				return dest;
			}
		} else if(speed.x!=0.0f) { // sinon déplacement horizontale
			
			if(pos1.x > right) { // initalement trop à droite
				if(dest.x > right) // approche horiontale insufisante
					return dest;
				
				float distanceX=Math.abs(pos1.x-right);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact
					return dest;
				} else {
					dest.x=right+COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			} else if(pos1.x < left) { // initialement trop à gauche
				if(dest.x < left) // approche horizontale insufisante
					return dest;
				
				float distanceX=Math.abs(left-pos1.x);
				float ratioX=distanceX/speed.x;
				float offsetX=speed.y*Math.abs(ratioX);
				if(pos1.y+offsetX>top || pos1.y+offsetX<bottom) { // pas de point de contact
					return dest;
				} else {
					dest.x=left-COLLISION_OFFSET; // point trouvé, ajuster la vitesse
					return dest;
				}
			}
			
		}
		
		return dest;
	}
	
}
